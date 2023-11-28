package com.sevdetneng.rapgeneratorai.presentation.rapper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevdetneng.rapgeneratorai.data.usecase.GetSampleUseCase
import com.sevdetneng.rapgeneratorai.data.usecase.GetVoicesUseCase
import com.sevdetneng.rapgeneratorai.domain.model.local.ResponseStatus
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.rapper.Voice
import com.sevdetneng.rapgeneratorai.domain.model.local.Rapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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