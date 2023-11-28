package com.sevdetneng.rapgeneratorai.data.usecase

import com.sevdetneng.rapgeneratorai.data.UberduckRepositoryImpl
import com.sevdetneng.rapgeneratorai.domain.model.local.NetworkResponse
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.rapper.Voices
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVoicesUseCase @Inject constructor(val repo : UberduckRepositoryImpl) {
    operator fun invoke() : Flow<NetworkResponse<Voices>> = repo.getVoices()
}