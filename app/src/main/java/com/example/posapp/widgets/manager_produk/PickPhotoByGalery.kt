package com.example.posapp.widgets.manager_produk

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posapp.R
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PickRoundedPhoto(
    uriImage:MutableState<Uri?>
) {
    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>( null)
    }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(),
        onResult = {
                uri: Uri? ->
            uriImage.value = uri
        } )

    uriImage.value?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images
                .Media.getBitmap(context.contentResolver,it)
        } else {
            val source = ImageDecoder
                .createSource(context.contentResolver,it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
        }

        val file = File(getRealPathFromURI(it,context))
        if (file.exists()) {
            Log.d("FILE EXIST BOSS",file.toString())
        } else {
            Log.d("GAK EKSIS","GAK BLAS")
        }
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentWidth(CenterHorizontally)
        .padding(start = 18.dp, end = 18.dp)) {
        Surface(
            color = Color.Transparent,
            shape = RoundedCornerShape(6.dp),
            onClick = {
                launcher.launch("image/*")
            }
        ) {
            Column {
                Surface(
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(6.dp)
                        .size(80.dp)
                ) {
                    if (bitmap.value == null) {
                        Image(painter = painterResource(id = R.drawable.profile_male ),
                            contentDescription = null,
                            contentScale = ContentScale.Crop)
                    } else {
                        Image(bitmap = bitmap.value!!.asImageBitmap(),
                            contentDescription =  null,
                            contentScale = ContentScale.Crop)
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Ganti Foto Profile",
                    color = Color(0xFF162CA2),
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.body1)
            }
        }
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PickPhotoByGalery(
    uriImage: MutableState<Uri?>
) {
    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>( null)
    }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(),
        onResult = {
                uri: Uri? ->
            uriImage.value = uri
        } )

    uriImage.value?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images
                .Media.getBitmap(context.contentResolver,it)
        } else {
            val source = ImageDecoder
                .createSource(context.contentResolver,it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
        }

        val file = File(getRealPathFromURI(it,context))
        if (file.exists()) {
            Log.d("FILE EXIST BOSS",file.toString())
        } else {
            Log.d("GAK EKSIS","GAK BLAS")
        }
    }

    Surface(
        modifier = Modifier
            .padding(start = 18.dp, end = 18.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(5.dp),
        color = MaterialTheme.colors.background,
        onClick = {
            launcher.launch("image/*")
        }
    ) {
        Row(
            Modifier
                .padding(start = 5.dp, end = 5.dp, top = 8.dp, bottom = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                Modifier
                    .size(55.dp),
                shape = RoundedCornerShape(5.dp)
            ) {
                if (bitmap.value == null) {
                    Image(painter = painterResource(id = R.drawable.image ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop)
                } else {
                    Image(bitmap = bitmap.value!!.asImageBitmap(),
                        contentDescription =  null,
                        contentScale = ContentScale.Crop)
                }

            }

            Surface(
                color = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onSurface,
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(text = "Pilih Foto",
                    style = MaterialTheme.typography.body2,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp, start = 30.dp, end = 30.dp))
            }
        }
    }
}


fun getRealPathFromURI(uri: Uri, context: Context): String? {
    val returnCursor = context.contentResolver.query(uri, null, null, null, null)
    val nameIndex =  returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    val sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE)
    returnCursor.moveToFirst()
    val name = returnCursor.getString(nameIndex)
    val size = returnCursor.getLong(sizeIndex).toString()
    val file = File(context.filesDir, name)
    try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)
        var read = 0
        val maxBufferSize = 1 * 1024 * 1024
        val bytesAvailable: Int = inputStream?.available() ?: 0
        //int bufferSize = 1024;
        val bufferSize = Math.min(bytesAvailable, maxBufferSize)
        val buffers = ByteArray(bufferSize)
        while (inputStream?.read(buffers).also {
                if (it != null) {
                    read = it
                }
            } != -1) {
            outputStream.write(buffers, 0, read)
        }
        Log.e("File Size", "Size " + file.length())
        inputStream?.close()
        outputStream.close()
        Log.e("File Path", "Path " + file.path)

    } catch (e: java.lang.Exception) {
        Log.e("Exception", e.message!!)
    }
    return file.path
}