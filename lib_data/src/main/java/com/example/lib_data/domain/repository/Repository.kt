package com.example.lib_data.domain.repository


import com.example.lib_data.domain.models.Data
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.util.Resource
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.models.LocationDetails

interface Repository {
    suspend fun  getCharacterList(): Resource<Data>
    suspend fun  getEpisodeById(id:Int): Resource<Episode>
    suspend fun  getCharactersById(id:String): Resource<Character>
    suspend fun  getLocationById(id:String): Resource<LocationDetails>

}