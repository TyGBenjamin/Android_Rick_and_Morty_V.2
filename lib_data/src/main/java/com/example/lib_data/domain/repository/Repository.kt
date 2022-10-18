package com.example.lib_data.domain.repository


import com.example.lib_data.domain.models.Data
import com.example.lib_data.util.Resource

interface Repository {
    suspend fun  getCharacterList(): Resource<Data>

}