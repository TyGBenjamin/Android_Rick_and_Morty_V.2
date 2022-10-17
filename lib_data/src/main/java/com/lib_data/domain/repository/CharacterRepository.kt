package com.lib_data.domain.repository

import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.models.CharacterWrapper
import com.lib_data.resources.Resource

interface CharacterRepository {
    suspend fun getAllCharacters(): Resource<CharacterWrapper>
    suspend fun getCharacterById(id: Int): Resource<CharacterDetails>
//    suspend fun getLocationDetailsById(id: Int): Resource<LocationDetails>
//    suspend fun getEpisodeById(id: Int): Resource<EpisodeDetails>
}