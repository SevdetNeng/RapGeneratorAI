package com.okation.aivideocreator.data.usecase

import com.okation.aivideocreator.data.UberduckRepositoryImpl
import com.okation.aivideocreator.domain.model.local.NetworkResponse
import com.okation.aivideocreator.domain.model.remote.uberduck.Beats
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBeatsUseCase @Inject constructor(private val repo : UberduckRepositoryImpl) {
    operator fun invoke() : Flow<NetworkResponse<Beats>> = repo.getBeats()
}