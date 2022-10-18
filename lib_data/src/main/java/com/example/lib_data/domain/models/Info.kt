package com.example.lib_data.domain.models

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
){
    override fun toString(): String {
        return "$count:$next:$pages:$prev"
    }
}