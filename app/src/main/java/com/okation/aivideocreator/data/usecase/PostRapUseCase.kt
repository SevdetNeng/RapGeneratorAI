package com.okation.aivideocreator.data.usecase

import com.okation.aivideocreator.data.UberduckRepositoryImpl
import com.okation.aivideocreator.domain.model.local.NetworkResponse
import com.okation.aivideocreator.domain.model.remote.uberduck.RapRequest
import com.okation.aivideocreator.domain.model.remote.uberduck.freestyle.Rap
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRapUseCase @Inject constructor(private val repo : UberduckRepositoryImpl) {
    operator fun invoke(request : RapRequest) : Flow<NetworkResponse<Rap>> {
        return repo.postRap(request)
    }
}