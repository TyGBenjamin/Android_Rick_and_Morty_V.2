package com.example.lib_data.di

import com.example.lib_data.data.remote.RickAndMortyApi
import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.domain.remote.repository.Repository
import com.example.lib_data.domain.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesRepo(apiService: RickAndMortyApi): Repository = RepositoryImpl(apiService)

    @Provides
    @Singleton
    fun providesApiService(): RickAndMortyApi =
        Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(RickAndMortyApi::class.java)
}
