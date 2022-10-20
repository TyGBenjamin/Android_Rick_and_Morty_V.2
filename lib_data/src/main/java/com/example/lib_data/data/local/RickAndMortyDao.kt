package com.example.lib_data.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.lib_data.domain.models.CharacterListWrapper
import com.example.lib_data.domain.models.Character
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

@Dao
interface RickAndMortyDao {
    @Query("SELECT * FROM characters")
    fun getCharacters(): PagingSource<Int, Character>

    @Query("SELECT * FROM characters WHERE id in(:id)")
    fun getCharacterById(id: Int): Character

    @Insert(onConflict = REPLACE)
    fun insertCharacters(characters: List<Character>)

    @Insert
    suspend fun insertCharacters(vararg characters: Character)

    @Delete
    suspend fun deleteCharacters(vararg characters: Character)

    @Query("DELETE FROM characters")
    fun deleteAll()
}