package com.okation.aivideocreator.domain.model.remote.uberduck

data class Beat(
    val bpm: Int? = null,
    val is_public: Boolean? = null,
    val name: String? = null,
    val source: String? = null,
    val timeline_origin: Int? = null,
    val url: String? = null,
    val uuid: String? = null,
    val verses: List<Verse>? = null
)