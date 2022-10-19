package com.lib_data.data.local.typeconverters

import androidx.room.TypeConverter
import com.lib_data.domain.models.*

class TypeConvertersHelper {
    private val separator = ","
    @TypeConverter
    fun convertListToString(list: List<String>): String {
        val stringBuilder = StringBuilder()
        for (item in list) {
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
    fun originToString(origin: Origin) = "$origin"

    @TypeConverter
    fun stringToOrigin(value: String): Origin {
        val name = value.substringBefore(':')
        val url = value.substringAfter(':')

        return Origin(name, url)
    }
    @TypeConverter
    fun locationToString(location: Location) = "$location" //Other options are json string, serialized blob

    @TypeConverter
    fun stringToLocation(value: String): Location {
        val name = value.substringBefore(':')
        val url = value.substringAfter(':')

        return Location(name, url)
    }

    @TypeConverter
    fun infoToString(info: CharacterInfo) = "$info" //Other options are json string, serialized blob
    @TypeConverter
    fun stringToInfo(value: String): CharacterInfo {
        val count = value.substringBefore(':').toInt()
        val pages = value.substringAfter(':').toInt()
        val next = value.substringAfter(':')
        val prev = value.substringAfter(':')

        return CharacterInfo(count, pages, next, prev)
    }

    @TypeConverter
    fun episodeCharacterInfo(info: EpisodeCharactersInfo) = "$info" //Other options are json string, serialized blob
    @TypeConverter
    fun stringToEpisodeCharacterInfo(value: String): EpisodeCharactersInfo {
        val characters = value.substringBefore(':')
        return EpisodeCharactersInfo(convertStringToList(characters))
    }






}