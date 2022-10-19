package com.lib_data.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.lib_data.data.local.typeconverters.TypeConvertersHelper

@Entity(tableName = "locations")
data class LocationDetails (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val type: String,
    @ColumnInfo
    val dimension: String,
    @ColumnInfo
    val residents: List<String>,
    @ColumnInfo
    val url: String,
    @ColumnInfo
    val created: String,
)