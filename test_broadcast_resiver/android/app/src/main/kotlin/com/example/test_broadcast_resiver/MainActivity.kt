package com.example.test_broadcast_resiver

import io.flutter.embedding.android.FlutterActivity
import android.os.Build

import io.flutter.plugin.common.MethodChannel

import io.flutter.plugin.common.MethodCall

import android.content.Intent

import io.flutter.plugins.GeneratedPluginRegistrant

import android.os.Bundle
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel.MethodCallHandler


class MainActivity: FlutterActivity() {

    private var forService: Intent? = null

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        GeneratedPluginRegistrant.registerWith(flutterEngine)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GeneratedPluginRegistrant.registerWith(this)
        forService = Intent(this@MainActivity, MyService::class.java)
        MethodChannel(getFlutterView(), "com.retroportalstudio.messages")
            .setMethodCallHandler { methodCall, result ->
                if (methodCall.method == "startService") {
                    startService()
                    result.success("Service Started")
                }
            }
    }



    override fun onDestroy() {
        super.onDestroy()
        stopService(forService)
    }

    private fun startService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(forService)
        } else {
            startService(forService)
        }
    }

}
