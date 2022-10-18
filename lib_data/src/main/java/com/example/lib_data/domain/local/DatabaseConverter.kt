package com.example.lib_data.domain.local

import androidx.room.TypeConverter
import com.example.lib_data.domain.models.CharLocation
import com.example.lib_data.domain.models.Info
import com.example.lib_data.domain.models.Origin


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
    fun originToString(origin: Origin) = "$origin" //Other options are json string, serialized blob
    @TypeConverter
    fun stringToOrigin(value: String): Origin {
        val name = value.substringBefore(':')
        val url = value.substringAfter(':')

        return Origin(name, url)
    }

    @TypeConverter
    fun locationToString(location: CharLocation) = "$location" //Other options are json string, serialized blob
    @TypeConverter
    fun stringToLocation(value: String): CharLocation {
        val name = value.substringBefore(':')
        val url = value.substringAfter(':')

        return CharLocation(name, url)
    }

    @TypeConverter
    fun infoToString(info: Info) = "$info" //Other options are json string, serialized blob
    @TypeConverter
    fun stringToInfo(value: String): Info {
        val count = value.substringBefore(':').toInt()
        val next = value.substringAfter(':')
        val pages = value.substringAfter(':').toInt()
        val prev = value.substringAfter(':')

        return Info(count, next, pages, prev)
    }

//    @TypeConverter
//    fun convertOriginToString(origin: Origin): String {
//
//        val stringBuilder = StringBuilder()
//        for (item in origin.name) {
//            stringBuilder.append(item).append(separator)
//        }
//        for (item in origin.url) {
//            stringBuilder.append(item).append(separator)
//        }
//        stringBuilder.setLength(stringBuilder.length - separator.length)
//        return stringBuilder.toString()
//
//    }
//    @TypeConverter
//
//    fun convertOriginToObject(origin: String): Origin {
//
//        return Origin(origin, null)
//    }
//
//    @TypeConverter
//    fun convertLocationToString(location: CharLocation): String {
//        val stringBuilder = StringBuilder()
//        for (item in location.name!!) {
//            stringBuilder.append(item).append(separator)
//        }
//        for (item in location.url!!) {
//            stringBuilder.append(item).append(separator)
//        }
//        stringBuilder.setLength(stringBuilder.length - separator.length)
//        return stringBuilder.toString()
//    }
//    @TypeConverter
//    fun convertLocationToObject(location:String): CharLocation {
//        return CharLocation(location, null)
//    }
}