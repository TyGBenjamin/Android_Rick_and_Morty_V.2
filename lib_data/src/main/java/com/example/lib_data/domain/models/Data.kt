package com.example.lib_data.domain.models

import androidx.room.Entity

//@Entity(tableName = "data")
data class Data(
    val info: Info,
    val results: List<com.example.lib_data.domain.models.Character>
)