package com.example.lib_data.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Episode")
data class Episode(

    @ColumnInfo
    val air_date: String,

    @ColumnInfo
    val characters: List<String>,

    @ColumnInfo
    val created: String,

    @ColumnInfo
    val episode: String,

    @PrimaryKey
    @ColumnInfo
    val id: Int,

    @ColumnInfo
    val name: String,

    @ColumnInfo
    val url: String
)