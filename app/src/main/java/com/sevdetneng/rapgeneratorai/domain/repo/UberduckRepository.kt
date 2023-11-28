package com.sevdetneng.rapgeneratorai.domain.repo

import com.sevdetneng.rapgeneratorai.domain.model.local.NetworkResponse
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.Beats
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.RapRequest
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.freestyle.Rap
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.rapper.Voices
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.voicedetail.Sample
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.voicedetail.Samples
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.voicedetail.VoiceDetail
import kotlinx.coroutines.flow.Flow

interface UberduckRepository {
    fun getBeats() : Flow<NetworkResponse<Beats>>

    fun getVoices() : Flow<NetworkResponse<Voices>>

    fun getVoiceSample(uuid : String) : Flow<NetworkResponse<Samples>>

    fun postRap(request : RapRequest) : Flow<NetworkResponse<Rap>>
}