package com.example.lib_data.di

import android.content.Context
import androidx.room.Room
import com.example.lib_data.data.local.CharacterDatabase
import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.data.remote.ApiService
import com.example.lib_data.domain.repository.Repository
import com.example.lib_data.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        ).build().create(ApiService::class.java)!!
    }

    @Singleton
    @Provides
    fun providesRoomDB(@ApplicationContext applicationContext: Context): CharacterDatabase{
      return  Room.databaseBuilder(applicationContext, CharacterDatabase::class.java,"character-database").build()
    }


    @Singleton
    @Provides
    fun provideRepositoryImpl(apiService: ApiService, database: CharacterDatabase): Repository =
       RepositoryImpl(apiService, database)

    @Singleton
    @Provides
    fun providesRepo(apiService: ApiService, database: CharacterDatabase): Repository =
        RepositoryImpl(apiService, database)


}