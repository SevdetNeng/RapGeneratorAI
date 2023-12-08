package com.okation.aivideocreator.presentation.rapper

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RapperViewModel @Inject constructor(
) : ViewModel() {

    private val _isPlaying : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isPlaying : StateFlow<Boolean> = _isPlaying

    private val _playingIndex : MutableStateFlow<Int> = MutableStateFlow(-1)
    val playingIndex : StateFlow<Int> = _playingIndex

    fun setIsPlaying(isPlaying : Boolean){
        _isPlaying.value = isPlaying
    }
    fun setPlayingIndex(index : Int){
        _playingIndex.value = index
    }

}