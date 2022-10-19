package com.example.lib_data.domain.repository

import android.location.Location
import com.example.lib_data.domain.models.CharWrapper
import com.example.lib_data.domain.models.Data
import com.example.lib_data.utils.Resource
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.models.Episode

interface Repository {
    suspend fun getCharacters() : Resource<Data>
    suspend fun getCharacterById(id:Int) : Resource<Character>
    suspend fun getEpisodeById(id:Int) : Resource<Episode>
    suspend fun getLocationById(id:Int) : Resource<Location>
//    suspend fun getAnimeById(id: String): Resource<AnimeWrapper>
}