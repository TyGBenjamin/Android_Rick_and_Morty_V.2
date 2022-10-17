package com.example.lib_data.domain.remote


import com.example.lib_data.domain.models.Data
import com.example.lib_data.domain.models.Results
import com.example.lib_data.utils.Constants.BASE_URL
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/character")
    suspend fun getCharacters(): Response<Data>

//    @GET("music/genre")
//    suspend fun getGenre(@Query("search") genre: String): Flow<List<com.example.lib_data.domain.models.Character>>

    companion object {
        val apiInstance =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
    }
}