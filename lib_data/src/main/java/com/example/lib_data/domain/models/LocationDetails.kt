package com.example.lib_data.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class LocationDetails(

    @ColumnInfo
    val dimension: String,

    @PrimaryKey
    @ColumnInfo
    val id: Int,

    @ColumnInfo
    val name: String,

    @ColumnInfo
    val residents: List<String>,

    @ColumnInfo
    val type: String,

    @ColumnInfo
    val url: String
)