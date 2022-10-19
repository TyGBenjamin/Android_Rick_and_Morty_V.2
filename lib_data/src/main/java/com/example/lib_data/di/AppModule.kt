package com.example.lib_data.di

import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.data.remote.ApiService
import com.example.lib_data.domain.repository.Repository
import com.example.lib_data.utils.Constants
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

    @Singleton
    @Provides
    fun providesApisService(): ApiService {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideRepositoryImpl(apiService: ApiService): Repository = RepositoryImpl(apiService)

//    @Provides
//    @Singleton
//    fun provideDataStore(@ApplicationContext context: Context): DataStorePrefSource = DataStorePrefImpl(context)
}