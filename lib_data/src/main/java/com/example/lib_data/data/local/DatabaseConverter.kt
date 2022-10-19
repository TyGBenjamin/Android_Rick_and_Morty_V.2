package com.example.lib_data.data.local

import androidx.room.TypeConverter
import com.example.lib_data.domain.models.Info
import com.example.lib_data.domain.models.Place

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

    @TypeConverter
    fun infoToString(info: Info) = "$info"
    @TypeConverter
    fun stringToInfo(value: String): Info {
        val count = value.substringBefore(':').toInt()
        val pages = value.substringAfter(':').toInt()
        val next = value.substringAfter(':')
        val prev = value.substringAfter(':')

        return Info(count, pages, next, prev)
    }

    @TypeConverter
    fun placeToString(place: Place) = "$place"
    @TypeConverter
    fun stringToPlace(value: String): Place {
        val name = value.substringBefore(':')
        val url = value.substringAfter(':')

        return Place(name, url)
    }
}