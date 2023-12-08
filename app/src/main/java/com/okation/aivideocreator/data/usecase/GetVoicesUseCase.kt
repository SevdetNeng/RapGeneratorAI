package com.okation.aivideocreator.data.usecase

import com.okation.aivideocreator.data.UberduckRepositoryImpl
import com.okation.aivideocreator.domain.model.local.NetworkResponse
import com.okation.aivideocreator.domain.model.remote.uberduck.rapper.Voices
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVoicesUseCase @Inject constructor(val repo : UberduckRepositoryImpl) {
    operator fun invoke() : Flow<NetworkResponse<Voices>> = repo.getVoices()
}