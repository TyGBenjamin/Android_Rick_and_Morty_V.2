package com.lib_data.domain.models

data class CharacterWrapper(
    val results: List<CharacterDetails>,
    val info: CharacterInfo,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long? = null

)
