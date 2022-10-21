package com.lib_data.domain.models

data class EpisodeCharactersInfo(
    val characters: List<String>
) {
    override fun toString(): String = "$characters"
}
