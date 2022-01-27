package com.example.notifications.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.notifications.receiver.GPSReceiver;
import com.example.notifications.receiver.IdelModeReceiver;

public class IdelBackService extends Service {
    IdelModeReceiver idelModeReceiver = new IdelModeReceiver();
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        registerReceiver(idelModeReceiver,new IntentFilter("android.os.action.DEVICE_IDLE_MODE_CHANGED"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(idelModeReceiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
