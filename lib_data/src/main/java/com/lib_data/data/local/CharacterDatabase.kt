package com.lib_data.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lib_data.data.local.typeconverters.TypeConvertersHelper
import com.lib_data.domain.models.*

@Database(entities = [CharacterDetails::class,
    CharacterDetailsRemoteKeys::class], version = 5)
@TypeConverters(TypeConvertersHelper::class)
abstract class CharacterDatabase : RoomDatabase(){
    abstract fun characterDao(): CharacterDao
    abstract fun characterDetailsRemoteKeysDao(): CharacterDetailsRemoteKeysDao
}