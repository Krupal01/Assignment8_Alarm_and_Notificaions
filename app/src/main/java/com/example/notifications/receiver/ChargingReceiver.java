package com.example.notifications.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

import com.example.notifications.MainActivity;
import com.example.notifications.service.BatteryLowService;
import com.example.notifications.service.ChargingService;

public class ChargingReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        String batteryPct = String.valueOf(level * 100 / (float)scale);

        Intent serviceIntent = new Intent(context, ChargingService.class);
        serviceIntent.putExtra(MainActivity.BATTERY_PCT , batteryPct);

        Intent serviceIntent1 = new Intent(context, BatteryLowService.class);
        int Status = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);

        if (Status==BatteryManager.BATTERY_STATUS_CHARGING){
            Log.i("Receiver","Charging");
            context.stopService(serviceIntent1);
            context.startService(serviceIntent);
        }else {
            context.stopService(serviceIntent);
        }


    }
}