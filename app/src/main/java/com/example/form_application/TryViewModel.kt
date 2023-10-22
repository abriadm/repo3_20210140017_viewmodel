package com.example.form_application

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.form_application.Data.DataForm
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TryViewModel: ViewModel() {
    var namaUsr: String by mutableStateOf("")
        private set
    var noTlp: String by mutableStateOf("")
        private set
    var jenisKl: String by mutableStateOf("")
        private set
    private val _uiState = mutableStateOf(DataForm())
    val uiState: StateFlow<DataForm> = _uiState.asStateFlow()

    fun BacaData(nm: String, tlp: String, jk:String) {
        namaUsr=nm;
        noTlp=tlp;
        jenisKl=jk;
    }

    fun setJenisKl(pilihJK:String) {
        _uiState.update{currentState -> currentState.copy(gender = pilihJK)}
    }
}