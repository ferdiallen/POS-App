package com.example.posapp.view.add_menu_produk

import androidx.lifecycle.ViewModel
import com.example.posapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddMenuProductViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val productState = repository.showAllProductData()
}