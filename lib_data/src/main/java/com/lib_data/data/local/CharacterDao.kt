package com.lib_data.data.local

import androidx.room.Dao
import androidx.room.Query
import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.models.CharacterWrapper
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters_database")
    suspend fun getAllCharacters(): Flow<CharacterWrapper>

    @Query("SELECT * FROM characters_database WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharacterDetails

//    @Query("SELECT * FROM characters_database WHERE id = :id")
//    suspend fun getCharacterById(id: Int): CharacterDetails
}