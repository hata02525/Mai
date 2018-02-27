package com.example.fluper.mai.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fluper.mai.R;
import com.example.fluper.mai.activity.Food_Beverages;

/**
 * Created by fluper on 28/11/17.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.VH> {

    Context context;


    public CityAdapter(Context context)
    {
        this.context=context;

    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.city_layout,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position)
    {
        holder.rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, Food_Beverages.class);
                context.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return 2;
    }


    public class VH extends RecyclerView.ViewHolder {
            TextView cityname,citynumber;
            RelativeLayout rl2;

        public VH(View itemView) {
            super(itemView);
            cityname=(itemView).findViewById(R.id.cityname);
            citynumber=(itemView).findViewById(R.id.citynumber);
            rl2=(itemView).findViewById(R.id.rl2);
        }
    }
}
