package com.anit.scanner_barcode_library

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit


class ScannerService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("anit", "onCreate");
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("anit", "onStartCommand");
        val string = intent!!.getStringExtra("settings")
        val obj = Json.decodeFromString<Settings>(Settings.serializer(), string!!)
        Log.d("anit", obj.toString());

        //TimeUnit.SECONDS.sleep(3000);

        sendData(DataBarcode("123456789"));

        return START_STICKY
    }

    fun sendData(dataBarcode: DataBarcode) {
        val intent = Intent(ServiceManager.BROADCAST_ACTION)
        val string = Json.encodeToString(DataBarcode.serializer(), dataBarcode)
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