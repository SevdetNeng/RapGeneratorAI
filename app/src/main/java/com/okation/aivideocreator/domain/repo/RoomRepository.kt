package com.okation.aivideocreator.domain.repo

import com.okation.aivideocreator.domain.model.local.SongEntity
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    suspend fun upsertSong(song : SongEntity)

    fun getAllSongs() : Flow<List<SongEntity>>

    suspend fun deleteSong(song : SongEntity)
}