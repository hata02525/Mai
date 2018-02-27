package com.example.fluper.mai.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.fluper.mai.Merchant.Merchant;
import com.example.fluper.mai.R;
import com.example.fluper.mai.modal.ListModel;

import java.util.ArrayList;

/**
 * Created by fluper on 27/11/17.
 */

public class AdapterBottom extends RecyclerView.Adapter<AdapterBottom.VH> {

    Context context;
    ArrayList<ListModel> list;


    public AdapterBottom(Context context, ArrayList<ListModel> list)
    {
        this.context=context;
        this.list=list;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.scroll_recycler_for_profile,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(AdapterBottom.VH holder, int position) {
        holder.showdata(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VH extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView iv;
        FrameLayout onlyimage;



        public VH(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.imagev);
            onlyimage=itemView.findViewById(R.id.onlyimage);
            onlyimage.setOnClickListener(this);

        }




        public void showdata(int position) {
            ListModel listModel=list.get(position);
            listModel.getImageid();
            iv.setImageResource(listModel.getImageid());
            Log.e("gyg", "showdata: "+listModel.getImageid());
        }

        @Override
        public void onClick(View view) {
           Intent i =  new Intent(context,Merchant.class);
           context.startActivity(i);

        }
    }
}
