package com.example.lib_data.domain.remote.repository

import androidx.paging.PagingData
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.domain.models.Location
import com.example.lib_data.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getCharacters(): Flow<PagingData<Character>>
    suspend fun getCharacterById(id: Int): Resource<Character>
    suspend fun getEpisodeById(id: Int): Resource<Episode>
    suspend fun getLocationById(id: Int): Resource<Location>
}