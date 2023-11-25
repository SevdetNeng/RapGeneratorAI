package com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck

data class Beat(
    val bpm: Int,
    val is_public: Boolean,
    val name: String,
    val source: String,
    val timeline_origin: Int,
    val url: String,
    val uuid: String,
    val verses: List<Verse>
)