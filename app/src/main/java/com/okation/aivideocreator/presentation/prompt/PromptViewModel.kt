package com.okation.aivideocreator.presentation.prompt

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PromptViewModel : ViewModel() {
    private val _selectedCategory : MutableStateFlow<String> = MutableStateFlow("Fun")
    val selectedCategory : StateFlow<String> = _selectedCategory

    private val _isPromptEntered : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isPromptEntered : StateFlow<Boolean> = _isPromptEntered

    fun selectCategory(title : String){
        _selectedCategory.value = title
    }
    fun setPromptEntered(isEntered : Boolean){
        _isPromptEntered.value = isEntered
    }

}