package com.sevdetneng.rapgeneratorai.domain.repo

import com.sevdetneng.rapgeneratorai.domain.model.local.NetworkResponse
import com.sevdetneng.rapgeneratorai.domain.model.remote.gpt.GptResponse
import kotlinx.coroutines.flow.Flow

interface GptRepository  {

    fun postPrompt(prompt : String) : Flow<NetworkResponse<GptResponse>>
}