package com.example.posapp.data.dao

import androidx.room.Dao
import com.example.posapp.data.EnterTransaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    fun getAllEnterTransactions() : Flow<List<EnterTransaction>>
}