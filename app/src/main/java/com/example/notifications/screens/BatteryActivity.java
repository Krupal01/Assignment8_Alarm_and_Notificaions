package com.example.notifications.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.notifications.MainActivity;
import com.example.notifications.databinding.ActivityBatteryBinding;


public class BatteryActivity extends AppCompatActivity {

    private ActivityBatteryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBatteryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent =getIntent();
        String msgShort = intent.getStringExtra(MainActivity.SHORT_DIS);
        String msgLong = intent.getStringExtra(MainActivity.LONG_DIS);
        binding.tvBatteryShort.setText(msgShort);
        binding.tvBatteryLong.setText(msgLong);
    }
}