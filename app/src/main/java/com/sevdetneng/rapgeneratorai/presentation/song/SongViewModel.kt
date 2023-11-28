package com.sevdetneng.rapgeneratorai.presentation.song

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SongViewModel : ViewModel() {

    private val _isPlaying : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isPlaying : StateFlow<Boolean> = _isPlaying

    fun setIsPlaying(isPlaying : Boolean){
        _isPlaying.value = isPlaying
    }
}