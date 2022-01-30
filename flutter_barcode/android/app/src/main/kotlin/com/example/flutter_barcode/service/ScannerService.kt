package com.example.flutter_barcode.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.google.gson.GsonBuilder


class ScannerService : Service() {

    private val gson = GsonBuilder().create()

    override fun onCreate() {
        super.onCreate()
        Log.d("anit", "onCreate");
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("anit", "onStartCommand");
        val string = intent!!.getStringExtra(ServiceManager.KEY_SETTINGS)
        val obj = gson.fromJson(string, Settings::class.java)
        Log.d("anit", obj.toString());



        sendData(DataBarcode("123456789"));

        return START_STICKY
    }

    private fun sendData(dataBarcode: DataBarcode) {
        val intent = Intent(ServiceManager.BROADCAST_ACTION)
        val string = gson.toJson(dataBarcode)
        intent.putExtra(ServiceManager.KEY_DATA, string);
        sendBroadcast(intent);
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null;
    }

    override fun onDestroy() {
        Log.d("anit", "onDestroy");
    }
}