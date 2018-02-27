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
import com.example.fluper.mai.modal.ListStore;

import java.util.ArrayList;

/**
 * Created by fluper on 28/11/17.
 */

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.VH> {


    Context context;
    ArrayList<ListStore> list;
    public StarAdapter (Context context, ArrayList<ListStore> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.starlist, parent, false);
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

    public class VH extends RecyclerView.ViewHolder {
        ImageView imagev;
        TextView maintext;

        public VH(View itemView) {
            super(itemView);
            imagev = itemView.findViewById(R.id.imagev);
            maintext = itemView.findViewById(R.id.maintext);
        }


        public void showdata(int position) {
            ListStore listStore = list.get(position);
            listStore.getMerchantimgid();
            listStore.getMerchantname();
            imagev.setImageResource(listStore.getMerchantimgid());
            maintext.setText(listStore.getMerchantname());
            Log.e("gyg", "showdata: " + listStore.getMerchantimgid());
        }
    }

}
