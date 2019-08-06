package com.dalililastproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import com.dalililastproject.R;
public class Faculties_CustomAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<DataModel> dataSet;
    Context context;

    public Faculties_CustomAdapter(ArrayList<DataModel> data, Context context) {
        this.dataSet = data;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(  ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder myViewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 1: {
                View view1 = layoutInflater.inflate(R.layout.faculties_cardview, parent, false);

                myViewHolder = new MyViewHolder(view1);

                break;
            }
            case 2: {
          //      View view2 = layoutInflater.inflate(R.layout.fuclt_adds, parent, false);

              //  myViewHolder = new Adds_MyViewHolder(view2);

                break;
            }
        }

        //////

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int listPosition) {
        final DataModel model = dataSet.get(holder.getAdapterPosition());
        switch (holder.getItemViewType()) {

            case 1: {

                MyViewHolder myViewHolder = (MyViewHolder) holder;


                myViewHolder.textViewName.setText(model.getName().trim());

                final int id = model.getId();

                myViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, Departments.class);
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

    @Override
    public int getItemViewType(int position) {
        return dataSet.get(position).getViewType();
    }

    //////////////////
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;

        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.faculties_textViewName);

            this.linearLayout = itemView.findViewById(R.id.linner);
        }
    }

    public static class Adds_MyViewHolder extends RecyclerView.ViewHolder {

     //   AdView madView;


        Adds_MyViewHolder(View itemView) {
            super(itemView);
         /*   madView =itemView.findViewById(R.id.adView);
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