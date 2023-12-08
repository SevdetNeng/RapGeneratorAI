package com.okation.aivideocreator.presentation.song

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okation.aivideocreator.data.usecase.UpsertSongUseCase
import com.okation.aivideocreator.domain.model.local.SongEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(private val upsertSongUseCase: UpsertSongUseCase) : ViewModel() {

    private val _isPlaying : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isPlaying : StateFlow<Boolean> = _isPlaying

    fun setIsPlaying(isPlaying : Boolean){
        _isPlaying.value = isPlaying
    }

    fun upsertSong(song : SongEntity){
        viewModelScope.launch {
            upsertSongUseCase(song)
        }
    }
}