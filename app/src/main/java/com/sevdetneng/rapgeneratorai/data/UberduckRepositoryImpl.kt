package com.sevdetneng.rapgeneratorai.data

import com.sevdetneng.rapgeneratorai.data.network.UberduckApi
import com.sevdetneng.rapgeneratorai.domain.model.local.NetworkResponse
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.Beats
import com.sevdetneng.rapgeneratorai.domain.repo.UberduckRepository
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
}