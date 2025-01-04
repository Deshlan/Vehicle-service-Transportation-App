package com.example.latest_try_dauvan_app;

import android.app.Application;
import com.google.firebase.FirebaseApp;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Firebase
        FirebaseApp.initializeApp(this);
    }
}
