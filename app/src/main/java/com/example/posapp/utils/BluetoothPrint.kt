package com.example.posapp.utils

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import com.dantsu.escposprinter.EscPosPrinter
import com.dantsu.escposprinter.connection.bluetooth.BluetoothPrintersConnections
import com.dantsu.escposprinter.textparser.PrinterTextParser
import com.dantsu.escposprinter.textparser.PrinterTextParserImg
import com.example.posapp.R


class BluetoothPrint (
    val context:Context
        ) {
    fun print() {
        val printer = EscPosPrinter(
            BluetoothPrintersConnections.selectFirstPaired(),
            203,
            48f,
            32
        )
        val formattedText = formatText(printer)
        printer.printFormattedText(formattedText).let {
            Log.d("Berhasil","Di Print")
        }

    }

    private fun formatText(printer:EscPosPrinter):String {
        val satu = 1
        return "[C]<img>"+PrinterTextParserImg.bitmapToHexadecimalString(
            printer,
            context.resources.getDrawableForDensity(
                R.drawable.ic_launcher_foreground,DisplayMetrics.DENSITY_MEDIUM
            )
        ) + "</img>\n" +
                "[L]\n"+
                "[C]<b> Print $satu ni ? </b>" +
                "[L]Iya nih"
    }
}