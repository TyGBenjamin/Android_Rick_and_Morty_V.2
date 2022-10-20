package com.example.lib_data.domain.models

data class Info(
    val count: Int,
    val pages: Int,
    val next: String? = null,
    val prev: String? = null

) { override fun toString(): String = "$count:$pages:$next:$prev" }

