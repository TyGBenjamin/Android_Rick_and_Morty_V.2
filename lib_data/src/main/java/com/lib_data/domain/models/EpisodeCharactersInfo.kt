package com.lib_data.domain.models

import androidx.room.TypeConverters
import com.lib_data.data.local.typeconverters.TypeConvertersHelper

data class EpisodeCharactersInfo(
    val characters: List<String>
) {
    override fun toString(): String = "$characters"
}
