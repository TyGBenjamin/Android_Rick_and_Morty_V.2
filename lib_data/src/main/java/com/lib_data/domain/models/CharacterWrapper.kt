package com.lib_data.domain.models

import com.google.gson.annotations.SerializedName

data class CharacterWrapper(
    @SerializedName(value = "results")
    val characters: List<Characters>
)
