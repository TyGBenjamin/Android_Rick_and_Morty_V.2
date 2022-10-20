package com.example.lib_data.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_keys")
data class CharacterRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?,
)

