package com.lib_data.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.lib_data.data.local.CharacterDatabase
import com.lib_data.data.remote.ApiService
import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.models.CharacterDetailsRemoteKeys
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CharacterDetailsRemoteMediator(
    private val characterApi: ApiService,
    private val characterDatabase: CharacterDatabase,
) : RemoteMediator<Int, CharacterDetails>() {

    private val characterDao = characterDatabase.characterDao()
    //characterRemoteKeysDao
    private val characterDetailsRemoteKeysDao = characterDatabase.characterDetailsRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = characterDetailsRemoteKeysDao.getRemoteKeys(characterId = 1)?.lastUpdated ?: 0
        val cacheTimeout = 1440
        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60

        return if (diffInMinutes.toInt() <= cacheTimeout) {
            // Cached data is up-to-date, so there is no need to re-fetch from network.
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            // Need to refresh cached data from network; returning LAUNCH_INITIAL_REFRESH here
            // will also block RemoteMediator's APPEND and PREPEND from running until REFRESH
            // succeeds.
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterDetails>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH ->
                // In this example, we never need to prepend, since REFRESH will always load the
                // first page in the list. Immediately return, reporting end of pagination.
                {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    nextPage
                }
            }

        // Suspending network load via Retrofit. This doesn't need to be wrapped in a
        // withContext(Dispatcher.IO) { ... } block since Retrofit's Coroutine CallAdapter
        // dispatches on a worker thread.
        val response = characterApi.getAllCharacters(page = page)
        if(response.results.isNotEmpty()){
            characterDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    characterDao.deleteAllCharacters()
                    characterDetailsRemoteKeysDao.deleteAllKeys()
                }
                val prevPage = response.info.prev?.substringAfter("=")
                val nextPage = response.info.next?.substringAfter("=")
                val keys = response.results.map { details ->
                    CharacterDetailsRemoteKeys(
                        id = details.id,
                        prevPage = prevPage?.toInt(),
                        nextPage = nextPage?.toInt(),
                        lastUpdated = System.currentTimeMillis()
                    )
                }
                // Insert new users into database, which invalidates the current
                // PagingData, allowing Paging to present the updates in the DB.
                characterDetailsRemoteKeysDao.addAllRemoteKeys(characterDetailsRemoteKeysDao = keys)
                characterDao.addCharacters(character = response.results)
            }
        }
            MediatorResult.Success(endOfPaginationReached = response.info.next?.substringAfter("=") == null)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CharacterDetails>): CharacterDetailsRemoteKeys? {
        return state.pages.lastOrNull{ it.data.isNotEmpty() }?.data?.lastOrNull()?.let { details ->
            characterDetailsRemoteKeysDao.getRemoteKeys(characterId = details.id)
        }
    }

    private suspend fun getRemoteKeysForFirstItem(state: PagingState<Int, CharacterDetails>): CharacterDetailsRemoteKeys? {
        return state.pages.firstOrNull{ it.data.isNotEmpty() }?.data?.firstOrNull()?.let { details ->
            characterDetailsRemoteKeysDao.getRemoteKeys(characterId = details.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, CharacterDetails>): CharacterDetailsRemoteKeys? {
        return state.anchorPosition?.let{ position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                characterDetailsRemoteKeysDao.getRemoteKeys(characterId = id)
            }
        }
    }
}
