package com.example.flutter_barcode.service

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.gson.GsonBuilder


class ServiceManager(private val context: Context,private val callScanBarcode: (String)->Unit) :
    LifecycleObserver {

    private val gson = GsonBuilder().create()
    private val barcodeBroadcastReceiver = BarcodeBroadcastReceiver(callScanBarcode)


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(){
        context.stopService(Intent(context, ScannerService::class.java))
        context.unregisterReceiver(barcodeBroadcastReceiver);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        val intFilter = IntentFilter(BROADCAST_ACTION)
        context.registerReceiver(barcodeBroadcastReceiver, intFilter)
    }



    companion object{
        const val BROADCAST_ACTION =  "com.anit.scanner_barcode_library.broadcast_action"
        const val KEY_SETTINGS = "settings"
        const val KEY_DATA = "data"
    }

    fun startService(settings: Settings) {
        val string = gson.toJson(settings)
        val intent = Intent(context, ScannerService::class.java).putExtra(KEY_SETTINGS,string)
        context.startService(intent)
    }
}