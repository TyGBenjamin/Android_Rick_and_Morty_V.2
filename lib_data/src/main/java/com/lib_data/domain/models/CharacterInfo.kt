package com.lib_data.domain.models

data class CharacterInfo(
    val count: Int,
    val pages: Int,
    val next: String? = null,
    val prev: String? = null
)