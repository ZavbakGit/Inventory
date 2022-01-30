package com.anit.scanner_barcode_library

import android.content.Context
import android.content.Intent
import kotlinx.serialization.json.Json





class ServiceManager(val context: Context) {

    companion object{
        const val BROADCAST_ACTION =  "com.anit.scanner_barcode_library.broadcast_action"
        const val KEY_SETTINGS = "settings"
        const val KEY_DATA = "data"
    }

    fun startService(settings: Settings) {
        val string = Json.encodeToString(Settings.serializer(),settings)
        val intent = Intent(context, ScannerService::class.java).putExtra(KEY_SETTINGS,string)
        context.startService(intent)
    }

    fun stopService() {
        context.stopService(Intent(context, ScannerService::class.java))
    }
}