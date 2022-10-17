package com.lib_data.domain.models

data class LocationDetails (
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: Residents,
    val url: String,
    val created: String
)