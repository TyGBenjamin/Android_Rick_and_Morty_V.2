package com.lib_data.domain.models

import com.google.gson.annotations.SerializedName

data class LocationWrapper(
    @SerializedName(value = "results")
    val location : List<LocationDetails>
)