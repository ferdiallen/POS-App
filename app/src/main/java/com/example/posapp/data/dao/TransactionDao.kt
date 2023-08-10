package com.example.posapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.posapp.data.EnterTransaction
import com.example.posapp.data.ExitTransaction
import com.example.posapp.data.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query("SELECT * FROM ExitTransaction")
    fun getAllEnterTransactions() : Flow<ExitTransaction>

    @Insert
    suspend fun insertTransaction(data:ExitTransaction)

    @Query("DELETE FROM ExitTransaction where idTransaksi =:id")
    suspend fun deleteTransaction(id:Int)

}