package com.okation.aivideocreator.domain.model.remote.uberduck

data class RapRequest(
    val lyrics: List<List<String>>,
    val backing_track: String,
    val voicemodel_uuid: String
)