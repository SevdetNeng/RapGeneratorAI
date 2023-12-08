package com.okation.aivideocreator.domain.model.remote.gpt

data class Choice(
    val finish_reason: String,
    val index: Int,
    val text: String
)
