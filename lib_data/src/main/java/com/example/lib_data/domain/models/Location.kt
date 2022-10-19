package com.example.lib_data.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class Location(
    @PrimaryKey
    @ColumnInfo val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val type: String,
    @ColumnInfo val dimension: String,
    @ColumnInfo val residents: List<String>
)
