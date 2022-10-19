package com.lib_data.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lib_data.data.local.typeconverters.TypeConvertersHelper
import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.models.CharacterDetailsRemoteKeys
import com.lib_data.domain.models.EpisodeDetails
import com.lib_data.domain.models.LocationDetails

@Database(entities = [CharacterDetails::class, CharacterDetailsRemoteKeys::class, LocationDetails::class, EpisodeDetails::class], version = 2)
@TypeConverters(TypeConvertersHelper::class)
abstract class CharacterDatabase : RoomDatabase(){
    abstract fun characterDao(): CharacterDao
    abstract fun characterDetailsRemoteKeysDao(): CharacterDetailsRemoteKeysDao
}