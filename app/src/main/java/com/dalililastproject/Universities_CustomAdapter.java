package com.dalililastproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dalililastproject.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.NativeExpressAdView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Universities_CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<DataModel> dataSet;
    private Context context;

    Universities_CustomAdapter(ArrayList<DataModel> data, Context context) {
        this.dataSet = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder myViewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 1: {
                View view1 = layoutInflater.inflate(R.layout.cardview, parent, false);

                myViewHolder = new MyViewHolder(view1);

                break;
            }
            case 2: {
              //  View view2 = layoutInflater.inflate(R.layout.naitve_adds_item, parent, false);

             //   myViewHolder = new Adds_MyViewHolder(view2);

                break;
            }
        }

        return myViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return dataSet.get(position).getViewType();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int listPosition) {

        final DataModel model = dataSet.get(holder.getAdapterPosition());


        switch (holder.getItemViewType()) {

            case 1: {
                MyViewHolder myViewHolder = (MyViewHolder) holder;


                myViewHolder.textViewName.setText(model.getName());
                final int id = model.getId();
                InputStream ims = null;
                try {
                    Picasso.with(context).load(model.getImage()).into(myViewHolder.ImageView);
                    ims = context.getAssets().open(model.getIcon());
                    Drawable drawable = Drawable.createFromStream(ims, null);
                    myViewHolder.ImageView.setImageDrawable(drawable);


                } catch (IOException e) {

                }

                LinearLayout linearLayout = myViewHolder.linearLayout;
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, Faculties.class);
                        intent.putExtra("id", id);

                        context.startActivity(intent);
                    }
                });
                break;
            }
            case 2: {
                //ADS

                break;
            }
        }

    }

    //////////////////
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        ImageView ImageView;
        LinearLayout linearLayout;

          MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.ImageView = (ImageView) itemView.findViewById(R.id.ImageView);
            this.linearLayout = itemView.findViewById(R.id.linner);
        }
    }

    public static class Adds_MyViewHolder extends RecyclerView.ViewHolder {
            //    AdView madView;


          Adds_MyViewHolder(View itemView) {
            super(itemView);
          /*  madView =itemView.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            madView.loadAd(adRequest);*/


        }
    }

    @Override
    public int getItemCount() {
        return (null != dataSet ? dataSet.size() : 0);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}