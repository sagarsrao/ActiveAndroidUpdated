package com.example.sumit.activeandroidupdated;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by sumit on 13-Aug-16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        //Notice this initialization code here
        ActiveAndroid.initialize(this);
    }
}
