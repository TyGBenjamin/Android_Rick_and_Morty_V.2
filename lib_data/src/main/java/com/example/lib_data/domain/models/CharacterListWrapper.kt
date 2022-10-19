package com.example.lib_data.domain.models

import com.example.lib_data.domain.models.Character

data class CharacterListWrapper(
    val info: Info,
    val results: List<Character>
)
