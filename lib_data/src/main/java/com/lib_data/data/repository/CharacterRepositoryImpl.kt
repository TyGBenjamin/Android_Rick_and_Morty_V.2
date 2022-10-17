package com.lib_data.data.repository

import com.lib_data.data.remote.CharacterApiService
import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.models.CharacterWrapper
import com.lib_data.domain.repository.CharacterRepository
import com.lib_data.resources.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CharacterRepositoryImpl @Inject constructor(
    private val characterApiService: CharacterApiService,
): CharacterRepository {
    override suspend fun getAllCharacters(): Resource<CharacterWrapper> = withContext(Dispatchers.IO){
        return@withContext try{
            val res = characterApiService.getAllCharacters()
            if(res.isSuccessful && res.body() != null){
                Resource.Success(res.body()!!)
            } else{
                Resource.Error("Something went wrong")
            }
        } catch(e: Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getCharacterById(id: Int): Resource<CharacterDetails> = withContext(Dispatchers.IO){
        return@withContext try{
            val res = characterApiService.getCharacterById(id)
            if(res.isSuccessful && res.body() != null){
                Resource.Success(res.body()!!)
            } else{
                Resource.Error("Something went wrong")
            }
        } catch(e: Exception){
            Resource.Error(e.message.toString())
        }
    }

//    override suspend fun getLocationDetailsById(id: Int): Resource<LocationDetails> = withContext(Dispatchers.IO) {
//        return@withContext try{
//            val res = characterApiService.getLocationDetailsById(id)
//            if(res.isSuccessful && res.body() != null){
//                Resource.Success(res.body()!!)
//            } else{
//                Resource.Error("Something went wrong")
//            }
//        } catch(e: Exception){
//            Resource.Error(e.message.toString())
//        }
//    }
//
//    override suspend fun getEpisodeById(id: Int): EpisodeDetails {
//
//    }

}