package com.okation.aivideocreator.domain.model.remote.gpt

data class GptResponse(
    val choices: List<Choice>,
    val created: Int,
    val id: String,
    val model: String,
    val `object`: String,
    val usage: Usage
)
