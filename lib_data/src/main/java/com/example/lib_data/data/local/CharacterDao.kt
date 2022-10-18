package com.example.lib_data.data.local

import androidx.room.*

import com.example.lib_data.domain.models.Data
import kotlinx.coroutines.flow.Flow
import com.example.lib_data.domain.models.Character
@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    fun getCharacters(): Flow<List<Character>>

    @Query("select * from character where id = :id")
    suspend fun getCharactersById(id:Int): com.example.lib_data.domain.models.Character?



}