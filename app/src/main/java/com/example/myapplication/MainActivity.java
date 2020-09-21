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
        final Button connect_ble_button = (Button) findViewById(R.id.connect_ble);
        final Button proximity_button = (Button) findViewById(R.id.proximity);
        final TextView output = (TextView) findViewById(R.id.output);
        output.setText("");

        connect_ble_button.setOnClickListener(new View.OnClickListener() {
            output.setText("Connecting to Device");
        });

        proximity_button.setOnClickListener(new View.OnClickListener() {
            output.setText("Checking Proximity");
        });
    }
}