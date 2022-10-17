package com.lib_data.domain.models

import com.google.gson.annotations.SerializedName

data class EpisodeWrapper (
    @SerializedName(value = "results")
    val episodes: List<EpisodeDetails>
)