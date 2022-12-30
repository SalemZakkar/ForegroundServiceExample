package com.salem.background_service;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends FlutterActivity {
    public String channel = "com.app/AppChannel";
    public String startMethod = "start";
    public String stop = "stop";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Notification.initNotification(getApplicationContext());
        System.out.println("Created");
        System.out.println("========================================================>");
    }



    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        System.out.println("Hello App Created!\n=========================================================");
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger() , channel).setMethodCallHandler(
                (call , result) -> {
                    if(call.method.equals(startMethod)){
                        System.out.println("started\n------------------------------------------------------->");
                        Intent intent = new Intent(getApplicationContext() , AppService.class);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            startForegroundService(intent);
                        }else{
                            startService(intent);
                        }
                        result.success(true);
                    }if(call.method.equals(stop)){
                        System.out.println("Stopped\n------------------------------------------------------->");
                        stopService(new Intent(getApplicationContext() , AppService.class));
                        result.success(true);
                    }
                });


    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
