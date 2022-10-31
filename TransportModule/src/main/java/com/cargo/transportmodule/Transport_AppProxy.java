package com.cargo.transportmodule;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.hdgq.locationlib.LocationOpenApi;

import io.dcloud.feature.uniapp.UniAppHookProxy;

public class Transport_AppProxy implements UniAppHookProxy {
    private static final String TAG = "初始化";

    @Override
    public void onSubProcessCreate(Application application) {

            Log.e(TAG, "onCreate123123: ");
            LocationOpenApi.init(application);
            Log.e("onCreateSuccess", "onCreate: ");
    }

    @Override
    public void onCreate(Application application) {
            Log.e(TAG, "onCreate123123: ");
            LocationOpenApi.init(application);
            Log.e("onCreateSuccess", "onCreate: ");
    }
}
