package com.example.posapp.viewmodels

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posapp.data.CheckoutModel
import com.example.posapp.repository.CheckoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val repo:CheckoutRepository):ViewModel() {
    private val _uiState = MutableStateFlow<List<CheckoutModel>>(emptyList())
    val uiState = _uiState.asStateFlow()

    fun getCheckout() =
        viewModelScope.launch {
            repo.getCheckout().collect {
                item ->
                if (item.isNotEmpty())
                    _uiState.value = item
                else
                    _uiState.value = emptyList()
            }
        }
init {
    getCheckout()
}

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
    fun insertCheckout(model:CheckoutModel) =
        viewModelScope.launch {
            repo.insertCheckout(model)
        }


}