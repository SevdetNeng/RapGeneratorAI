package com.sevdetneng.rapgeneratorai.data.usecase

import com.sevdetneng.rapgeneratorai.data.UberduckRepositoryImpl
import com.sevdetneng.rapgeneratorai.domain.model.local.NetworkResponse
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.Beats
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBeatsUseCase @Inject constructor(private val repo : UberduckRepositoryImpl) {
    operator fun invoke() : Flow<NetworkResponse<Beats>> = repo.getBeats()
}