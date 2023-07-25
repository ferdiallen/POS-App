package com.example.posapp

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.posapp.ui.theme.POSAppTheme
import com.example.posapp.utils.BluetoothPrint
import com.example.posapp.view.MainView
import com.example.posapp.view.home.HomeView
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bluetoothPermissions =
            // Checks if the device has Android 12 or above

        setContent {
            val bluetoothPermissions =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                rememberMultiplePermissionsState(
                    permissions = listOf(
                        android.Manifest.permission.BLUETOOTH,
                        android.Manifest.permission.BLUETOOTH_ADMIN,
                        android.Manifest.permission.BLUETOOTH_CONNECT,
                        android.Manifest.permission.BLUETOOTH_SCAN,
                    )
                )
            } else {
                rememberMultiplePermissionsState(
                    permissions = listOf(
                        android.Manifest.permission.BLUETOOTH,
                        android.Manifest.permission.BLUETOOTH_ADMIN,
                    )
                )
            }

            POSAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val printer = BluetoothPrint(this)

                    val enableBluetoothContract = rememberLauncherForActivityResult(
                        ActivityResultContracts.StartActivityForResult()
                    ) {
                        if (it.resultCode == Activity.RESULT_OK) {
                            Log.d("bluetoothLauncher", "Success")
                            printer.print()
                        } else {
                            Log.w("bluetoothLauncher", "Failed")
                        }
                    }

                    val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)

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
                                printer.print()
                            } else {
                                // Bluetooth is off, ask user to turn it on
                                enableBluetoothContract.launch(enableBluetoothIntent)
                            }
                        } else {
                            Log.e("Error","Print ERROR")
                        }
                    }
                    }
                }
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