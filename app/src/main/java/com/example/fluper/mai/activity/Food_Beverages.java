package com.example.fluper.mai.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fluper.mai.R;
import com.example.fluper.mai.adapter.AdapterList;
import com.example.fluper.mai.adapter.SortedListAdapter;
import com.example.fluper.mai.modal.ListStore;
import com.example.fluper.mai.modal.SortedList;
import com.example.fluper.mai.utils.SortedData;

import java.util.ArrayList;

public class Food_Beverages extends AppCompatActivity implements View.OnClickListener
{
    ImageView back;
    TextView text;
    Context context;
    RecyclerView recyclersortedview;
    SortedListAdapter sortedListAdapter;
    String s="Food & Beverage";
    ArrayList<SortedList> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__beverages);
        back=(ImageView)findViewById(R.id.back);
        text=(TextView)findViewById(R.id.text);
        recyclersortedview=(RecyclerView)findViewById(R.id.sortedlist);
        text.setText(s);
        context=this;
        back.setOnClickListener(this);
        setAdapterData();

    }

    private void setAdapterData()
    {
        list=SortedData.StringData();
        recyclersortedview.setHasFixedSize(true);
        recyclersortedview.invalidate();
        recyclersortedview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        sortedListAdapter = new SortedListAdapter(context,list);
        recyclersortedview.setAdapter(sortedListAdapter);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.back:
                backpress();
              break;
        }

    }

    @Override
    public void onBackPressed() {

        backpress();
    }

    private void backpress() {
        super.onBackPressed();
        finish();

    }
}
