package com.example.notifications.receiver;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.notifications.MainActivity;
import com.example.notifications.screens.DelayAlarmActivity;

public class DelayAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Alarm","Alarm Received");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"0")
                .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                .setContentTitle("Wake Up")
                .setContentText(intent.getStringExtra(MainActivity.ALARM_DIS))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent1 = new Intent(context, DelayAlarmActivity.class);
        intent1.putExtra(MainActivity.SHORT_DIS,"Wake Up");
        intent1.putExtra(MainActivity.LONG_DIS , intent.getStringExtra(MainActivity.ALARM_DIS));
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(8, builder.build());
    }
}