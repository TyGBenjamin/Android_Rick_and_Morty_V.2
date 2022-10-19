package com.lib_data.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.lib_data.data.local.CharacterDatabase
import com.lib_data.data.remote.ApiService
import com.lib_data.data.repository.RepositoryImpl
import com.lib_data.domain.repository.Repository
import com.lib_data.domain.use_cases.GetAllCharactersUseCase
import com.lib_data.domain.use_cases.GetCharacterByIdUseCase
import com.lib_data.domain.use_cases.GetLocationDetailsByIdUseCase
import com.lib_data.utils.objects.BaseUrl.BASE_URL
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
class AppModule {
    @Provides
    @Singleton
    fun provideCharacterApiService(): ApiService {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext applicationContext: Context): CharacterDatabase = Room.databaseBuilder(
        applicationContext,
        CharacterDatabase::class.java, "character-database"
    ).build()

    @Singleton
    @Provides
    fun provideRepo(apiService: ApiService, characterDatabase: CharacterDatabase): Repository{
        return RepositoryImpl(apiService, characterDatabase)
    }

    @Provides
    fun providesGetAllCharactersUseCase(repository: Repository) = GetAllCharactersUseCase(repository)

    @Provides
    fun providesGetCharacterByIdUseCase(repository: Repository) = GetCharacterByIdUseCase(repository)

    @Provides
    fun providesGetLocationDetailsByIdUseCase(repository: Repository) = GetLocationDetailsByIdUseCase(repository)

}