package com.okation.aivideocreator.data.usecase

import com.okation.aivideocreator.data.RoomRepositoryImpl
import com.okation.aivideocreator.domain.model.local.SongEntity
import javax.inject.Inject

class DeleteSongUseCase @Inject constructor(private val repo : RoomRepositoryImpl) {
    suspend operator fun invoke(song : SongEntity) = repo.deleteSong(song)
}