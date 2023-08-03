package com.example.posapp.data

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val productId: Int? = null,
    val namaProduk: String = "",
    val harga: Int = 0,
    val deskripsi: String = "",
    val kategori: String = "",
    val fotoProduk: Uri?
)