package com.example.flutter_barcode

import com.example.flutter_barcode.service.ServiceManager
import com.example.flutter_barcode.service.Settings
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {

    private val CHANNEL = "com.example.flutter_barcode"
    private val METHOD_START_SERVICE = "startService"
    private val SCAN_BARCODE = "scanBarcode"


    private lateinit var serviceManager: ServiceManager

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        val methodChannel = MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        )

        fun scanBarcode( barcode:String){
            methodChannel.invokeMethod(SCAN_BARCODE,barcode)
        }

        methodChannel.setMethodCallHandler { call, result ->
            when (call.method) {
                METHOD_START_SERVICE -> {
                    serviceManager.startService(Settings("start service"))
                }
            }
        }


        serviceManager = ServiceManager(this) { it ->
            scanBarcode(it)
        }

        lifecycle.addObserver(serviceManager);

    }

}
