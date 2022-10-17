package com.example.lib_data.data.repository

import com.example.lib_data.domain.models.Data
import com.example.lib_data.domain.remote.ApiService
import com.example.lib_data.domain.repository.Repository
import com.example.lib_data.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private var apiInstance: ApiService) : Repository {
    override suspend fun getCharacters(): Resource<Data> = withContext(Dispatchers.IO) {
        return@withContext try {
            val res = apiInstance.getCharacters()
            if (res.isSuccessful && res.body() != null) {
                Resource.Success(res.body()!!)
            } else {
                Resource.Error("An Error Occurred")
            }

        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getCharacterById(id: String): Resource<Data> {
        TODO("Not yet implemented")
    }

}