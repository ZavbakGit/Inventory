package com.anit.scanner_barcode_service

import android.content.Context
import android.content.Intent
import kotlinx.serialization.json.Json


class ServiceManager(val context: Context) {

    fun startService(settins: Settings) {
        val string = Json.encodeToString(Settings.serializer(),settins)
        val intent = Intent(context, ScannerService::class.java).putExtra("settings",string)
        context.startService(intent)
    }

    fun stopService() {
        context.stopService(Intent(context, ScannerService::class.java))
    }
}