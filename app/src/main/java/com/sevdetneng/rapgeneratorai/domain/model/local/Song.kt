package com.sevdetneng.rapgeneratorai.domain.model.local

import java.io.Serializable

data class Song(
    val songUrl : String,
    val rapper : Rapper,
    val songTitle : String
) : Serializable
