package com.example.myapplication;

import android.app.Application;
import android.util.Log;
import android.webkit.WebView;

public class App extends Application {

    // 启动阶段
    @Override
    public void onCreate() {
        super.onCreate();

        WebView.setWebContentsDebuggingEnabled(true);
        Log.d("s", "test");
    }
}
