package com.example.lib_data.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.lib_data.data.local.RickAndMortyDatabase
import com.example.lib_data.data.paging.CharacterRemoteMediator
import com.example.lib_data.data.remote.RickAndMortyApi
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.models.CharacterListWrapper
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.domain.models.Location
import com.example.lib_data.domain.remote.repository.Repository
import com.example.lib_data.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: RickAndMortyApi,
    private val database: RickAndMortyDatabase,
) : Repository {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getCharacters(): Flow<PagingData<Character>> =
        withContext(Dispatchers.IO) {
            val pagingSourceFactory = { database.rickAndMortyDao().getCharacters() }
            Pager(
                config = PagingConfig(20),
                pagingSourceFactory = pagingSourceFactory,
                remoteMediator = CharacterRemoteMediator(
                    api =  apiService,
                    database = database
                )
            ).flow
        }

    override suspend fun getCharacterById(id: Int): Resource<Character> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response = apiService.getCharacterById(id)
                if (response.isSuccessful && response.body() != null) Resource.Success(response.body()!!)
                else Resource.Error("Some Error")
            } catch (e: Exception) {
                Resource.Error(e.message.toString())
            }
        }

    override suspend fun getEpisodeById(id: Int): Resource<Episode> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = apiService.getEpisodeById(id)
            if (response.isSuccessful && response.body() != null) Resource.Success(response.body()!!)
            else Resource.Error("Some Error")
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getLocationById(id: Int): Resource<Location> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response = apiService.getLocationById(id)
                if (response.isSuccessful && response.body() != null) Resource.Success(response.body()!!)
                else Resource.Error("Some Error")
            } catch (e: Exception) {
                Resource.Error(e.message.toString())
            }
        }
}