package com.example.posapp

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.posapp.ui.theme.POSAppTheme
import com.example.posapp.view.MainView
import com.example.posapp.view.home.HomeView
import com.example.posapp.viewmodels.CheckoutViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.mazenrashed.printooth.Printooth
import com.mazenrashed.printooth.data.printable.Printable
import com.mazenrashed.printooth.data.printable.RawPrintable
import com.mazenrashed.printooth.data.printable.TextPrintable
import com.mazenrashed.printooth.data.printer.DefaultPrinter
import com.mazenrashed.printooth.ui.ScanningActivity
import com.mazenrashed.printooth.utilities.Printing
import com.mazenrashed.printooth.utilities.PrintingCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var printing: Printing? = null
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Printooth.hasPairedPrinter()) {
            printing = Printooth.printer()
        }

        setContent {
            val bluetoothPermissions =
                rememberMultiplePermissionsState(
                    permissions = listOf(
                        android.Manifest.permission.BLUETOOTH,
                        android.Manifest.permission.BLUETOOTH_ADMIN
                    ).apply {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                            toMutableList().addAll(
                                listOf(
                                    android.Manifest.permission.BLUETOOTH_CONNECT,
                                    android.Manifest.permission.BLUETOOTH_SCAN,
                                )
                            )
                        }
                    }
                )
            POSAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val enableBluetoothContract = rememberLauncherForActivityResult(
                        ActivityResultContracts.StartActivityForResult()
                    ) {
                        if (it.resultCode == Activity.RESULT_OK) {
                            Log.d("bluetoothLauncher", "Success")
                            initListeners()
                        } else {
                            Log.w("bluetoothLauncher", "Failed")
                        }
                    }

                    val enableBluetoothIntent = remember {
                        Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    }

                    val bluetoothManager = remember {
                        this.getSystemService(BluetoothManager::class.java)
                    }
                    val bluetoothAdapter: BluetoothAdapter? = remember {
                        bluetoothManager.adapter
                    }

                    MainView() {
                        if (bluetoothPermissions.allPermissionsGranted) {
                            if (bluetoothAdapter?.isEnabled == true) {
                                // Bluetooth is on print the receipt
                                initListeners()
                            } else {
                                // Bluetooth is off, ask user to turn it on
                                enableBluetoothContract.launch(enableBluetoothIntent)
                            }
                        } else {
                            Log.e("Error", "Print ERROR")
                        }
                    }
                }
            }
        }
    }
    private fun initListeners() {
        if (!Printooth.hasPairedPrinter())
            resultLauncher.launch(
                Intent(
                    this@MainActivity,
                    ScanningActivity::class.java
                )
            )
        else printDetails()


        /* callback from printooth to get printer process */
        printing?.printingCallback = object : PrintingCallback {
            override fun connectingWithPrinter() {
                Toast.makeText(this@MainActivity, "Connecting with printer", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun printingOrderSentSuccessfully() {
                Toast.makeText(this@MainActivity, "Order sent to printer", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun connectionFailed(error: String) {
                Toast.makeText(this@MainActivity, "Failed to connect printer", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onError(error: String) {
                Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
            }

            override fun onMessage(message: String) {
                Toast.makeText(this@MainActivity, "Message: $message", Toast.LENGTH_SHORT).show()
            }

            override fun disconnected() {
                Toast.makeText(this@MainActivity, "Disconnected Printer", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun printDetails() {
        val printables = getSomePrintables()
        printing?.print(printables)
    }

    /* Customize your printer here with text, logo and QR code */
    private fun getSomePrintables() = ArrayList<Printable>().apply {

        add(RawPrintable.Builder(byteArrayOf(27, 100, 4)).build()) // feed lines example in raw mode


        //logo
//            add(ImagePrintable.Builder(R.drawable.bold, resources)
//                    .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
//                    .build())

        setContent {

            val checkout:CheckoutViewModel = hiltViewModel()
            val uiState = checkout.uiState.collectAsState().value
            add(
                TextPrintable.Builder()
                    .setText("Pesanan")
                    .setLineSpacing(DefaultPrinter.LINE_SPACING_60)
                    .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                    .setFontSize(DefaultPrinter.FONT_SIZE_LARGE)
                    .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                    .setUnderlined(DefaultPrinter.UNDERLINED_MODE_OFF)
                    .setNewLinesAfter(1)
                    .build()
            )
            add(
                TextPrintable.Builder()
                    .setText("======================")
                    .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                    .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                    .setUnderlined(DefaultPrinter.UNDERLINED_MODE_OFF)
                    .setNewLinesAfter(1)
                    .build()
            )

        uiState.forEach {
            item ->
            add(
                TextPrintable.Builder()
                    .setText("Nama Produk: ${item.name}")
                    .setAlignment(DefaultPrinter.ALIGNMENT_RIGHT)
                    .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                    .setUnderlined(DefaultPrinter.UNDERLINED_MODE_OFF)
                    .setNewLinesAfter(1)
                    .build()
            )

            add(
                TextPrintable.Builder()
                    .setText("Harga Produk: ${item.price}")
                    .setAlignment(DefaultPrinter.ALIGNMENT_RIGHT)
                    .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                    .setUnderlined(DefaultPrinter.UNDERLINED_MODE_OFF)
                    .setNewLinesAfter(1)
                    .build()
            )

            add(
                TextPrintable.Builder()
                    .setText("======================")
                    .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                    .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                    .setUnderlined(DefaultPrinter.UNDERLINED_MODE_OFF)
                    .setNewLinesAfter(1)
                    .build()
            )
        }

            add(RawPrintable.Builder(byteArrayOf(27, 100, 4)).build())
        }


    }


    /* Inbuilt activity to pair device with printer or select from list of pair bluetooth devices */
    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == ScanningActivity.SCANNING_FOR_PRINTER && result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
//            val intent = result.data
                printDetails()
            }
        }

}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    POSAppTheme {
        Greeting("Android")
    }
}