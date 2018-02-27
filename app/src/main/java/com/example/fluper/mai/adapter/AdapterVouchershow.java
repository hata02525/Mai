package com.example.fluper.mai.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fluper.mai.Merchant.Merchant;
import com.example.fluper.mai.R;

/**
 * Created by fluper on 30/11/17.
 */

public class AdapterVouchershow extends RecyclerView.Adapter<AdapterVouchershow.VH> {


    Context context;

    public AdapterVouchershow(Context context)
    {

        this.context=context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.voucherrecycler,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position)
    {
            holder.carddiscount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Merchant.openDialog();
                }
            });
    }

    @Override
    public int getItemCount() {
        return 6;
    }


    public class VH extends RecyclerView.ViewHolder{
        CardView carddiscount;

        public VH(View itemView) {
            super(itemView);
            carddiscount=itemView.findViewById(R.id.carddiscount);
        }
    }



}
