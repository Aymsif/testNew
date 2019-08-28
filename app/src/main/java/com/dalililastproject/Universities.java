package com.dalililastproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.dalililastproject.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Locale;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Universities extends AppCompatActivity {
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    GridLayoutManager gridLayoutManager;
    RecyclerView recyclerView;
    ImageView imageView;
    private ArrayList<DataModel> data;
    DB_helper db_helper;
    private int id = 0;
 //   public AdView AdView;
    GlobalV globalV;
    AdRequest adRequest;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universities);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");

        imageView = findViewById(R.id.header);
        InputStream ims = null;
        try {
            ims = this.getAssets().open("ic_ysg.png");
            Drawable drawable = Drawable.createFromStream(ims, null);
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        db_helper = new DB_helper(this);
        File file = getApplicationContext().getDatabasePath(db_helper.DB_NAME);
        if (!file.exists()) {
            db_helper.getReadableDatabase();
            if (copyDataBase(this)) {
            } else return;
        }
        recyclerView = (RecyclerView) findViewById(R.id.universities_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        // recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = new ArrayList<DataModel>();

        String lang= Locale.getDefault().getLanguage();
        if (lang!="ar"){
            data=db_helper.get_universities_data(id,"en");
        }else {data=db_helper.get_universities_data(id,"ar");}



        mAdView = findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
/*

        AdView = new AdView(this);
        AdView.setAdSize(AdSize.SMART_BANNER);


        AdView.setAdUnitId(getString(R.string.banner_ad_unit_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        AdView.loadAd(adRequest);

        AdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
              //  Toast.makeText(Universities.this, "جاري العرض", Toast.LENGTH_SHORT).show();



                DataModel dataModel2 = new DataModel();
                dataModel2.setViewType(2);
                data.add(2, dataModel2);
              //  data.add(data.size(), dataModel2);
                //adapter.notifyItemInserted(2);
                recyclerView.getAdapter().notifyDataSetChanged();
                //runAnimation(recyclerView);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
             //   Toast.makeText(Universities.this, "يرجى الاتصال بالانترنت", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
             }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
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
*/

    }


    @Override
    protected void onResume() {
        super.onResume();


        runAnimation(recyclerView);


    }

    private void runAnimation(RecyclerView recyclerView) {

        Context context = recyclerView.getContext();
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_down);

        adapter = new Universities_CustomAdapter(data, this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();



    }

    public Boolean copyDataBase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(db_helper.DB_NAME);
            String outFileName = db_helper.DB_LOCATION + db_helper.DB_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
}
