package com.example.applicationtestkb.domain

import android.content.Context
import androidx.room.Room
import com.example.applicationtestkb.data.database.AmiiboDao
import com.example.applicationtestkb.data.database.AmiiboDataBase
import com.example.applicationtestkb.data.database.CharacterFavDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun provideAmiiboDao(amiiboDatabase: AmiiboDataBase): AmiiboDao {
        return amiiboDatabase.amiiboDao()
    }

    @Provides
    fun provideCharacterFavDao(amiiboDatabase: AmiiboDataBase): CharacterFavDao {
        return amiiboDatabase.characterFavDao()
    }

    @Provides
    @Singleton
    fun provideAmiiboDatabase(@ApplicationContext appContext: Context): AmiiboDataBase {
        return Room.databaseBuilder(appContext, AmiiboDataBase::class.java, "AmiiboDataBase").build()
    }

}