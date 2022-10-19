package com.example.lib_data.domain.repository


import com.example.lib_data.domain.models.Data
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.util.Resource
import com.example.lib_data.domain.models.Character

interface Repository {
    suspend fun  getCharacterList(): Resource<Data>
    suspend fun  getEpisodeById(id:Int): Resource<Episode>
    suspend fun  getCharactersById(id:String): Resource<Character>

}