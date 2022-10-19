package com.example.lib_data.domain.repository

import com.example.lib_data.domain.models.Data
import com.example.lib_data.utils.Resource

interface Repository {
    suspend fun getCharacters() : Resource<Data>
    suspend fun getCharacterById(id:Int) : Resource<Data>
//    suspend fun getAnimeById(id: String): Resource<AnimeWrapper>
}