package com.kobo_rotate_touch;

import android.app.Application;
import android.content.Context;

public class KoboRotateTouchApplication extends Application {
    private static Context context;

    public void onCreate(){
        super.onCreate();
        KoboRotateTouchApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return KoboRotateTouchApplication.context;
    }
}
