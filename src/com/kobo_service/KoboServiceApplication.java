package com.kobo_service;

import android.app.Application;
import android.content.Context;

public class KoboServiceApplication extends Application {
    private static Context context;

    public void onCreate(){
        super.onCreate();
        KoboServiceApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return KoboServiceApplication.context;
    }
}
