package com.sevdetneng.rapgeneratorai.ui.prompt

import androidx.lifecycle.ViewModel
import com.sevdetneng.rapgeneratorai.model.data.Category
import com.sevdetneng.rapgeneratorai.util.Categories
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class PromptViewModel : ViewModel() {
    val selectedCategory : MutableStateFlow<String> = MutableStateFlow("Fun")
    val isPromptEntered : MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun selectCategory(title : String){
        selectedCategory.value = title
    }

}