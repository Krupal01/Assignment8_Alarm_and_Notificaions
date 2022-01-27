package com.example.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TimePicker;

import com.example.notifications.databinding.ActivityMainBinding;
import com.example.notifications.receiver.AlarmReceiver;
import com.example.notifications.receiver.DelayAlarmReceiver;
import com.example.notifications.service.BatteryLowBackServie;
import com.example.notifications.service.BatteryLowService;
import com.example.notifications.service.ChargingBackService;
import com.example.notifications.service.ChargingService;
import com.example.notifications.service.GpsBackService;
import com.example.notifications.service.IdelBackService;
import com.example.notifications.service.NetworkBackService;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public static String ALARM_DIS = "ALARM_DIS";
    public static String SHORT_DIS = "SHORT_DIS";
    public static String LONG_DIS = "LONG_DIS";
    public static String BATTERY_PCT = "BATTERY_PCT";
    public Calendar calendar = Calendar.getInstance();
    public AlarmManager alarmManager1,alarmManager2;
    public PendingIntent pendingIntent1 , pendingIntent2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager2 = (AlarmManager) getSystemService(ALARM_SERVICE);
        //Notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "kk";
            String description = "kkk";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("0", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        binding.swBatteryLow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent serviceBatteryLow = new Intent(getBaseContext(), BatteryLowBackServie.class);
                Intent ForGroundserviceBatteryLow = new Intent(getBaseContext(), BatteryLowService.class);
                if (isChecked){
                    getBaseContext().startService(serviceBatteryLow);
                }
                else{
                    getBaseContext().stopService(serviceBatteryLow);
                    getBaseContext().stopService(ForGroundserviceBatteryLow);
                }
            }
        });
        binding.swCharge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent serviceCharge = new Intent(getBaseContext(), ChargingBackService.class);
                Intent ForGroundserviceCharge = new Intent(getBaseContext(), ChargingService.class);
                if (isChecked){
                    getBaseContext().startService(serviceCharge);
                }
                else{
                    getBaseContext().stopService(serviceCharge);
                    getBaseContext().stopService(ForGroundserviceCharge);
                }
            }
        });
        binding.swGps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Intent serviceGPS = new Intent(getBaseContext(), GpsBackService.class);
                if (isChecked){
                    getBaseContext().startService(serviceGPS);
                }
                else{
                    getBaseContext().stopService(serviceGPS);
                }
            }
        });
        binding.swNetwork.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent serviceNetwork = new Intent(getBaseContext(), NetworkBackService.class);
                if (isChecked){
                    getBaseContext().startService(serviceNetwork);
                }
                else{
                    getBaseContext().stopService(serviceNetwork);
                }
            }
        });
        binding.swIdel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent serviceIdle = new Intent(getBaseContext(), IdelBackService.class);
                if (isChecked){
                    getBaseContext().startService(serviceIdle);
                }
                else{
                    getBaseContext().stopService(serviceIdle);
                }
            }
        });
        binding.etAlarmTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                                calendar.set(Calendar.HOUR_OF_DAY, i);
                                calendar.set(Calendar.MINUTE, i1);
                                calendar.set(Calendar.SECOND,0);

                                binding.etAlarmTime.setText("Alarm set at "+ DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime()));

                            }
                        },00,00,false)
                        .show();
            }
        });
        binding.swSetAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Log.i("time","check true");
                    if(calendar.getTimeInMillis() != 0 && !binding.etAlarmTime.getText().toString().isEmpty() ){
                        Log.i("time",calendar.getTime().toString());
                        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                        intent.putExtra(ALARM_DIS,"Timing Alarm");
                        pendingIntent1 = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
                        alarmManager1.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent1);
                    }else {
                        binding.etAlarmTime.setError("Define Time");
                        binding.swSetAlarm.setChecked(false);
                    }
                }else if (pendingIntent1 != null){
                    alarmManager1.cancel(pendingIntent1);
                    binding.etAlarmTime.getText().clear();
                }
            }
        });

        binding.swSetDelayAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Log.i("delay","check true");
                    if (!binding.etDelayTime.getText().toString().isEmpty()){
                        int Delay = Integer.parseInt(binding.etDelayTime.getText().toString());
                        Log.i("delay","delay");
                        Intent intent = new Intent(MainActivity.this, DelayAlarmReceiver.class);
                        intent.putExtra(ALARM_DIS,"Delay Alarm");
                        pendingIntent2 = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
                        alarmManager2.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(Delay* 1000L),pendingIntent2);
                    }else {
                        binding.etDelayTime.setError("Define proper delay");
                        binding.swSetDelayAlarm.setChecked(false);
                    }
                }else if (pendingIntent2 != null){
                    alarmManager2.cancel(pendingIntent2);
                    binding.etDelayTime.getText().clear();
                }
            }
        });


    }


    public static void SetNotification(Context context, String title, String text, int smallIcon, int id , Class activity) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"0")
                .setSmallIcon(smallIcon)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent1 = new Intent(context, activity);
        intent1.putExtra(SHORT_DIS,title);
        intent1.putExtra(LONG_DIS,text);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(id, builder.build());
    }
}