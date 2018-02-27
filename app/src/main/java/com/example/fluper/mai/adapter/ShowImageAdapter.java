package com.example.fluper.mai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.fluper.mai.R;
import com.example.fluper.mai.modal.ListModel;

import java.util.ArrayList;

/**
 * Created by fluper on 30/11/17.
 */

public class ShowImageAdapter extends RecyclerView.Adapter<ShowImageAdapter.VH> {

    Context context;
    ArrayList<ListModel> list;


    public ShowImageAdapter(Context context,ArrayList<ListModel> list)
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
    public void onBindViewHolder(VH holder, int position) {
        holder.showdata(position);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class VH extends RecyclerView.ViewHolder{
        ImageView iv;




        public VH(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.imagev);}



        public void showdata(int position) {
            ListModel listModel=list.get(position);
            listModel.getImageid();
            iv.setImageResource(listModel.getImageid());
            Log.e("gyg", "showdata: "+listModel.getImageid());
        }
    }
}
