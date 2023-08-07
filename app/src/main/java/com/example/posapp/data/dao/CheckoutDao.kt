package com.example.posapp.data.dao

import androidx.room.*
import com.example.posapp.data.CheckoutModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckoutDao {
    @Query("SELECT * FROM tb_checkout")
    fun getCheckout():Flow<List<CheckoutModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCheckout(model:CheckoutModel)

    @Update
    suspend fun updateCheckout(model:CheckoutModel)

    @Delete
    suspend fun deleteCheckout(model: CheckoutModel)
}