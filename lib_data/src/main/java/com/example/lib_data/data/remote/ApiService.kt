package com.example.lib_data.data.remote


import com.example.lib_data.domain.models.Data
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.util.Constants.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    suspend fun  getCharacterList(): Response<Data>

    @GET("episode/{id}")
    suspend fun getEpisodeById(): Response<Episode>




}