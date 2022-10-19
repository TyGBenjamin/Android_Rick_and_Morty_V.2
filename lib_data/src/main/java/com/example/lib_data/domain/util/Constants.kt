package com.example.lib_data.domain.util

object Constants {
    val BASE_URL = "https://rickandmortyapi.com"
    fun GET_ID_BY_URL(url: String): String {
        val index = url.lastIndexOf('/')
        return url.substring(index + 1)
    }
}