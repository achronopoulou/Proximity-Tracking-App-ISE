package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    //comment
    //create a feature that initiates bluetooth, looks for a device, connects, and reports the distance
    //3 bags (same room (near), same home, and not in same building (far)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean connected = false;
        final Button connect_ble_button = (Button) findViewById(R.id.connect_ble);
        final Button proximity_button = (Button) findViewById(R.id.proximity);
        final TextView output = (TextView) findViewById(R.id.output);
        output.setText("");

        connect_ble_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connected) {
                    output.setText("Device Already Connected")
                    //device already paired
                } else {
                    //connecting to device
                }
                output.setText("Device Connected!");
            }
        });

        proximity_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (connected) {
                    //print distance
                } else {
                    //no connection exists connect to a device first
                }
                output.setText("Checking Proximity");
            }
        });
    }
}