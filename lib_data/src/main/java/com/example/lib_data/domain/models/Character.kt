package com.example.lib_data.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "character")
data class Character(
    @ColumnInfo
    val episode: List<String>,

    @ColumnInfo
    val gender: String,

    @PrimaryKey
    @ColumnInfo
    val id: Int,

    @ColumnInfo
    val image: String,

    @ColumnInfo
    val location: CharacterLocation,

    @ColumnInfo
    val name: String,

    @ColumnInfo
    val origin: Origin,

    @ColumnInfo
    val species: String,

    @ColumnInfo
    val status: String,

    @ColumnInfo
    val type: String,

    @ColumnInfo
    val url: String
)