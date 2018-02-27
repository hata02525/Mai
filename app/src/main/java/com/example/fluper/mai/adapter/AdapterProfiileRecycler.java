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

import java.util.ArrayList;

/**
 * Created by fluper on 29/11/17.
 */

public class AdapterProfiileRecycler extends RecyclerView.Adapter<AdapterProfiileRecycler.VH> {

    Context context;
    ArrayList<ListModel> list;


    public AdapterProfiileRecycler(Context context, ArrayList<ListModel> list)
    {
        this.context=context;
        this.list=list;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_myprofile,parent,false);
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
