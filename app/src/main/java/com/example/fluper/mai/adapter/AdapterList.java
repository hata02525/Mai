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
import com.example.fluper.mai.modal.ListModel;
import com.example.fluper.mai.modal.ListStore;

import java.util.ArrayList;

/**
 * Created by fluper on 27/11/17.
 */
public class AdapterList extends RecyclerView.Adapter<AdapterList.VH>{
    Context context;
    ArrayList<ListStore> list;

        public AdapterList(Context context, ArrayList<ListStore> list)
        {
            this.context=context;
            this.list=list;
        }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lists_layout,parent,false);
        return new VH(view);
        }

    @Override
    public void onBindViewHolder(AdapterList.VH holder, int position) {
        holder.showdata(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class VH extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imagev;
        TextView maintext;
        CardView cardvi;



        public VH(View itemView) {
            super(itemView);
            imagev=itemView.findViewById(R.id.imagev);
            maintext=itemView.findViewById(R.id.maintext);
            cardvi=itemView.findViewById(R.id.cardvi);
        cardvi.setOnClickListener(this);}



        public void showdata(int position) {
           ListStore listStore=list.get(position);
            listStore.getMerchantimgid();
            listStore.getMerchantname();
            imagev.setImageResource(listStore.getMerchantimgid());
            maintext.setText(listStore.getMerchantname());
            Log.e("gyg", "showdata: "+listStore.getMerchantimgid());
        }


        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.cardvi:
                    Intent i =  new Intent(context,Merchant.class);
                    context.startActivity(i);
                    break;
            }

        }
    }

}
