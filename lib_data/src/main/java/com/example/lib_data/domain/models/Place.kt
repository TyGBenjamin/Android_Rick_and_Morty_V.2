package com.example.lib_data.domain.models

data class Place(
    val name: String,
    val url: String?
) { override fun toString(): String = "$name:$url" }