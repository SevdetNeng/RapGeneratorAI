package com.sevdetneng.rapgeneratorai.data.usecase

import com.sevdetneng.rapgeneratorai.data.UberduckRepositoryImpl
import com.sevdetneng.rapgeneratorai.domain.model.local.NetworkResponse
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.RapRequest
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.freestyle.Rap
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRapUseCase @Inject constructor(private val repo : UberduckRepositoryImpl) {
    operator fun invoke(request : RapRequest) : Flow<NetworkResponse<Rap>> {
        return repo.postRap(request)
    }
}