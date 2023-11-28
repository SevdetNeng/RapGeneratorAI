package com.sevdetneng.rapgeneratorai.data.usecase

import com.sevdetneng.rapgeneratorai.data.UberduckRepositoryImpl
import com.sevdetneng.rapgeneratorai.domain.model.local.NetworkResponse
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.voicedetail.Samples
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSampleUseCase @Inject constructor(private val repo : UberduckRepositoryImpl) {
    operator fun invoke(uuid : String) : Flow<NetworkResponse<Samples>> = repo.getVoiceSample(uuid)
}