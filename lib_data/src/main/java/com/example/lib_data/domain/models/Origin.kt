package com.example.lib_data.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity

data class Origin(
    val name: String,
    val url: String?
){
    override fun toString(): String {
        return "$name:$url"
    }
}