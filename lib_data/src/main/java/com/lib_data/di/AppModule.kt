package com.lib_data.di

import com.lib_data.data.remote.CharacterApiService
import com.lib_data.data.repository.CharacterRepositoryImpl
import com.lib_data.domain.repository.CharacterRepository
import com.lib_data.utils.objects.BaseUrl.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
//    @Singleton
//    @Provides
//    fun provideCharacterDatabase(@ApplicationContext applicationContext: Context): CharacterDatabase = Room.databaseBuilder(
//        applicationContext,
//        CharacterDatabase::class.java, "characters-database"
//    ).build()

    @Provides
    @Singleton
    fun provideCharacterApiService(): CharacterApiService{
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(CharacterApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideCharacterRepo(characterApiService: CharacterApiService): CharacterRepository = CharacterRepositoryImpl(characterApiService)

}