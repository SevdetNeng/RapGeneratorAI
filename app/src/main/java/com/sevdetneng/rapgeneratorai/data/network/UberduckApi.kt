package com.sevdetneng.rapgeneratorai.data.network

import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.Beats
import com.sevdetneng.rapgeneratorai.util.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UberduckApi {
    @GET("reference-audio/backing-tracks")
    fun getBeats(@Header("Authorization") token : String = "Basic ${Constants.UBERDUCK_TOKEN}",@Query("detailed") detailed : Boolean = true) : Call<Beats>
}