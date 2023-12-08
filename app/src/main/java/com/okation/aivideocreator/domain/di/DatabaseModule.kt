package com.okation.aivideocreator.domain.di

import android.content.Context
import androidx.room.Room
import com.okation.aivideocreator.data.database.SongDatabase
import com.okation.aivideocreator.domain.dao.SongDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDao(db : SongDatabase) : SongDao{
        return db.songDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context : Context) : SongDatabase{
        return Room.databaseBuilder(context,
            SongDatabase::class.java,
            "Song Database"
            ).fallbackToDestructiveMigration()
            .build()
    }
}