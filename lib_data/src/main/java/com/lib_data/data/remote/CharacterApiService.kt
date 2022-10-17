package com.lib_data.data.remote

import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.models.CharacterWrapper
import retrofit2.Response
import retrofit2.http.GET

interface CharacterApiService {
    @GET("character")
    suspend fun getAllCharacters() : Response<CharacterWrapper>

    @GET("character")
    suspend fun getCharacterById(id: Int) : Response<CharacterDetails>
}