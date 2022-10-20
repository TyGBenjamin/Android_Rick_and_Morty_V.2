package com.example.lib_data.data.repository

import com.example.lib_data.data.remote.ApiService
import com.example.lib_data.domain.models.Data
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.domain.repository.Repository
import com.example.lib_data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.models.LocationDetails


class RepositoryImpl @Inject constructor(
    private val apiInstance: ApiService
): Repository {
    override suspend fun getCharacterList(): Resource<Data> = withContext(Dispatchers.IO) {
        return@withContext try {
            val res = apiInstance.getCharacterList()
            if (res.isSuccessful && res.body() != null) {
                Resource.Success(res.body()!!)
            } else {
                Resource.Error("I AM BROKEN")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getEpisodeById(id: Int): Resource<Episode> = withContext(Dispatchers.IO) {
        return@withContext try {
            val res = apiInstance.getEpisodeById(id)
            if (res.isSuccessful && res.body() != null) {
                Resource.Success(res.body()!!)
            } else {
                Resource.Error("I AM BROKEN")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getCharactersById(id: String): Resource<Character> = withContext(Dispatchers.IO) {
        return@withContext try {
            val res = apiInstance.getCharactersById(id)
            if (res.isSuccessful && res.body() != null) {
                Resource.Success(res.body()!!)
            } else {
                Resource.Error("I AM BROKEN")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }

    }


    override suspend fun getLocationById(id: String): Resource<LocationDetails> = withContext(Dispatchers.IO) {
        return@withContext try {
            val res = apiInstance.getLocationById(id)
            if (res.isSuccessful && res.body() != null) {
                Resource.Success(res.body()!!)
            } else {
                Resource.Error("I AM BROKEN")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }




}