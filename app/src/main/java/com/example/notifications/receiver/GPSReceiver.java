package com.example.notifications.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;

import com.example.notifications.MainActivity;
import com.example.notifications.screens.GPSActivity;

public class GPSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Log.i("Receiver", "gpson");
                MainActivity.SetNotification(context, "GPS on", "Your GPS is On, go to setting for Disable GPS", android.R.drawable.ic_menu_mylocation, 3, GPSActivity.class);
            } else {
                Log.i("Receiver", "gps off");
                MainActivity.SetNotification(context, "GPS off", "Your GPS is Off, go to setting for Enable GPS", android.R.drawable.ic_menu_mylocation, 3,GPSActivity.class);
            }

    }
}