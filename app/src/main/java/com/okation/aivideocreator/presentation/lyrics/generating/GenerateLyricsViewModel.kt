package com.okation.aivideocreator.presentation.lyrics.generating

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okation.aivideocreator.data.usecase.PostPromptUseCase
import com.okation.aivideocreator.domain.model.local.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateLyricsViewModel @Inject constructor(private val postPromptUseCase : PostPromptUseCase): ViewModel() {

    private val _gptCompletion : MutableStateFlow<String> = MutableStateFlow("")
    val gptCompletion : StateFlow<String> = _gptCompletion

    private val _isLoading : MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading : StateFlow<Boolean> = _isLoading

    private val _isError : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isError : StateFlow<Boolean> = _isError

    fun postPrompt(prompt : String){
        _isError.value = false
        viewModelScope.launch {
            postPromptUseCase(prompt).collect(){ response ->
                when(response.status){
                    ResponseStatus.LOADING -> {
                        _isLoading.value = true
                    }
                    ResponseStatus.SUCCESS -> {
                        if(response.data!=null){
                            _gptCompletion.value = response.data.choices[0].text
                            println(_gptCompletion.value.trim())
                        }
                        _isLoading.value = false
                    }
                    else -> {
                        Log.d("Gpt Exc",response.message.toString())
                        _isError.value = true
                        _isLoading.value = false
                    }
                }
            }
        }

    }


}