package com.okation.aivideocreator.data

import com.okation.aivideocreator.data.network.GptApi
import com.okation.aivideocreator.domain.model.local.GptPostRequest
import com.okation.aivideocreator.domain.model.local.NetworkResponse
import com.okation.aivideocreator.domain.model.remote.gpt.GptResponse
import com.okation.aivideocreator.domain.repo.GptRepository
import com.okation.aivideocreator.util.Constants
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GptRepositoryImpl @Inject constructor(private val gptApi : GptApi) : GptRepository {
    override fun postPrompt(prompt: String): Flow<NetworkResponse<GptResponse>> {

        return callbackFlow {
            val request = GptPostRequest(
                Constants.GPT_MODEL,
                prompt = "a rap song" + prompt,
                max_tokens = Constants.MAX_TOKENS,
            )
            val response = gptApi.postPrompt(request)
            trySend(NetworkResponse.Loading)
            response.enqueue(object : Callback<GptResponse> {
                override fun onResponse(call: Call<GptResponse>, response: Response<GptResponse>) {
                    if(response.isSuccessful){
                        val body = response.body()
                        body?.let {
                            trySend(NetworkResponse.Success(body))
                        }
                    }else{
                        trySend(NetworkResponse.Error("Response Failure"))
                    }
                }

                override fun onFailure(call: Call<GptResponse>, t: Throwable) {
                    trySend(NetworkResponse.Error(t.message.toString()))
                }

            })
            awaitClose()
        }
    }
}