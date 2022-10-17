package com.example.lib_data.domain.local

import androidx.room.TypeConverter


class DatabaseConverter {
    private val separator = ","
    @TypeConverter
    fun convertListToString(episode: List<String>): String {
        val stringBuilder = StringBuilder()
        for (item in episode) {
            stringBuilder.append(item).append(separator)
        }
        stringBuilder.setLength(stringBuilder.length - separator.length)
        return stringBuilder.toString()
    }
    @TypeConverter
    fun convertStringToList(string: String): List<String> {
        return string.split(separator)
    }
}