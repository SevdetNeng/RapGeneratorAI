package com.sevdetneng.rapgeneratorai.data.network

import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.Beats
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.RapRequest
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.freestyle.Rap
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.rapper.Voices
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.voicedetail.Sample
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.voicedetail.Samples
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.voicedetail.VoiceDetail
import com.sevdetneng.rapgeneratorai.util.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UberduckApi {
    @GET("reference-audio/backing-tracks")
    fun getBeats(@Header("Authorization") token : String = "Basic ${Constants.UBERDUCK_TOKEN}",@Query("detailed") detailed : Boolean = true) : Call<Beats>

    @GET("voices")
    fun getVoices(@Header("Authorization") token : String = "Basic ${Constants.UBERDUCK_TOKEN}",
                  @Query("mode") mode : String = "tts-all", @Query("slim") slim : Boolean = false) : Call<Voices>

    @GET("voices/{uuid}/samples")
    fun getVoiceDetail(@Header("Authorization") token : String = "Basic ${Constants.UBERDUCK_TOKEN}",
                       @Path("uuid") uuid : String) : Call<Samples>

    @POST("tts/freestyle")
    fun postFreestyle(@Header("Authorization") token : String = "Basic ${Constants.UBERDUCK_TOKEN}",
                      @Body request : RapRequest) : Call<Rap>
}