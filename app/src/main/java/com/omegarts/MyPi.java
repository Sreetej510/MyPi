package com.omegarts;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.view.Window;

import com.omegarts.mypi.R;

public class MyPi extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static Window window;

    public void onCreate() {
        super.onCreate();
        MyPi.context = getApplicationContext();
    }

    public static void setWindow(Window window){
        MyPi.window = window;
    }

    public static void setStatusBarColor(int color){
        window.setStatusBarColor(color);
    }

}
