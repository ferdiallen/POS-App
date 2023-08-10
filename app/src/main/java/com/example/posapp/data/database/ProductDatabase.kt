package com.example.posapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.posapp.data.CheckoutModel
import com.example.posapp.data.EnterTransaction
import com.example.posapp.data.ExitTransaction
import com.example.posapp.data.ProductEntity
import com.example.posapp.data.dao.CheckoutDao
import com.example.posapp.data.dao.ProductDao
import com.example.posapp.data.dao.TransactionDao
import com.example.posapp.utils.Converters

@Database(entities = [ProductEntity::class,CheckoutModel::class,ExitTransaction::class],
    version = 1)
@TypeConverters(value = [Converters::class])
abstract class ProductDatabase() : RoomDatabase() {
    abstract fun dao(): ProductDao
    abstract fun checkoutDao():CheckoutDao

    abstract fun transactionDao(): TransactionDao
}