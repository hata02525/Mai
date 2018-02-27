package com.example.fluper.mai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fluper.mai.R;
import com.example.fluper.mai.activity.Home.Home_Activity;
import com.example.fluper.mai.modal.Categorylist;

import java.util.ArrayList;

/**
 * Created by fluper on 27/11/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.VH> {
    Context context;
    private Home_Activity home_activity;
    ArrayList<Categorylist> list;

public CategoryAdapter(Context context,ArrayList<Categorylist> list)
{
    this.context=context;
    this.list=list;
}


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.categorylayout,parent,false);
        return new VH(view);
}

    @Override
    public void onBindViewHolder(CategoryAdapter.VH holder, int position) {
      holder.showdata(position);
        holder.rl_countrylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Home_Activity.openCityDialog();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VH extends RecyclerView.ViewHolder{
    TextView tvnumber,tvname;
        RelativeLayout rl_countrylist;



        public VH(View itemView) {
            super(itemView);
            tvnumber=itemView.findViewById(R.id.tvnumber);
            tvname=itemView.findViewById(R.id.tvname);
            rl_countrylist=(RelativeLayout) itemView.findViewById(R.id.rl_countrylist);
        }



        public void showdata(int position) {
            Categorylist cl=list.get(position);
            tvname.setText(cl.getListname());
            tvnumber.setText(cl.getNumber());
            Log.e("gyg", "showdata: "+cl.getListname());

        }
    }
}
