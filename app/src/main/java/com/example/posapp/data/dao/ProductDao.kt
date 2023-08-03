package com.example.posapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.posapp.data.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM ProductEntity")
    fun showAllSavedTransaction(): Flow<List<ProductEntity>>

    @Insert
    suspend fun saveNewProduct(data: ProductEntity)

    @Delete
    suspend fun deleteProduct(data: ProductEntity)
}