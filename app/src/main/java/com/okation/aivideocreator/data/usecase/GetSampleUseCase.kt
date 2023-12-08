package com.okation.aivideocreator.data.usecase

import com.okation.aivideocreator.data.UberduckRepositoryImpl
import com.okation.aivideocreator.domain.model.local.NetworkResponse
import com.okation.aivideocreator.domain.model.remote.uberduck.voicedetail.Samples
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSampleUseCase @Inject constructor(private val repo : UberduckRepositoryImpl) {
    operator fun invoke(uuid : String) : Flow<NetworkResponse<Samples>> = repo.getVoiceSample(uuid)
}