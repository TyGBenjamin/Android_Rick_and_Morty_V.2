package com.lib_data.domain.models

data class EpisodeWrapper (
    val info : List<String>,
    val results: List<EpisodeDetails>,
)