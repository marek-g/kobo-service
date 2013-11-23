package com.kobo_rotate_touch;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class KoboRotateTouchActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = 320;
        layoutParams.height = 200;
        getWindow().setAttributes(layoutParams);
    }

    public void onToggleScreenOrientation(View view) {
        int orientation = OrientationHelper.getScreenOrientation(this);
        if (orientation == 0 || orientation == 2) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
}
