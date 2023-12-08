package com.okation.aivideocreator.presentation

import androidx.lifecycle.ViewModel
import com.okation.aivideocreator.domain.model.local.Rapper
import com.okation.aivideocreator.domain.model.remote.uberduck.Beat

class SharedViewModel : ViewModel() {
    var beat : Beat = Beat()
    var rapper : Rapper = Rapper()
    var lyrics : String = ""
}