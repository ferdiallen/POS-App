package com.example.posapp.view.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posapp.data.ProductEntity
import com.example.posapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val repo:Repository):ViewModel() {
    private val _uiState = MutableStateFlow<List<ProductEntity>>(emptyList())
    val uiState = _uiState.asStateFlow()

    fun getMenu(kategori:String) = viewModelScope.launch {
        repo.kategoriProduct(kategori).collect {
            list ->
            _uiState.value = list
        }
    }
}