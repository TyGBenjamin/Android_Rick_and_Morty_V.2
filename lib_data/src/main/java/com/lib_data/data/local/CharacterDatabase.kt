package com.lib_data.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alecbrando.musicplayer.data.typeconverters.TypeConvertersHelper
import com.lib_data.domain.models.CharacterDetails

//@Database(entities = [CharacterDetails::class], version = 1)
//@TypeConverters(TypeConvertersHelper::class)
//abstract class CharacterDatabase : RoomDatabase(){
//    abstract fun characterDao(): CharacterDao
//}