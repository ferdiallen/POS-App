package com.example.posapp.view.manage_produk

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posapp.data.ProductEntity
import com.example.posapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun saveImageToStorage(context: Context, data: Uri): String {
        val path = context.getExternalFilesDir("product_photo")
        val productPhotoName = "product_photo_${Random.nextInt(1000)}.png"
        val bitmapData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val res = ImageDecoder.createSource(context.contentResolver, data)
            ImageDecoder.decodeBitmap(res)
        } else {
            MediaStore.Images.Media.getBitmap(context.contentResolver, data)
        }
        val fileToSave = File(path, productPhotoName)
        val fos = FileOutputStream(fileToSave)
        fos.use {
            bitmapData.compress(Bitmap.CompressFormat.PNG, 90, fos)
        }
        return "$path/$productPhotoName"
    }

    fun saveProduct(product: ProductEntity) = viewModelScope.launch {
        repository.insertProduct(product)
    }
}