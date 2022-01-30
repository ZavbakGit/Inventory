package com.example.test_broadcast_resiver

import io.flutter.app.FlutterApplication
import android.app.NotificationManager

import android.app.NotificationChannel

import android.os.Build




class App: FlutterApplication() {
    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("messages", "Messages", NotificationManager.IMPORTANCE_LOW)
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}