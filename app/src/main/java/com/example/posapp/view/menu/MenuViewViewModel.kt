package com.example.posapp.view.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posapp.data.ProductEntity
import com.example.posapp.data.toCheckOutModel
import com.example.posapp.repository.CheckoutRepository
import com.example.posapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewViewModel @Inject constructor(
    private val repository: Repository,
    private val checkoutRepository: CheckoutRepository
) : ViewModel() {
    val productState = repository.showAllProductData()

    fun addProduct(data: ProductEntity) = viewModelScope.launch {
        println("Added Product")
        checkoutRepository.insertCheckout(
            data.toCheckOutModel()
        )
    }
}