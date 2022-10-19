package com.example.lib_data.domain.remote.repository

import com.example.lib_data.domain.models.CharacterListWrapper
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.domain.models.Location
import com.example.lib_data.domain.util.Resource

interface Repository {
    suspend fun getCharacters(): Resource<CharacterListWrapper>
    suspend fun getCharacterById(id: Int): Resource<Character>
    suspend fun getEpisodeById(id: Int): Resource<Episode>
    suspend fun getLocationById(id: Int): Resource<Location>
}