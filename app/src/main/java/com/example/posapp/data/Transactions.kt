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
    val itemTtansaksi:ListItemTransactions,
    val totalHarga: Int,
    val namaKasir: String,
    val kontakKasir: Int
)

data class ListItemTransactions(
    val itemTransaksi:List<ProductEntity>
)