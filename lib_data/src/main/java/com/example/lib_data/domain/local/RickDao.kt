package com.example.lib_data.domain.local

import androidx.room.*
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.models.Data
import com.example.lib_data.domain.models.Results
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


@Dao
interface RickDao {
    @Query("SELECT * FROM characters")
    fun getCharacters(): Flow<List<com.example.lib_data.domain.models.Character>>

    @Query("select * from characters WHERE id in(:id)")
    suspend fun getCharactersById(id:Int): Character

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter (character: Character)

    @Delete
    fun deleteCharacter(character:Character)

}