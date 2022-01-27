package com.example.notifications.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.notifications.service.BatteryLowService;

public class BatteryLowReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent serviceIntent = new Intent(context, BatteryLowService.class);
        if (intent.getAction() == Intent.ACTION_BATTERY_LOW){
            context.startService(serviceIntent);
        }else {
            context.stopService(serviceIntent);
        }
    }
}