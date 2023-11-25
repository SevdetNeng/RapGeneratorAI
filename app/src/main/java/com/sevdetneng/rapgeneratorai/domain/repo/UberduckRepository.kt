package com.sevdetneng.rapgeneratorai.domain.repo

import com.sevdetneng.rapgeneratorai.domain.model.local.NetworkResponse
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.Beats
import kotlinx.coroutines.flow.Flow

interface UberduckRepository {
    fun getBeats() : Flow<NetworkResponse<Beats>>
}