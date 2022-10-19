package com.lib_data.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.lib_data.data.local.typeconverters.TypeConvertersHelper

@Entity(tableName = "characters")
data class CharacterDetails(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val status: String,
    @ColumnInfo
    val species : String,
    @ColumnInfo
    val type: String,
    @ColumnInfo
    val gender: String,
    @ColumnInfo
    val origin: Origin,
    @ColumnInfo
    val location: Location,
    @ColumnInfo
    val image: String,
    @ColumnInfo
    val episode: List<String>,
    @ColumnInfo
    val url: String,
    @ColumnInfo
    val created: String
)