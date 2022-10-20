package com.example.lib_data.data.remote

import android.location.Location
import com.example.lib_data.domain.models.CharWrapper
import com.example.lib_data.domain.models.Data
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.domain.models.LocationDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path




interface ApiService {
    @GET("character")
    suspend fun getCharacters(): Response<Data>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<com.example.lib_data.domain.models.Character>

    @GET("episode/{id}")
    suspend fun getEpisodeById(@Path("id") id: Int): Response<Episode>

    @GET("location/{id}")
    suspend fun getLocationById(@Path("id") id: Int): Response<LocationDetails>
}