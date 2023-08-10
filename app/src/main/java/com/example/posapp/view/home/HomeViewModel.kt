package com.example.posapp.view.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posapp.data.LoginModel
import com.example.posapp.data.ProductEntity
import com.example.posapp.repository.Repository
import com.example.posapp.utils.FirebaseSignIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firebaseSignIn: FirebaseSignIn,
    private val repository: Repository
) : ViewModel() {
    var currentUserData by mutableStateOf<LoginModel?>(null)
        private set

    private val _uiState = MutableStateFlow<List<ProductEntity>>(emptyList())
    val uiState = _uiState.asStateFlow()

    fun getRekomendasi() = viewModelScope.launch {
        repository.limitRekomendasiProduct().collect {
            item ->
            if (item.isNotEmpty())
                _uiState.value = item
        }
    }

    init {
        currentUserData = firebaseSignIn.getSignInUser()
        getRekomendasi()
    }

}