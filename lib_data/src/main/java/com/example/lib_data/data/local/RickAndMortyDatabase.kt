package com.example.lib_data.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.models.CharacterRemoteKeys
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.domain.models.Location

@Database(entities = [Character::class, Episode::class, Location::class, CharacterRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class RickAndMortyDatabase: RoomDatabase() {
    abstract fun rickAndMortyDao(): RickAndMortyDao
    abstract fun characterRemoteKeysDao(): CharacterRemoteKeysDao
}