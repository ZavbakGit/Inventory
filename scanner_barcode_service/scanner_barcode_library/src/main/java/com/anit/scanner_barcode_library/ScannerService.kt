package com.anit.scanner_barcode_service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.serialization.json.Json

class ScannerService:Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("anit","onCreate");
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("anit","onStartCommand");
        val string = intent!!.getStringExtra("settings")
        val obj = Json.decodeFromString<Settings>(Settings.serializer(),string!!)
        Log.d("anit",obj.toString());
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null;
    }

    override fun onDestroy() {
        Log.d("anit","onDestroy");
    }
}