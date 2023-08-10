package com.example.posapp.repository

import com.example.posapp.data.ExitTransaction
import com.example.posapp.data.ProductEntity
import com.example.posapp.data.dao.ProductDao
import com.example.posapp.data.dao.TransactionDao
import javax.inject.Inject

class Repository @Inject constructor(
    private val productDao: ProductDao,
    private val transactionDao: TransactionDao
) {
    fun showAllProductData() = productDao.showAllSavedTransaction()

    suspend fun insertProduct(productEntity: ProductEntity) = productDao.saveNewProduct(productEntity)

    suspend fun insertTransaction(data:ExitTransaction) = transactionDao.insertTransaction(data)

    fun getSavedProduct() = transactionDao.getAllEnterTransactions()

    suspend fun deleteTransaction(data:ProductEntity) = data.productId?.let { transactionDao.deleteTransaction(it) }
}