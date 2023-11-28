package com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.rapper

data class Voice(
    val accent: String,
    val added_at: Int,
    val age: String,
    val architecture: String,
    val category: String,
    val contributors: List<String>,
    val controls: Boolean,
    val description: String,
    val display_name: String,
    val gender: String,
    val hifi_gan_vocoder: String,
    val image_url: String,
    val is_active: Boolean,
    val is_primary: Boolean,
    val is_private: Boolean,
    val language: String,
    val memberships: List<Membership>,
    val ml_model_id: Int,
    val mood: String,
    val name: String,
    val speaker_id: Int,
    val style: String,
    val symbol_set: String,
    val voicemodel_uuid: String
)