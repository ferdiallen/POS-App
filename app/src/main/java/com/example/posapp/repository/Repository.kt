package com.example.posapp.repository

import com.example.posapp.data.ProductEntity
import com.example.posapp.data.dao.ProductDao
import javax.inject.Inject

class Repository @Inject constructor(
    private val productDao: ProductDao
) {
    fun showAllProductData() = productDao.showAllSavedTransaction()
    fun kategoriProduct(kategori:String) = productDao.kategoriProduct(kategori)
    fun limitRekomendasiProduct() = productDao.limitRekomendasiProduct()


    suspend fun insertProduct(productEntity: ProductEntity) = productDao.saveNewProduct(productEntity)
}