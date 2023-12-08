package com.okation.aivideocreator.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.okation.aivideocreator.domain.model.local.SongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @Upsert
    suspend fun upsertSong(song : SongEntity)

    @Query("SELECT * FROM song")
    fun getAllSongs() : Flow<List<SongEntity>>
    @Delete
    suspend fun deleteSong(song : SongEntity)


}