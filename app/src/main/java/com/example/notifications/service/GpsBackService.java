package com.example.notifications.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.notifications.receiver.GPSReceiver;

public class GpsBackService extends Service {
    GPSReceiver gpsReceiver = new GPSReceiver();
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        registerReceiver(gpsReceiver,new IntentFilter("android.location.PROVIDERS_CHANGED"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(gpsReceiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
