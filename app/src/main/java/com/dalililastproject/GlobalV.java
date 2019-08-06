package com.dalililastproject;

import android.app.Application;

import com.dalililastproject.R;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class GlobalV extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        MobileAds.initialize(this,
                getString(R.string.app_ad_unit_id));


    }
}
