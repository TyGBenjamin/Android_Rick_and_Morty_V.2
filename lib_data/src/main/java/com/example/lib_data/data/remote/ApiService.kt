package com.example.lib_data.data.remote

import com.example.lib_data.domain.models.CharWrapper
import com.example.lib_data.domain.models.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path




interface ApiService {
    @GET("character")
    suspend fun getCharacters(): Response<Data>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<com.example.lib_data.domain.models.Character>
}