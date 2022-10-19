package com.lib_data.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.lib_data.domain.models.CharacterDetails

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters")
    fun getAllCharacters(): PagingSource<Int, CharacterDetails>

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacterById(id: Int): CharacterDetails

    @Query("DELETE FROM characters")
    fun deleteAllCharacters()

    @Insert(onConflict = REPLACE)
    fun addCharacters(character: List<CharacterDetails>)
}