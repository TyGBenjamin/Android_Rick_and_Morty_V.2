package com.example.lib_data.utils

object Constants {
    const val BASE_URL = "https://rickandmortyapi.com/api/"
    const val TAG = "Logger"
    const val PREFERENCES = ""
    private fun getIdFromUrl(url: String): Int {
        val index = url.lastIndexOf('/')
        return url.substring(index + 1).toInt()
    }
}