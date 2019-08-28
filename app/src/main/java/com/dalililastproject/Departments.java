package com.dalililastproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.dalililastproject.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.NativeExpressAdView;

import java.util.Locale;

public class Departments extends AppCompatActivity {
    DB_helper db_helper;
    TextView dept;
    public AdView AdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);
        db_helper=new DB_helper(this);
        dept=findViewById(R.id.textViewDisc_dept);
        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
        String lang= Locale.getDefault().getLanguage();
        if (lang!="ar"){

            dept.setText(db_helper.get_dept_info(id,"en").trim());
        }else {

            dept.setText(db_helper.get_dept_info(id,"ar").trim());
        }



        AdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        AdView.loadAd(adRequest);



    }
}
