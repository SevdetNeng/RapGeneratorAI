package com.okation.aivideocreator.domain.model.remote.gpt

data class Usage(
    val completion_tokens: Int,
    val prompt_tokens: Int,
    val total_tokens: Int
)
