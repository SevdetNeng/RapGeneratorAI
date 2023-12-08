package com.okation.aivideocreator.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okation.aivideocreator.data.usecase.DeleteSongUseCase
import com.okation.aivideocreator.data.usecase.GetAllSongsUseCase
import com.okation.aivideocreator.domain.model.local.SongEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getAllSongsUseCase : GetAllSongsUseCase,
    private val deleteSongUseCase: DeleteSongUseCase) : ViewModel() {

    private val _favoriteSongs : MutableStateFlow<List<SongEntity>> = MutableStateFlow(emptyList())
    val favoriteSongs : StateFlow<List<SongEntity>> = _favoriteSongs

    private val _isPlayingSong : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isPlayingSong : StateFlow<Boolean> = _isPlayingSong

    private val _playingSong : MutableStateFlow<SongEntity?> = MutableStateFlow(null)
    val playingSong : StateFlow<SongEntity?> = _playingSong

    init {
        getAllSongs()
    }
    private fun getAllSongs(){
        viewModelScope.launch {
            getAllSongsUseCase.invoke().collect(){
                _favoriteSongs.value = it
            }
        }

    }

    fun deleteSong(song : SongEntity){
        viewModelScope.launch {
            deleteSongUseCase.invoke(song)
        }
    }

    fun setIsPlaying(isPlaying : Boolean){
        _isPlayingSong.value = isPlaying
    }

    fun setPlayingSong(song : SongEntity?){
        _playingSong.value = song
    }


}