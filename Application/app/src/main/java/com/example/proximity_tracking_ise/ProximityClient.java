package com.example.proximity_tracking_ise;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class ProximityClient extends Service {
    //scanner properties
    private BluetoothLeScanner bleScanner;
    private long SCAN_PERIOD;
    private boolean scanning;
    private Handler handler;
    Context context = getApplicationContext();
    int duration = Toast.LENGTH_SHORT;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onCreate() {
        //this is called when the service is first started (one-time)
        //setup service things
        // Device scan callback.
        bleScanner =  BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();
        SCAN_PERIOD = 10000;
        handler = new Handler();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public int onStartCommand() {
        // Device scan callback. Outputs the Result of the Scan
        final ScanCallback leScanCallback =
                new ScanCallback() {
                    @Override
                    public void onScanResult(int callbackType, ScanResult result) {
                        super.onScanResult(callbackType, result);
                        //toast a message saying devices connected and ouput RSSI
                        if (result == null) {
                            Toast noDeviceFound = Toast.makeText(context, "No Device Found", duration);
                            noDeviceFound.show();
                        } else {
                            int rss = result.getRssi();
                            Toast rssValue = Toast.makeText(context, rss, duration);
                            rssValue.show();
                        }
                    }
                };
        if (!scanning) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    scanning = false;
                    bleScanner.stopScan(leScanCallback);
                }
            }, SCAN_PERIOD);
            scanning = true;
            //startScan(List<ScanFilter> filters, ScanSettings settings, ScanCallback callback) eventually for UUID 
            bleScanner.startScan(leScanCallback);
        }
        scanning = false;
        bleScanner.stopScan(leScanCallback);
        return 0;
    }

    @Override
    public void onDestroy() {
        Toast destroyNotification = Toast.makeText(context, "Proximity Connection Destroyed", duration);
        destroyNotification.show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
