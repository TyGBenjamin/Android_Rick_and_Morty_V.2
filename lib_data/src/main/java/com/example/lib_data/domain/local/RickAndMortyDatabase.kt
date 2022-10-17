package com.example.lib_data.domain.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters



@Database(entities = [com.example.lib_data.domain.models.Character::class], version = 1 )
@TypeConverters(DatabaseConverter::class)
abstract class RickAndMortyDatabase : RoomDatabase() {
    abstract fun rickDao() : RickDao
}