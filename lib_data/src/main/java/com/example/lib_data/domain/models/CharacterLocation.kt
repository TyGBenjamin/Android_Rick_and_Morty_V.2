package com.example.lib_data.domain.models


data class CharacterLocation(
    val name: String?,
    val url: String?
){
    override fun toString(): String {
        return "$name:$url"
    }
}