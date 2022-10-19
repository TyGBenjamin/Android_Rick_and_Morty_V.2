package com.example.lib_data.data.remote

import com.example.lib_data.domain.models.CharacterListWrapper
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.domain.models.Location
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {
    @GET("/api/character")
    suspend fun getCharacters(): Response<CharacterListWrapper>

    @GET("/api/character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<Character>

    @GET("/api/episode/{id}")
    suspend fun getEpisodeById(@Path("id") id: Int): Response<Episode>

    @GET("/api/location/{id}")
    suspend fun getLocationById(@Path("id") id: Int): Response<Location>
}