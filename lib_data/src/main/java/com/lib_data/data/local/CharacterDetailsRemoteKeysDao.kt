package com.lib_data.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.lib_data.domain.models.CharacterDetailsRemoteKeys


@Dao
interface CharacterDetailsRemoteKeysDao {
    @Query("SELECT * FROM characters_keys WHERE id = :characterId")
    suspend fun getRemoteKeys(characterId: Int): CharacterDetailsRemoteKeys?

    @Query("DELETE FROM characters_keys")
    fun deleteAllKeys()

    @Insert(onConflict = REPLACE)
    fun addAllRemoteKeys(characterDetailsRemoteKeysDao: List<CharacterDetailsRemoteKeys>)

}
