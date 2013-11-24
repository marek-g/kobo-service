package com.kobo_rotate_touch;

import android.content.Context;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

public class OrientationHelper {
    public static int getScreenOrientation(Context context) {
        return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
    }

    public static void setTouchOrientation(final Context context, final int orientation) {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Process process = Runtime.getRuntime().exec(new String[] {
                            "su", "-c", "sendevent /dev/input/event2 17 1 " + orientation
                    });
                    process.waitFor();
                    process.destroy();

                    Toast.makeText(context, context.getString(R.string.touch_set_to)
                            + " " + getOrientationName(context, orientation),
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();

                    Toast.makeText(context, context.getString(R.string.touch_set_failed)
                            + " " + getOrientationName(context, orientation),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
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
