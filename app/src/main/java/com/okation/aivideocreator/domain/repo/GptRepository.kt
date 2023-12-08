package com.okation.aivideocreator.domain.repo

import com.okation.aivideocreator.domain.model.local.NetworkResponse
import com.okation.aivideocreator.domain.model.remote.gpt.GptResponse
import kotlinx.coroutines.flow.Flow

interface GptRepository  {

    fun postPrompt(prompt : String) : Flow<NetworkResponse<GptResponse>>
}