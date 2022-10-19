package com.example.lib_data.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "episodes")
data class Episode(
    @PrimaryKey
    @ColumnInfo val id: Int,
    @ColumnInfo val name: String,
    @SerializedName(value = "air_date")
    @ColumnInfo val date: String,
    @ColumnInfo val episode: String,
    @ColumnInfo val characters: List<String>
)
