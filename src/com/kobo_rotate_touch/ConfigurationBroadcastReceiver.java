package com.kobo_rotate_touch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class ConfigurationBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            // register for CONFIGURATION_CHANGED action
            KoboRotateTouchApplication.getAppContext().registerReceiver(this,
                    new IntentFilter(Intent.ACTION_CONFIGURATION_CHANGED));

            // update touch orientation (do not wait for the process because we will be killed -
            // system is not loaded yet and super user will give us permissions way too late)
            OrientationHelper.setTouchOrientation(OrientationHelper.getScreenOrientation(context), false);
        } else if (Intent.ACTION_CONFIGURATION_CHANGED.equals(action)) {
            // when system booted it's better to wait for the process to end to not leak resources
            OrientationHelper.setTouchOrientation(OrientationHelper.getScreenOrientation(context), true);
        }
    }
}
