package com.example.fluper.mai.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fluper.mai.Merchant.Merchant;
import com.example.fluper.mai.R;
import com.example.fluper.mai.modal.ListStore;
import com.example.fluper.mai.modal.SortedList;

import java.util.ArrayList;

/**
 * Created by fluper on 28/11/17.
 */

public class SortedListAdapter extends RecyclerView.Adapter<SortedListAdapter.VH> {

    Context context;
    ArrayList<SortedList> list;


    public SortedListAdapter(Context context, ArrayList<SortedList> list)
    {
        this.context=context;
        this.list=list;
    }
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sortedlistlayout,parent,false);
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

    public class VH extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imagev;
        TextView maintext ,subtext,sortedn;
        CardView card;

        public VH(View itemView) {
            super(itemView);
            imagev=itemView.findViewById(R.id.imagev);
            maintext=itemView.findViewById(R.id.maintext);
            subtext=itemView.findViewById(R.id.subtext);
            sortedn=itemView.findViewById(R.id.sortedn);
            card=itemView.findViewById(R.id.card);
            card.setOnClickListener(this);
        }
        public void showdata(int position) {
            SortedList sortedList=list.get(position);
            sortedList.getImageid();
            sortedList.getTitlename();
            sortedList.getSubtite();
            sortedList.getSnumber();
            imagev.setImageResource(sortedList.getImageid());
            maintext.setText(sortedList.getTitlename());
            subtext.setText(sortedList.getSubtite());
            sortedn.setText(sortedList.getSnumber());

            Log.e("gyg", "showdata: "+sortedList.getTitlename());
        }

        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.card:
                    Intent i =  new Intent(context,Merchant.class);
                    context.startActivity(i);


            }

        }
    }

    }
