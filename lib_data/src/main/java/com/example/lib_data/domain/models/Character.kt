package com.example.lib_data.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Character(
    @ColumnInfo
    val created: String,
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
    val location: List<String>,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val origin: List<String>,
    @ColumnInfo
    val species: String,
    @ColumnInfo
    val status: String,
    @ColumnInfo
    val type: String,
    @ColumnInfo
    val url: String
)