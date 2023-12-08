package com.okation.aivideocreator.data.usecase

import com.okation.aivideocreator.data.GptRepositoryImpl
import com.okation.aivideocreator.domain.model.local.NetworkResponse
import com.okation.aivideocreator.domain.model.remote.gpt.GptResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostPromptUseCase @Inject constructor(private val repo : GptRepositoryImpl)  {
    operator fun invoke(prompt : String): Flow<NetworkResponse<GptResponse>>{
        return repo.postPrompt(prompt)
    }
}