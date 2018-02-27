package com.example.fluper.mai.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.fluper.mai.R;
import com.example.fluper.mai.activity.Home.Home_Activity;
import com.example.fluper.mai.modal.Categorylist;
import com.example.fluper.mai.modal.ListModel;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

/**
 * Created by fluper on 27/11/17.
 */

public class AdapterTop extends RecyclerView.Adapter<AdapterTop.VH> {

    Activity context;
    ArrayList<ListModel> list;
    private Home_Activity home_activity;
    ArrayList<Categorylist> categorylists;
    int selectedPosition=-1;



    public AdapterTop(Context context, ArrayList<ListModel> list, Activity activity) {
        this.context = activity;
        this.list = list;

    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.scroll_recycler_top, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(final VH holder, final int position) {

        switch (position)
        {
            case 0:
//                holder.iv.(context.getResources().getDrawable(R.drawable.foodborder));
               holder.iv.setBorderColor(context.getResources().getColor(R.color.foodgreen));
                break;
            case 1:
                holder.iv.setBorderColor(context.getResources().getColor(R.color.beautycolor));
                break;
            case 2:
                holder.iv.setBorderColor(context.getResources().getColor(R.color.wellnesscolor));
                break;
            case 3:
                holder.iv.setBorderColor(context.getResources().getColor(R.color.hotelcolor));
                break;
            case 4:
                holder.iv.setBorderColor(context.getResources().getColor(R.color.tourcolor));
                break;
            case 5:
                holder.iv.setBorderColor(context.getResources().getColor(R.color.attraction));
                break;
            case 6:
                holder.iv.setBorderColor(context.getResources().getColor(R.color.retailcolor));
                break;
            case 7:
                holder.iv.setBorderColor(context.getResources().getColor(R.color.servicecolor));
                break;
            case 8:
                holder.iv.setBorderColor(context.getResources().getColor(R.color.education));
                break;
            case 9:
                holder.iv.setBorderColor(context.getResources().getColor(R.color.medical));
                break;
             }

        if(selectedPosition==position)
            holder.iv.setColorFilter(context.getResources().getColor(R.color.light));
        else
            holder.iv.setColorFilter(Color.TRANSPARENT);

        holder.showdata(position);
        holder.frametop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition=position;
                notifyDataSetChanged();
               Home_Activity.openDialog();

            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    public class VH extends RecyclerView.ViewHolder {
        RoundedImageView iv;
        TextView tv;
        FrameLayout frametop;



        public VH(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.imagev);
            tv = itemView.findViewById(R.id.textoncard);
            frametop = itemView.findViewById(R.id.frametop);


        }

        public void showdata(int position) {
            ListModel listModel = list.get(position);
            iv.setImageResource(listModel.getImageid());
            tv.setText(listModel.getName());
            Log.e("gyg", "showdata: " + listModel.getName());

        }

    }
}
