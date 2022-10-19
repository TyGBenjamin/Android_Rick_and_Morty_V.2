package com.lib_data.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lib_data.data.local.CharacterDatabase
import com.lib_data.data.paging.CharacterDetailsRemoteMediator
import com.lib_data.data.remote.ApiService
import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.models.EpisodeCharactersInfo
import com.lib_data.domain.models.EpisodeDetails
import com.lib_data.domain.models.LocationDetails
import com.lib_data.domain.repository.Repository
import com.lib_data.resources.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val characterDatabase: CharacterDatabase
): Repository {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getAllCharacters(): Flow<PagingData<CharacterDetails>> = withContext(Dispatchers.IO){
        val pagingSourceFactory = { characterDatabase.characterDao().getAllCharacters() }
        Pager(
            config = PagingConfig(20),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = CharacterDetailsRemoteMediator(
                characterApi = apiService,
                characterDatabase = characterDatabase
            )
        ).flow
    }

    override suspend fun getCharacterById(id: Int): Resource<CharacterDetails> = withContext(Dispatchers.IO){
        return@withContext try{
            val res = apiService.getCharacterById(id)
            if(res.isSuccessful && res.body() != null){
                Resource.Success(res.body()!!)
            } else{
                Resource.Error("Something went wrong")
            }
        } catch(e: Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getLocationDetailsById(id: Int): Resource<LocationDetails> = withContext(Dispatchers.IO){
        return@withContext try{
            val res = apiService.getLocationDetailsById(id)
            if(res.isSuccessful && res.body() != null){
                Resource.Success(res.body()!!)
            } else{
                Resource.Error("Something went wrong")
            }
        } catch(e: Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getEpisodeById(id: Int): Resource<EpisodeDetails> = withContext(Dispatchers.IO){
        return@withContext try{
            val res = apiService.getEpisodeById(id)
            if(res.isSuccessful && res.body() != null){
                Resource.Success(res.body()!!)
            } else{
                Resource.Error("Something went wrong")
            }
        } catch(e: Exception){
            Resource.Error(e.message.toString())
        }
    }
}