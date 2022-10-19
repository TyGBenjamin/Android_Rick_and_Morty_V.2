package com.lib_data.data.remote

import com.lib_data.domain.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/api/character")
    suspend fun getAllCharacters(@Query("page") page: Int) : CharacterWrapper

    @GET("/api/character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int) : Response<CharacterDetails>

    @GET("/api/location/{id}")
    suspend fun getLocationDetailsById(@Path("id") id: Int): Response<LocationDetails>

    @GET("/api/episode/{id}")
    suspend fun getEpisodeById(@Path("id") id: Int): Response<EpisodeDetails>
}