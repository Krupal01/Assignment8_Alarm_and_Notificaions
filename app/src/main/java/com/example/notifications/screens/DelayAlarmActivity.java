package com.example.notifications.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.notifications.MainActivity;
import com.example.notifications.databinding.ActivityDelayAlarmBinding;
import com.example.notifications.databinding.ActivityGpsactivityBinding;

public class DelayAlarmActivity extends AppCompatActivity {

    private ActivityDelayAlarmBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDelayAlarmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent =getIntent();
        String msgShort = intent.getStringExtra(MainActivity.SHORT_DIS);
        String msgLong = intent.getStringExtra(MainActivity.LONG_DIS);
        binding.tvDelayAlarmShort.setText(msgShort);
        binding.tvDelayAlarmLong.setText(msgLong);
    }
}