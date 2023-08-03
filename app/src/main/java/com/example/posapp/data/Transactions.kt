package com.example.posapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EnterTransaction(
    @PrimaryKey(autoGenerate = true)
    val idTransaksi: Int? = null,
    val waktuTransaksi: String,
    val tanggalTransaksi: String,
    val namaTransaksi: String,
    val deskripsiTransaksi: String,
    val totalHarga: Int,
    val namaKasir: String,
    val kontakKasir: Int
)

@Entity
data class ExitTransaction(
    @PrimaryKey(autoGenerate = true)
    val idTransaksi: Int? = null,
    val waktuTransaksi: String,
    val tanggalTransaksi: String,
    val itemTransaksi:List<ProductEntity>,
    val totalHarga: Int,
    val namaKasir: String,
    val kontakKasir: Int
)
