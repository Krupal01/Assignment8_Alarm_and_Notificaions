package com.example.notifications.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.notifications.MainActivity;
import com.example.notifications.databinding.ActivityGpsactivityBinding;
import com.example.notifications.databinding.ActivityIdleBinding;

public class IdleActivity extends AppCompatActivity {

    private ActivityIdleBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIdleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent =getIntent();
        String msgShort = intent.getStringExtra(MainActivity.SHORT_DIS);
        String msgLong = intent.getStringExtra(MainActivity.LONG_DIS);
        binding.tvIdleShort.setText(msgShort);
        binding.tvIdleLong.setText(msgLong);
    }
}