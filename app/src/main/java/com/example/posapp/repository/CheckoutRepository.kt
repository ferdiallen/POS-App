package com.example.posapp.repository

import com.example.posapp.data.CheckoutModel
import com.example.posapp.data.dao.CheckoutDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckoutRepository @Inject constructor(private val dao:CheckoutDao) {
    fun getCheckout():Flow<List<CheckoutModel>> =dao.getCheckout()
    suspend fun insertCheckout(model:CheckoutModel) = dao.insertCheckout(model)
    suspend fun updateCheckout(model:CheckoutModel) = dao.updateCheckout(model)
    suspend fun deleteCheckout(model:CheckoutModel) = dao.deleteCheckout(model)
}