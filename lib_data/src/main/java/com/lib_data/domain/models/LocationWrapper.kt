package com.lib_data.domain.models

import com.google.gson.annotations.SerializedName

data class LocationWrapper(
    @SerializedName(value ="results")
    val results : List<LocationDetails>,
    val info: LocationInfo
)