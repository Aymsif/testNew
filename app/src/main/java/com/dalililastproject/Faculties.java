package com.dalililastproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.Toast;

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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import com.dalililastproject.R;
public class Faculties extends AppCompatActivity {
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    GridLayoutManager gridLayoutManager;
    RecyclerView recyclerView;
    ImageView imageView;
    private ArrayList<DataModel> data;
    DB_helper db_helper;
/*    AdLoader adLoader;*/

  //  public AdView AdView;

    AdRequest adRequest;
    AdView mAdView;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculties);


        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");

        db_helper = new DB_helper(this);
        File file = getApplicationContext().getDatabasePath(db_helper.DB_NAME);
        if (!file.exists()) {
            db_helper.getReadableDatabase();
            if (copyDataBase(this)) {
            } else return;
        }
        recyclerView = (RecyclerView) findViewById(R.id.faculties_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        // recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = new ArrayList<DataModel>();
        data = db_helper.get_faculties_data(id);


        // data.add(data.size(), dataModel2);


        imageView = findViewById(R.id.header);
        InputStream ims = null;
        try {
            ims = this.getAssets().open(data.get(0).getImage().toString().trim());
            Drawable drawable = Drawable.createFromStream(ims, null);
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mAdView = findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    /*    AdView = new AdView(this);
        AdView.setAdSize(AdSize.SMART_BANNER);


        AdView.setAdUnitId(getString(R.string.banner_ad_unit_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        AdView.loadAd(adRequest);

        AdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.

                DataModel dataModel2 = new DataModel();
                dataModel2.setViewType(2);
                data.add(2, dataModel2);
                data.add(data.size(), dataModel2);
                //adapter.notifyItemInserted(2);
                recyclerView.getAdapter().notifyDataSetChanged();
                //runAnimation(recyclerView);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
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
        });*/

   /*     MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });








        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        adLoader = new AdLoader.Builder(this, "ca-app-pub-4754692842325949/9176378254")
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {

                        if (adLoader.isLoading()) {
                            // The AdLoader is still loading ads.
                            // Expect more adLoaded or onAdFailedToLoad callbacks.
                            return;
                        } else {
                            // The AdLoader has finished loading ads.

                            DataModel dataModel2 = new DataModel();
                            dataModel2.setViewType(2);

                            data.add(data.size()/2, dataModel2);
                            //adapter.notifyItemInserted(2);
                            recyclerView.getAdapter().notifyDataSetChanged();
                            //runAnimation(recyclerView);
                        }



                    }
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // Handle the failure by logging, altering the UI, and so on.
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build();
        adLoader.loadAd(new AdRequest.Builder().build());*/
    }

    @Override
    protected void onResume() {

        runAnimation(recyclerView, 0);



        super.onResume();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tool_bar,menu);

        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.about)
        {
            Intent intent=new Intent(this,Universities_about.class);
            intent.putExtra("id",this.id);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    private void runAnimation(RecyclerView recyclerView, int type) {
        if (type == 0) {
            Context context = recyclerView.getContext();
            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_right);

            adapter = new Faculties_CustomAdapter(data, this);
            recyclerView.setAdapter(adapter);

            recyclerView.setLayoutAnimation(controller);
            recyclerView.getAdapter().notifyDataSetChanged();
            recyclerView.scheduleLayoutAnimation();
        }
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
