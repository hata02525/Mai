package com.example.fluper.mai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fluper.mai.R;
import com.example.fluper.mai.modal.ListModel;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

/**
 * Created by fluper on 27/11/17.
 */

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.VH> {
    Context context;
    ArrayList<ListModel> list;


    public AdapterSearch(Context context, ArrayList<ListModel> list)
    {
        this.context=context;
        this.list=list;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.scroll_recycler_search,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(AdapterSearch.VH holder, int position) {

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



        holder.showdata(position);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VH extends RecyclerView.ViewHolder{
        RoundedImageView iv;
        TextView tv;
        TextView tvn;



        public VH(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.imagev);
            tv=itemView.findViewById(R.id.textoncard);
            tvn=itemView.findViewById(R.id.tv_nnmum);

        }



        public void showdata(int position) {
            ListModel listModel=list.get(position);
            iv.setImageResource(listModel.getImageid());
            tv.setText(listModel.getName());
            tvn.setText(listModel.getNamewithnumbr());
            Log.e("gyg", "showdata: "+listModel.getName());

        }

    }
}
