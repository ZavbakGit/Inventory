package com.example.flutter_barcode.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import android.util.Log
import com.google.gson.GsonBuilder
import java.text.SimpleDateFormat
import java.util.*

class BarcodeBroadcastReceiver(val callScanBarcode: (String)->Unit) : BroadcastReceiver() {
    private val gson = GsonBuilder().create()


    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            ServiceManager.BROADCAST_ACTION -> {
                val string = intent.getStringExtra(ServiceManager.KEY_DATA)
                val obj = gson.fromJson(string, DataBarcode::class.java)

                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                val currentDate = sdf.format(Date())

                callScanBarcode("onReceive ${obj.toString()} ${currentDate}")
            }
        }
    }
}