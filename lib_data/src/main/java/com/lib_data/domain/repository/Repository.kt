package com.lib_data.domain.repository

import androidx.paging.PagingData
import com.lib_data.domain.models.*
import com.lib_data.resources.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getAllCharacters(): Flow<PagingData<CharacterDetails>>
    suspend fun getCharacterById(id: Int): Resource<CharacterDetails>
    suspend fun getLocationDetailsById(id: Int): Resource<LocationDetails>

    suspend fun getEpisodeById(id: Int): Resource<EpisodeDetails>
}