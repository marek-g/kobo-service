package com.kobo_rotate_touch;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class KoboRotateTouchService extends Service {
    ConfigurationBroadcastReceiver _broadcastReceiver;

    public static void startService(Context context) {
        Intent service = new Intent(context, KoboRotateTouchService.class);
        context.startService(service);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        Log.i("KoboRotateTouchService", "KoboRotateTouchService is starting.");

        if (_broadcastReceiver == null) {
            _broadcastReceiver = new ConfigurationBroadcastReceiver();
            KoboRotateTouchApplication.getAppContext().registerReceiver(_broadcastReceiver,
                    new IntentFilter(Intent.ACTION_CONFIGURATION_CHANGED));
        }

        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i("KoboRotateTouchService", "KoboRotateTouchService is stopping.");

        if (_broadcastReceiver != null) {
            KoboRotateTouchApplication.getAppContext().unregisterReceiver(_broadcastReceiver);
            _broadcastReceiver = null;
        }

        super.onDestroy();
    }
}
