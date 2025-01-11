package com.example.applicationtestkb.domain

import android.content.Context
import com.example.applicationtestkb.data.database.CharacterFavDao
import com.example.applicationtestkb.data.repository.AmiiboDBRepositoryImpl
import com.example.applicationtestkb.data.repository.AmiiboRepository
import com.example.applicationtestkb.data.repository.AmiiboRepositoryImpl
import com.example.applicationtestkb.data.repository.CharacterFavDBRepository
import com.example.applicationtestkb.data.repository.CharacterFavDBRepositoryImpl
import com.example.applicationtestkb.data.repository.CharacterFavoritesRepository
import com.example.applicationtestkb.data.repository.CharacterFavoritesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAmiiboRepository(
        @ApplicationContext app: Context,
        apiService: ApiService,
        amiiboDBRepositoryImpl: AmiiboDBRepositoryImpl
    ): AmiiboRepository = AmiiboRepositoryImpl(app, apiService, amiiboDBRepositoryImpl)

    @Singleton
    @Provides
    fun provideCharacterFavDbRepository(
        characterFavDao: CharacterFavDao
    ) : CharacterFavDBRepository = CharacterFavDBRepositoryImpl(characterFavDao)

    @Singleton
    @Provides
    fun provideCharacterFavRepository(
        characterFavDBRepository: CharacterFavDBRepository
    ) : CharacterFavoritesRepository = CharacterFavoritesRepositoryImpl(characterFavDBRepository)

}