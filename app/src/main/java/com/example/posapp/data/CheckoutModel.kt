package com.example.posapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_checkout")
data class CheckoutModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val image: String = "",
    val price: Int = 0,
    val tipe: String
)

fun ProductEntity.toCheckOutModel(): CheckoutModel {
    return CheckoutModel(
        name = namaProduk, image = this.fotoProduk, price = this.harga,
        tipe = this.kategori
    )
}
