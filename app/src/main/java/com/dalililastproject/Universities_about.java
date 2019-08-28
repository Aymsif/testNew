package com.dalililastproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

import com.dalililastproject.R;
public class Universities_about extends AppCompatActivity {
    ImageView imageView;

    ArrayList<DataModel> dataModels;
    DB_helper db_helper;
    TextView title,info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universities_about);

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
        imageView=findViewById(R.id.ImageView_desc);
        db_helper = new DB_helper(this);
        dataModels=new ArrayList<DataModel>();
        title=findViewById(R.id.textViewName_desc);
        info=findViewById(R.id.textViewDisc_desc);

        String lang= Locale.getDefault().getLanguage();
        if (lang!="ar"){
            dataModels=db_helper.get_universities_info(id,"en");

        }else {
            dataModels=db_helper.get_universities_info(id,"ar");

        }

        InputStream ims = null;
        try {
            ims = this.getAssets().open(dataModels.get(0).getImage());
            Drawable drawable = Drawable.createFromStream(ims, null);
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        title.setText(dataModels.get(0).getName());
        info.setText(dataModels.get(0).getInfo());
    }
}
