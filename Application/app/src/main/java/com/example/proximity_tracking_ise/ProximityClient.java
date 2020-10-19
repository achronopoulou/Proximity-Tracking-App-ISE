package com.example.proximity_tracking_ise;

import android.app.Service;

public class ProximityClient extends Service {
    @Override
    public void onCreate() {
        //this is called when the service is first started (one-time)
    }

    @Override
    public int onStartCommand() {
        return 0;
    }

    @Override
    public void onDestroy() {
        //this is called when the service is ending
    }

}
