package com.example.lib_data.data.local

import androidx.room.TypeConverter
import com.example.lib_data.domain.models.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

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
//
//    @TypeConverter
//    fun convertOriginToString(origin: Origin): String {
//        val stringBuilder = StringBuilder()
//        for (item in origin.name) {
//            stringBuilder.append(item).append(separator)
//        }
//        for (item in origin.url) {
//            stringBuilder.append(item).append(separator)
//        }
//        stringBuilder.setLength(stringBuilder.length - separator.length)
//
//        return stringBuilder.toString()
//    }
//
//
//    @TypeConverter
//    fun convertOriginToList(origin: String): List<String> {
//        return origin.split(separator)
//    }


    @TypeConverter
    fun originToString(origin: Origin) = "$origin" //Other options are json string, serialized blob

    @TypeConverter
    fun stringToOrigin(value: String): Origin {
        val name = value.substringBefore(':')
        val url = value.substringAfter(':')

        return Origin(name, url)
    }

    @TypeConverter
    fun locationToString(location: CharacterLocation) = "$location" //Other options are json string, serialized blob

    @TypeConverter
    fun stringToLocation(value: String): CharacterLocation {
        val name = value.substringBefore(':')
        val url = value.substringAfter(':')

        return CharacterLocation(name, url)
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
//    fun convertLocationToString(location: Location): String {
//        val stringBuilder = StringBuilder()
//        for (item in location.name) {
//            stringBuilder.append(item).append(separator)
//        }
//        for (item in location.url) {
//            stringBuilder.append(item).append(separator)
//        }
//        stringBuilder.setLength(stringBuilder.length - separator.length)
//        return stringBuilder.toString()
//    }
////    @TypeConverter
////    fun convertLocationToList(string: String): List<String> {
////        return string.split(separator)
////    }
//    @TypeConverter
//    fun convertLocationToList(location: Location)= Gson().toJson(location)

}