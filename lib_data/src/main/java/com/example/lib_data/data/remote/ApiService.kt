package com.example.lib_data.data.remote

import com.example.lib_data.domain.models.Data
import com.example.lib_data.utils.Constants.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path




interface ApiService {
    @GET("character")
    suspend fun getCharacters(): Response<Data>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: String): Response<Data>
}