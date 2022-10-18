package com.example.lib_data.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lib_data.domain.models.Character

@Database(entities = [Character::class], version = 1 )
@TypeConverters(DatabaseConverter::class)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun characterDao() : CharacterDao
}