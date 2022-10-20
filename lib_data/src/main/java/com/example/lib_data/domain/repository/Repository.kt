package com.example.lib_data.domain.repository

import android.location.Location
import com.example.lib_data.domain.models.*
import com.example.lib_data.utils.Resource

interface Repository {
    suspend fun getCharacters() : Resource<Data>
    suspend fun getCharacterById(id:Int) : Resource<Character>
    suspend fun getEpisodeById(id:Int) : Resource<Episode>
    suspend fun getLocationById(id:Int) : Resource<LocationDetails>
//    suspend fun getAnimeById(id: String): Resource<AnimeWrapper>
}