package com.dalililastproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.dalililastproject.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    private static final String TAG = "MainActivity";
    AdRequest adRequest;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        mAdView = findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
          //      Toast.makeText(MainActivity.this, "جاري العرض", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.

               /* if (errorCode == AdRequest.ERROR_CODE_INTERNAL_ERROR) {
                    Toast.makeText(MainActivity.this, "حدث شيء ما داخليًا ؛ على سبيل المثال ، تم تلقي استجابة غير صالحة من خادم الإعلانات."+errorCode, Toast.LENGTH_SHORT).show();

                } else if (errorCode == AdRequest.ERROR_CODE_INVALID_REQUEST) {
                    Toast.makeText(MainActivity.this, " طلب الإعلان غير صالح ؛ على سبيل المثال ، معرف الوحدة الإعلانية غير صحيح."+errorCode, Toast.LENGTH_SHORT).show();
                } else if (errorCode == AdRequest.ERROR_CODE_NETWORK_ERROR) {
                    Toast.makeText(MainActivity.this, "يرجى الاتصال بالانترنت", Toast.LENGTH_SHORT).show();

                } else if (errorCode == AdRequest.ERROR_CODE_NO_FILL) {
                    Toast.makeText(MainActivity.this, "كان طلب الإعلان ناجحًا ، ولكن لم يتم إرجاع أي إعلان بسبب نقص مخزون الإعلان."+errorCode, Toast.LENGTH_SHORT).show();
                }
              //  Toast.makeText(MainActivity.this, "يرجى الاتصال بالانترنت", Toast.LENGTH_SHORT).show();*/
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Toast.makeText(MainActivity.this, "تم العرض", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                Toast.makeText(MainActivity.this, "تم انهاء العرض", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });


       /* mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/
    }

    public void universities(View view) {
        switch (view.getId()) {
            case R.id.Government: {
                Intent intent = new Intent(this, Universities.class);
                intent.putExtra("id", 1);
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "حكومي");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                startActivity(intent);
                break;
            }
            case R.id.Private: {
                Intent intent = new Intent(this, Universities.class);
                intent.putExtra("id", 2);
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "2");
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "خاص");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

                startActivity(intent);
                break;
            }
        }


    }
}
