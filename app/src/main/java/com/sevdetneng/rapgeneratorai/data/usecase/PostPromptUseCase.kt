package com.sevdetneng.rapgeneratorai.data.usecase

import com.sevdetneng.rapgeneratorai.data.GptRepositoryImpl
import com.sevdetneng.rapgeneratorai.domain.model.local.NetworkResponse
import com.sevdetneng.rapgeneratorai.domain.model.remote.gpt.GptResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostPromptUseCase @Inject constructor(private val repo : GptRepositoryImpl)  {
    operator fun invoke(prompt : String): Flow<NetworkResponse<GptResponse>>{
        return repo.postPrompt(prompt)
    }
}