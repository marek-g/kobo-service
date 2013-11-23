package com.kobo_rotate_touch;

import android.content.Context;
import android.view.WindowManager;

public class OrientationHelper {
    public static int getScreenOrientation(Context context) {
        return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
    }

    public static void setTouchOrientation(int orientation, boolean wait) {
        try {
            Process process = Runtime.getRuntime().exec(new String[] {
                    "su", "-c", "sendevent /dev/input/event2 17 1 " + orientation
            });

            if (wait) {
                process.waitFor();
                process.destroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
