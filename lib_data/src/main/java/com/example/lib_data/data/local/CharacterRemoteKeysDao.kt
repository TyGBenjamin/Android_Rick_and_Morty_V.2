package com.example.lib_data.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lib_data.domain.models.CharacterRemoteKeys

@Dao
interface CharacterRemoteKeysDao {
    @Query("SELECT * FROM character_keys WHERE id in(:id)")
    suspend fun getRemoteKeys(id: Int): CharacterRemoteKeys?

    @Query("DELETE FROM character_keys")
    fun deleteAllKeys()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllRemoteKeys(characterRemoteKeys: List<CharacterRemoteKeys>)
}