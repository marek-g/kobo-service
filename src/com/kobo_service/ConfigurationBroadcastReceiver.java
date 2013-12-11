package com.kobo_service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ConfigurationBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            KoboService.startService(context);
            OrientationHelper.setTouchOrientation(context, OrientationHelper.getScreenOrientation(context));
        } else if (Intent.ACTION_CONFIGURATION_CHANGED.equals(action)) {
            OrientationHelper.setTouchOrientation(context, OrientationHelper.getScreenOrientation(context));
        }
    }
}
