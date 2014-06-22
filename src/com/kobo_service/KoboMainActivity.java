package com.kobo_service;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Spinner;

public class KoboMainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        KoboService.startService(this);

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = 400;
        layoutParams.height = 320;
        getWindow().setAttributes(layoutParams);

        Spinner updateModeSpinner = (Spinner)findViewById(R.id.update_mode_spinner);
        updateModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                NativeHelper.ioctlSetInteger("/dev/graphics/fb0", NativeHelper.MXCFB_SET_UPDATE_MODE, pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Spinner updateModeSpinner = (Spinner)findViewById(R.id.update_mode_spinner);
        int updateMode = NativeHelper.ioctlGetInteger("/dev/graphics/fb0", NativeHelper.MXCFB_GET_UPDATE_MODE);
        updateModeSpinner.setSelection(updateMode);
    }

    public void onToggleScreenOrientation(View view) {
        int orientation = OrientationHelper.getScreenOrientation(this);
        if (orientation == 0 || orientation == 2) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    public void onUSBMode(View view) {
        USBHelper.ShowUsbStorageActivity();
    }
}
