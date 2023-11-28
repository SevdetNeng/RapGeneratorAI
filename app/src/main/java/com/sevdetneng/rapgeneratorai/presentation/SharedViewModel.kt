package com.sevdetneng.rapgeneratorai.presentation

import androidx.lifecycle.ViewModel
import com.sevdetneng.rapgeneratorai.domain.model.local.Rapper
import com.sevdetneng.rapgeneratorai.domain.model.remote.uberduck.Beat

class SharedViewModel : ViewModel() {
    var beat : Beat = Beat()
    var rapper : Rapper = Rapper()
    var lyrics : String = ""
}