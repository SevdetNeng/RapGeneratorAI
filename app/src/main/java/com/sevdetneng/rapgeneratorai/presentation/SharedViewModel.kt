package com.sevdetneng.rapgeneratorai.presentation

import androidx.lifecycle.ViewModel
import com.sevdetneng.rapgeneratorai.domain.model.local.Beat

class SharedViewModel : ViewModel() {
    val beat : Beat = Beat()
}