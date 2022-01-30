package com.example.test_broadcast_resiver

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.R

import androidx.core.app.NotificationCompat

import android.os.Build




class MyService:Service() {

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, "messages")
                .setContentText("This is running in Background")
                .setContentTitle("Flutter Background")
                .setSmallIcon(R.drawable.ic_lock_lock)
            startForeground(101, builder.build())
        }
    }



    override fun onBind(p0: Intent?): IBinder? {
        return null;
    }

}