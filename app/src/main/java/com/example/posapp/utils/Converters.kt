package com.example.posapp.utils

import android.net.Uri
import androidx.room.TypeConverter
import com.example.posapp.data.ProductEntity
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun convertProductListToJsonString(data: List<ProductEntity>) = Gson().toJson(data)

    @TypeConverter
    fun convertProductJsonToList(data: String) = Gson().fromJson(data, ProductEntity::class.java)

    @TypeConverter
    fun convertUriToJsonString(data: Uri) = Gson().toJson(data)

    @TypeConverter
    fun convertJsonToUri(data:String) = Gson().fromJson(data,Uri::class.java)
}