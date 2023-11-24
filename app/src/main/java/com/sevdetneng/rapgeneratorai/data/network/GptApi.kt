package com.sevdetneng.rapgeneratorai.data.network

import com.sevdetneng.rapgeneratorai.domain.model.local.GptPostRequest
import com.sevdetneng.rapgeneratorai.domain.model.remote.gpt.GptResponse
import com.sevdetneng.rapgeneratorai.util.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GptApi {
    @Headers("Authorization: Bearer ${Constants.BEARER_TOKEN}")
    @POST("completions")
    fun postPrompt(@Body prompt : GptPostRequest) : Call<GptResponse>
}