package com.example.lib_data.domain.repository

import com.example.lib_data.domain.models.CharWrapper
import com.example.lib_data.domain.models.Data
import com.example.lib_data.utils.Resource
import com.example.lib_data.domain.models.Character

interface Repository {
    suspend fun getCharacters() : Resource<Data>
    suspend fun getCharacterById(id:Int) : Resource<Character>
//    suspend fun getAnimeById(id: String): Resource<AnimeWrapper>
}