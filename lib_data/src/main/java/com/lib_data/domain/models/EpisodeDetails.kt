package com.lib_data.domain.models

import com.google.gson.annotations.SerializedName

data class EpisodeDetails(
    val id: Int,
    val name: String,
    @SerializedName(value = "air_date")
    val airDate: String,
    val characters: Characters,
    val url: String,
    val created: String
)