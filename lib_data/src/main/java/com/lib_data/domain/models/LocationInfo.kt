package com.lib_data.domain.models

data class LocationInfo(
    val count: String,
    val pages: String,
    val next: String? = null,
    val prev: String? = null
)
