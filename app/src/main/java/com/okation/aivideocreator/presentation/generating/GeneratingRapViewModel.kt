package com.okation.aivideocreator.presentation.generating

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okation.aivideocreator.data.usecase.PostRapUseCase
import com.okation.aivideocreator.domain.model.local.ResponseStatus
import com.okation.aivideocreator.domain.model.remote.uberduck.RapRequest
import com.okation.aivideocreator.domain.model.remote.uberduck.freestyle.Rap
import com.okation.aivideocreator.util.Extensions.getVerses
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneratingRapViewModel @Inject constructor(
    private val postRapUseCase: PostRapUseCase
) : ViewModel() {

    private val _generatedSong : MutableStateFlow<Rap> = MutableStateFlow(Rap())
    val generatedSong : StateFlow<Rap> = _generatedSong

    private val _isLoading : MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading : StateFlow<Boolean> = _isLoading
    fun generateRap(voiceModel : String, backingTrack : String,lyrics : String){
        val formattedLyrics = lyrics.getVerses()
        val request = RapRequest(
            voicemodel_uuid = voiceModel,
            backing_track = backingTrack,
            lyrics = formattedLyrics
        )
        viewModelScope.launch {
            postRapUseCase.invoke(request).collect(){ response ->
                when(response.status){
                    ResponseStatus.SUCCESS -> {
                        println(response.data)
                        response.data?.let {
                            _generatedSong.value = it
                            _isLoading.value = false
                        }
                    }
                    ResponseStatus.LOADING -> {
                        _isLoading.value = true
                    }
                    else -> {
                        Log.d("Rap Exc",response.message.toString())
                    }
                }
            }
        }
    }
}