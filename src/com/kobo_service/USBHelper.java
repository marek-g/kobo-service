package com.kobo_service;

public class USBHelper {
    public static void ShowUsbStorageActivity() {
        try {
            Runtime.getRuntime().exec(new String[] {
                    "su", "-c", "/system/bin/am start -n com.android.systemui/.usb.UsbStorageActivity"
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
