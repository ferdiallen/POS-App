package com.example.posapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posapp.data.CheckoutModel
import com.example.posapp.repository.CheckoutRepository
import com.example.posapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val repo: CheckoutRepository,
    private val repository: Repository
) : ViewModel() {
    val uiState =  repo.getCheckout()

    fun insertCheckout(model: CheckoutModel) =
        viewModelScope.launch {
            repo.insertCheckout(model)
        }


}