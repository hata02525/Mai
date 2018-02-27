package com.example.fluper.mai.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fluper.mai.R;
import com.example.fluper.mai.adapter.AdapterProfiileRecycler;
import com.example.fluper.mai.modal.ListModel;
import com.example.fluper.mai.utils.DataStore;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    TextView text_title;
    String s="My Profile";
    RelativeLayout resettext;
    Button logout;
    AdapterProfiileRecycler adapterProfiileRecycler;
    RecyclerView Rprofile;
    ImageView iv_back,settings;
    Context context;
    ArrayList<ListModel> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        text_title=(TextView)findViewById(R.id.text_title) ;
        Rprofile=(RecyclerView)findViewById(R.id.Rprofile) ;
        iv_back=(ImageView)findViewById(R.id.iv_back);
        logout=(Button)findViewById(R.id.logout);
        settings=(ImageView)findViewById(R.id.settings);
        resettext=(RelativeLayout) findViewById(R.id.resettext);
        context=this;
        text_title.setText(s);
        setAdapterdata();
        resettext.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        settings.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    private void setAdapterdata() {
        list = DataStore.StringData();
        Rprofile.setHasFixedSize(true);
        Rprofile.invalidate();
        Rprofile.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        adapterProfiileRecycler = new AdapterProfiileRecycler(context, list);
        Rprofile.setAdapter(adapterProfiileRecycler);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.resettext:
                startActivity(new Intent(context, ResetPasscode.class));
                break;

            case R.id.iv_back:
               backpress();
                break;

            case R.id.settings:
                startActivity(new Intent(context, Settings.class));
                break;
            case R.id.logout:
//                startActivity(new Intent(context,LoginActivity.class));
//                finish();

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivity(new Intent(context,LoginActivity.class));
                                ProfileActivity.this.finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
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
