package com.example.lib_data.data.remote


import com.example.lib_data.domain.models.Data
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.util.Constants.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.models.LocationDetails
import dagger.Provides
import retrofit2.http.Path


interface ApiService {

    @GET("character")
    suspend fun  getCharacterList(): Response<Data>


    @GET("location/{id}")
    suspend fun getLocationById(@Path("id")id:String): Response<LocationDetails>

    @GET("character/{id}")
    suspend fun  getCharactersById(@Path("id")id:String): Response<Character>

    @GET("episode/{id}")
    suspend fun getEpisodeById(@Path("id")id:Int): Response<Episode>






}