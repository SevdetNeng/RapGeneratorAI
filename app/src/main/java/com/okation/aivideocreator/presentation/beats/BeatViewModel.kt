package com.okation.aivideocreator.presentation.beats

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okation.aivideocreator.data.usecase.GetBeatsUseCase
import com.okation.aivideocreator.domain.model.local.ResponseStatus
import com.okation.aivideocreator.domain.model.remote.uberduck.Beats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeatViewModel @Inject constructor(
    private val getBeatsUseCase: GetBeatsUseCase
) : ViewModel() {

    private val _isLoading : MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading : StateFlow<Boolean> = _isLoading

    private val _beats : MutableStateFlow<Beats> = MutableStateFlow(Beats())
    val beats : StateFlow<Beats> = _beats

    private val _isPlaying : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isPlaying : StateFlow<Boolean> = _isPlaying

    private val _playingBeatIndex : MutableStateFlow<Int> = MutableStateFlow(-1)
    val playingBeatIndex : StateFlow<Int> = _playingBeatIndex

    init {
        getBeats()
    }
    fun getBeats(){
        viewModelScope.launch {
            getBeatsUseCase.invoke().collect(){ response ->
                when(response.status){
                    ResponseStatus.SUCCESS -> {
                        response.data.let {
                            _beats.value = it!!
                            println(_beats.value.backing_tracks.toString())
                        }
                        _isLoading.value = false
                    }
                    ResponseStatus.LOADING -> {
                        _isLoading.value = true
                    }
                    else -> {
                        Log.d("Uberduck Exc",response.message.toString())
                    }
                }
            }
        }
    }

    fun setIsPlaying(isPlaying : Boolean) {
        _isPlaying.value = isPlaying
    }

    fun setPlayingIndex(index : Int){
        _playingBeatIndex.value = index
    }
}