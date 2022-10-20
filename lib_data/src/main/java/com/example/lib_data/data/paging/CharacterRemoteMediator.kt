package com.example.lib_data.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.lib_data.data.local.RickAndMortyDatabase
import com.example.lib_data.data.remote.RickAndMortyApi
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.models.CharacterRemoteKeys
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val api: RickAndMortyApi,
    private val database: RickAndMortyDatabase,
) :
    RemoteMediator<Int, Character>() {
    private val characterDao = database.rickAndMortyDao()
    private val characterRemoteKeysDao = database.characterRemoteKeysDao()


    @OptIn(ExperimentalPagingApi::class)
    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = characterRemoteKeysDao.getRemoteKeys(id = 1)?.lastUpdated ?: 0
        val cacheTimeout = 1440
        val diffInMinute = (currentTime - lastUpdated) / 6000
        return if (diffInMinute <= cacheTimeout) InitializeAction.SKIP_INITIAL_REFRESH
        else InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
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
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    nextPage
                }
            }
            val response = api.getCharacters(page = page)
            if (response.results.isNotEmpty()) {
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        characterDao.deleteAll()
                        characterRemoteKeysDao.deleteAllKeys()
                    }
                    val prevPage = response.info.prev?.substringAfter("=")
                    val nextPage = response.info.next?.substringAfter("=")
                    val keys = response.results.map { character ->
                        CharacterRemoteKeys(
                            id = character.id,
                            prevPage = prevPage?.toInt(),
                            nextPage = nextPage?.toInt(),
                            lastUpdated = System.currentTimeMillis()
                        )
                    }
                    characterRemoteKeysDao.addAllRemoteKeys(keys)
                    characterDao.insergtCharacters(response.results)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.info.next?.substringAfter("=") == null)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }

    }

    private suspend fun getRemoteKeysForLastItem(state: PagingState<Int, Character>): CharacterRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                characterRemoteKeysDao.getRemoteKeys(character.id)
            }
    }

    private suspend fun getRemoteKeysForFirstItem(state: PagingState<Int, Character>): CharacterRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                characterRemoteKeysDao.getRemoteKeys(character.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Character>): CharacterRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                characterRemoteKeysDao.getRemoteKeys(id)
            }
        }
    }
}