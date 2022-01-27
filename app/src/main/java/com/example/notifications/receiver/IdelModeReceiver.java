package com.example.notifications.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.notifications.MainActivity;
import com.example.notifications.screens.IdleActivity;

public class IdelModeReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (powerManager.isDeviceIdleMode()){
            Log.i("Receiver","idle on");
            MainActivity.SetNotification(context,"idle mode on","", android.R.drawable.ic_lock_idle_lock,5, IdleActivity.class);
        }
        else {
            Log.i("Receiver","idle off");
            MainActivity.SetNotification(context,"idle mode off","", android.R.drawable.ic_lock_idle_lock,5,IdleActivity.class);
        }
    }
}