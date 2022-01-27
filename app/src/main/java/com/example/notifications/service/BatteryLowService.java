package com.example.notifications.service;


import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.notifications.MainActivity;
import com.example.notifications.screens.BatteryActivity;

public class BatteryLowService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent intent1 = new Intent(this, BatteryActivity.class);
        intent1.putExtra(MainActivity.SHORT_DIS,"Low Battery");
        intent1.putExtra(MainActivity.LONG_DIS,"Your Battery is Low , Please put your phone in charge ");
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"0")
                .setSmallIcon(android.R.drawable.ic_lock_idle_low_battery)
                .setContentTitle("Low Battery")
                .setContentText("Your Battery is Low , Please put your phone in charge ")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        Notification notification = builder.build();

        startForeground(2,notification);

        return START_NOT_STICKY;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
