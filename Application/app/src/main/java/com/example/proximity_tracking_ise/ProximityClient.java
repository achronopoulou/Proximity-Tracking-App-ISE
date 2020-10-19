package com.example.proximity_tracking_ise;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class ProximityClient extends Service {
    @Override
    private BluetoothLeScanner bleScanner;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onCreate() {
        //this is called when the service is first started (one-time)
        //setup service things
        // Device scan callback.
        private ScanCallback leScanCallback =
                new ScanCallback() {
                    @Override
                    public void onScanResult(int callbackType, ScanResult result) {
                        super.onScanResult(callbackType, result);
                        leDeviceListAdapter.addDevice(result.getDevice());
                        leDeviceListAdapter.notifyDataSetChanged();
                    }
                };
        bleScanner =  BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();

    }

    @Override
    public int onStartCommand() {
        if (!scanning) {
            bleScanner.startScan(leScanCallback);

        }
        //do everything start scan, get rssi, and stop scan
        return 0;
    }

    @Override
    public void onDestroy() {
        //this is called when the service is ending
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
