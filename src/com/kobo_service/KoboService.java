package com.kobo_service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class KoboService extends Service {
    ConfigurationBroadcastReceiver _broadcastReceiver;

    public static void startService(Context context) {
        Intent service = new Intent(context, KoboService.class);
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
        Log.i("KoboService", "KoboService is starting.");

        if (_broadcastReceiver == null) {
            _broadcastReceiver = new ConfigurationBroadcastReceiver();
            KoboServiceApplication.getAppContext().registerReceiver(_broadcastReceiver,
                    new IntentFilter(Intent.ACTION_CONFIGURATION_CHANGED));
        }

        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i("KoboService", "KoboService is stopping.");

        if (_broadcastReceiver != null) {
            KoboServiceApplication.getAppContext().unregisterReceiver(_broadcastReceiver);
            _broadcastReceiver = null;
        }

        super.onDestroy();
    }
}
