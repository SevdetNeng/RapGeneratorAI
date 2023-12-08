package com.okation.aivideocreator.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.okation.aivideocreator.domain.dao.SongDao
import com.okation.aivideocreator.domain.model.local.SongEntity

@Database(entities = [SongEntity::class], version = 3)
abstract class SongDatabase : RoomDatabase() {
    abstract fun songDao() : SongDao
}