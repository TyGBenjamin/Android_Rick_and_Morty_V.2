package com.example.lib_data.domain.models

data class Origin(
    val name: String,
    val url: String?
){
    override fun toString(): String {
        return "$name:$url"
    }
}