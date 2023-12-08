package com.okation.aivideocreator.data

import com.okation.aivideocreator.data.network.UberduckApi
import com.okation.aivideocreator.domain.model.local.NetworkResponse
import com.okation.aivideocreator.domain.model.remote.uberduck.Beats
import com.okation.aivideocreator.domain.model.remote.uberduck.RapRequest
import com.okation.aivideocreator.domain.model.remote.uberduck.freestyle.Rap
import com.okation.aivideocreator.domain.model.remote.uberduck.rapper.Voices
import com.okation.aivideocreator.domain.model.remote.uberduck.voicedetail.Samples
import com.okation.aivideocreator.domain.repo.UberduckRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UberduckRepositoryImpl @Inject constructor(val api : UberduckApi) : UberduckRepository {
    override fun getBeats(): Flow<NetworkResponse<Beats>> {
        return callbackFlow {
            val response = api.getBeats()
            trySend(NetworkResponse.Loading)
            response.enqueue(object : Callback<Beats>{
                override fun onResponse(call: Call<Beats>, response: Response<Beats>) {
                    if(response.isSuccessful){
                        val body = response.body()
                        body?.let {
                            trySend(NetworkResponse.Success(it))
                        }
                    }
                    else {
                        trySend(NetworkResponse.Error("Uberduck Exc"))
                    }
                }
                override fun onFailure(call: Call<Beats>, t: Throwable) {
                    trySend(NetworkResponse.Error(t.message.toString()))
                }
            })
            awaitClose()
        }
    }

    override fun getVoices(): Flow<NetworkResponse<Voices>> {
        return callbackFlow {
            val response = api.getVoices()
            trySend(NetworkResponse.Loading)
            response.enqueue(object : Callback<Voices>{
                override fun onResponse(call: Call<Voices>, response: Response<Voices>) {
                    if(response.isSuccessful){
                        val body = response.body()
                        println("body is $body")
                        body?.let {
                            trySend(NetworkResponse.Success(it))
                        }
                    }else{
                        trySend(NetworkResponse.Error("Voices Exc"))
                    }
                }

                override fun onFailure(call: Call<Voices>, t: Throwable) {
                    trySend(NetworkResponse.Error(t.message.toString()))
                }

            })
            awaitClose()
        }
    }

    override fun getVoiceSample(uuid : String): Flow<NetworkResponse<Samples>> {
        return callbackFlow {
            val response = api.getVoiceDetail(uuid = uuid)
            trySend(NetworkResponse.Loading)
            response.enqueue(object : Callback<Samples>{
                override fun onResponse(call: Call<Samples>, response: Response<Samples>) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            trySend(NetworkResponse.Success(it))
                        }
                    }else{
                        trySend(NetworkResponse.Error("Sample Exc"))
                    }
                }
                override fun onFailure(call: Call<Samples>, t: Throwable) {
                    trySend(NetworkResponse.Error(t.message.toString()))
                }
            })
            awaitClose()
        }


    }

    override fun postRap(request: RapRequest): Flow<NetworkResponse<Rap>> {
        return callbackFlow {
            val response = api.postFreestyle(request = request)
            trySend(NetworkResponse.Loading)
            response.enqueue(object : Callback<Rap>{
                override fun onResponse(call: Call<Rap>, response: Response<Rap>) {
                    if(response.isSuccessful){
                        val body = response.body()
                        body?.let {
                            trySend(NetworkResponse.Success(it))
                        }
                    } else{
                        trySend(NetworkResponse.Error("Rap Exc"))
                    }
                }
                override fun onFailure(call: Call<Rap>, t: Throwable) {
                    trySend(NetworkResponse.Error(t.message.toString()))
                }
            })
            awaitClose()
        }


    }
}