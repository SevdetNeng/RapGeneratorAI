package com.okation.aivideocreator.data

import com.okation.aivideocreator.domain.dao.SongDao
import com.okation.aivideocreator.domain.model.local.SongEntity
import com.okation.aivideocreator.domain.repo.RoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(private val dao : SongDao): RoomRepository {
    override suspend fun upsertSong(song: SongEntity) {
        dao.upsertSong(song)
    }

    override fun getAllSongs(): Flow<List<SongEntity>> {
        return dao.getAllSongs()
    }

    override suspend fun deleteSong(song: SongEntity) {
        dao.deleteSong(song)
    }
}