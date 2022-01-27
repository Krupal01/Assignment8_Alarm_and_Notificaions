package com.example.notifications.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.example.notifications.MainActivity;
import com.example.notifications.screens.NetworkActivity;

public class NetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        Log.i("Receiver",wifiManager.getConnectionInfo().toString());
        if (info != null){
            MainActivity.SetNotification(context,"Network Type : "+info.getTypeName(),wifiManager.getConnectionInfo().toString(), android.R.drawable.stat_notify_error,4, NetworkActivity.class);
        }
    }
}