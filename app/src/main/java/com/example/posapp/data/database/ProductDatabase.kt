package com.example.posapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.posapp.data.ProductEntity
import com.example.posapp.data.dao.ProductDao
import com.example.posapp.utils.Converters

@TypeConverters(value = [Converters::class])
@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductDatabase() : RoomDatabase() {
    abstract fun dao(): ProductDao
}