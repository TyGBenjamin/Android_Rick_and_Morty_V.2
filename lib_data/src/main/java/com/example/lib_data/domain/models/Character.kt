package com.example.lib_data.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey
    @ColumnInfo val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val status: String,
    @ColumnInfo val species: String,
    @ColumnInfo val gender: String,
    @ColumnInfo val episode: List<String>,
    @ColumnInfo val image: String,
    @ColumnInfo val origin: Place,
    @ColumnInfo val location: Place,
)