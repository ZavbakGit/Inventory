package com.anit.scanner_barcode_service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.content.Intent


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btStart = findViewById<Button>(R.id.btStart)
        val btStop = findViewById<Button>(R.id.btStop)

        val serviceManager = ServiceManager(this)

        btStart.setOnClickListener {
            val data = Settings("Alex")
            serviceManager.startService(data)
        }

        btStop.setOnClickListener {
            Log.d("anit","btStart")
            stopService(Intent(this, ScannerService::class.java))
        }
    }
}