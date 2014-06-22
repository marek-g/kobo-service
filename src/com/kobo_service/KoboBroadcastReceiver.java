package com.kobo_service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class KoboBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        switch (action) {
            case Intent.ACTION_BOOT_COMPLETED:
                KoboService.startService(context);
                OrientationHelper.setTouchOrientation(context, OrientationHelper.getScreenOrientation(context));
                break;
            case Intent.ACTION_CONFIGURATION_CHANGED:
                OrientationHelper.setTouchOrientation(context, OrientationHelper.getScreenOrientation(context));
                break;
            case Intent.ACTION_UMS_CONNECTED:
                USBHelper.ShowUsbStorageActivity();
                break;
        }
    }
}
