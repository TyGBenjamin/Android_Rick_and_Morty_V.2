package com.example.lib_data.util

object Constants {
    val BASE_URL = "https://rickandmortyapi.com/api/"

    fun getIdFromUrl(url: String): String {
        val index = url.lastIndexOf('/')
        return url.substring(index + 1)
    }

}