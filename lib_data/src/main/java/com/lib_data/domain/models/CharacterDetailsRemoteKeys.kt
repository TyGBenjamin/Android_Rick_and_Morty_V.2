package com.lib_data.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters_keys")
data class CharacterDetailsRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?,
)
