package com.lib_data.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.lib_data.data.local.typeconverters.TypeConvertersHelper

@Entity(tableName = "episodes")
data class EpisodeDetails(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    @SerializedName(value = "air_date")
    val airDate: String,
    @ColumnInfo
    val characters: EpisodeCharactersInfo,
    @ColumnInfo
    val url: String,
    @ColumnInfo
    val created: String
)