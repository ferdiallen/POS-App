package com.example.posapp.view.kasir

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posapp.utils.KasirPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class KasirViewModel @Inject constructor(
    private val preferences: KasirPreferences
) : ViewModel() {
    var namaKasir by mutableStateOf("")
        private set
    var noTelp by mutableStateOf("")
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            preferences.getKasirValue()?.let {
                withContext(Dispatchers.Main){
                    namaKasir = it.first
                    noTelp = it.second.toString()
                }
            }
        }
    }

    fun setKasirProfile(nama: String) {
        namaKasir = nama
    }

    fun setKasirNomor(nomor: String) {
        noTelp = nomor
    }

    fun saveKasir() = viewModelScope.launch(Dispatchers.IO) {
        if(namaKasir.isNotEmpty() && noTelp.isNotEmpty()){
            preferences.saveKasir(namaKasir, noTelp.toIntOrNull() ?: 0)
        }
    }

}