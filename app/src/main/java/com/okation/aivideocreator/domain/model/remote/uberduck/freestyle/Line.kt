package com.okation.aivideocreator.domain.model.remote.uberduck.freestyle

data class Line(
    val end: Int,
    val start: Int,
    val text: String,
    val words: List<Word>
)