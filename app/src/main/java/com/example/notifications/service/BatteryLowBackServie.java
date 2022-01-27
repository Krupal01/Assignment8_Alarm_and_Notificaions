package com.example.notifications.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.notifications.receiver.BatteryLowReceiver;
import com.example.notifications.receiver.GPSReceiver;

public class BatteryLowBackServie extends Service {
    BatteryLowReceiver batteryLowReceiver = new BatteryLowReceiver();
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        registerReceiver(batteryLowReceiver,new IntentFilter(Intent.ACTION_BATTERY_LOW));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(batteryLowReceiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
