package com.okation.aivideocreator.domain.repo

import com.okation.aivideocreator.domain.model.local.NetworkResponse
import com.okation.aivideocreator.domain.model.remote.uberduck.Beats
import com.okation.aivideocreator.domain.model.remote.uberduck.RapRequest
import com.okation.aivideocreator.domain.model.remote.uberduck.freestyle.Rap
import com.okation.aivideocreator.domain.model.remote.uberduck.rapper.Voices
import com.okation.aivideocreator.domain.model.remote.uberduck.voicedetail.Samples
import kotlinx.coroutines.flow.Flow

interface UberduckRepository {
    fun getBeats() : Flow<NetworkResponse<Beats>>

    fun getVoices() : Flow<NetworkResponse<Voices>>

    fun getVoiceSample(uuid : String) : Flow<NetworkResponse<Samples>>

    fun postRap(request : RapRequest) : Flow<NetworkResponse<Rap>>
}