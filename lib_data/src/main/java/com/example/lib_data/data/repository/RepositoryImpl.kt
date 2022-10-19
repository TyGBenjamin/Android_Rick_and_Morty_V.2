package com.example.lib_data.data.repository

import com.example.lib_data.data.remote.RickAndMortyApi
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.models.CharacterListWrapper
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.domain.models.Location
import com.example.lib_data.domain.remote.repository.Repository
import com.example.lib_data.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: RickAndMortyApi
): Repository {
    override suspend fun getCharacters(): Resource<CharacterListWrapper> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = apiService.getCharacters()
            if (response.isSuccessful && response.body() != null) Resource.Success(response.body()!!)
            else Resource.Error("Some Error")
        } catch (e: Exception) {
            println(e.message.toString())
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getCharacterById(id: Int): Resource<Character> = withContext(Dispatchers.IO) {
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

    override suspend fun getLocationById(id: Int): Resource<Location> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = apiService.getLocationById(id)
            if (response.isSuccessful && response.body() != null) Resource.Success(response.body()!!)
            else Resource.Error("Some Error")
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}