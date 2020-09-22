package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

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
                int random = (int)(Math.random() * 1 + 1);
                if (connected) {
                    output.setText("DEVICE ALREADY CONNECTED!")
                    //device already paired
                } else if (random == 0){
                    connected = true;
                    output.setText("DEVICE CONNECTED!");
                } else {
                    output.setText("DEVICE FAILED TO CONNECT - TRY AGAIN.");
                }
            }
        });

        proximity_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int random = (int)(Math.random() * 2 + 1);
                if (connected) {
                    if (random == 0) {
                        output.setText("THE TWO DEVICES ARE IN THE SAME ROOM.");
                    } else if (random == 1) {
                        output.setText("THE TWO DEVICES ARE NOT IN THE SAME HOUSE.");

                    } else {
                        output.setText("THE TWO DEVICES ARE IN THE SAME HOUSE - BUT NOT THE SAME ROOM.");
                    }
                } else {
                    output.setText("DEVICE NOT CONNECTED. CONNECT DEVICE FIRST.");
                }
            }
        });
    }
}