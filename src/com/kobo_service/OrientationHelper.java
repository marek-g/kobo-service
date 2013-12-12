package com.kobo_service;

import android.content.Context;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

public class OrientationHelper {
    public static int getScreenOrientation(Context context) {
        return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
    }

    public static void setTouchOrientation(final Context context, final int orientation) {
        int res = NativeHelper.sendEvent("/dev/input/event2", (short)17, (short)1, orientation);

        Toast.makeText(context, context.getString(R.string.touch_set_to)
                + " " + getOrientationName(context, orientation),
                Toast.LENGTH_SHORT).show();
    }

    public static String getOrientationName(Context context, int orientation) {
        switch (orientation) {
            case 0: return context.getString(R.string.portrait);
            case 1: return context.getString(R.string.landscape);
            case 2: return context.getString(R.string.portrait_reversed);
            case 3: return context.getString(R.string.landscape_reversed);
        }
        return context.getString(R.string.unknown);
    }
}
