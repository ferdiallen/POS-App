package com.example.posapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posapp.data.CheckoutModel
import com.example.posapp.repository.CheckoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val repo:CheckoutRepository):ViewModel() {
    private val _uiState = MutableStateFlow<List<CheckoutModel>>(emptyList())
    val uiState = _uiState.asStateFlow()

    fun getCheckout() =
        viewModelScope.launch {
            repo.getCheckout().collect {
                item ->
                if (item.isNotEmpty())
                    _uiState.value = item
                else
                    _uiState.value = emptyList()
            }
        }
init {
    getCheckout()
}
    fun insertCheckout(model:CheckoutModel) =
        viewModelScope.launch {
            repo.insertCheckout(model)
        }


}