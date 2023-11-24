package com.sevdetneng.rapgeneratorai.domain.model.local

data class GptPostRequest(
    val model: String,
    val prompt: String,
    val max_tokens: Int,
    val temperature: Float = 0F,
)