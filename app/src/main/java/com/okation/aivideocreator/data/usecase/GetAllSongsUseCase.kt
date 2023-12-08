package com.okation.aivideocreator.data.usecase

import com.okation.aivideocreator.data.RoomRepositoryImpl
import com.okation.aivideocreator.domain.model.local.SongEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSongsUseCase @Inject constructor(private val repo : RoomRepositoryImpl) {
    operator fun invoke() : Flow<List<SongEntity>> {
        return repo.getAllSongs()
    }
}