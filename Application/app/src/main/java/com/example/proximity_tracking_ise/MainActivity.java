package com.example.proximity_tracking_ise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    

    //connect two devices via BLE
    //One device is the GATT client (scans for advertisements, connects, requests data)
    //One device is GATT Server (makes advertisements, connects, and sends data)
    //connect via UUID
    //once they are connected query for RSSI and calculate the distance
}