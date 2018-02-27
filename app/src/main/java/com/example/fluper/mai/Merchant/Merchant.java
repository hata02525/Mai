package com.example.fluper.mai.Merchant;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fluper.mai.R;
import com.example.fluper.mai.activity.Food_Beverages;
import com.example.fluper.mai.activity.ScannerActivity;
import com.example.fluper.mai.adapter.AdapterMerchantBottom;
import com.example.fluper.mai.adapter.AdapterVouchershow;
import com.example.fluper.mai.modal.ListModel;
import com.example.fluper.mai.utils.DataStore;

import java.util.ArrayList;

public class Merchant extends AppCompatActivity implements View.OnClickListener {

    private AdapterMerchantBottom adapterMerchantBottom;
    private AdapterVouchershow adapterVouchershow;
    private RecyclerView imagesscroll,voucher_shows;
    private ImageView dropdown,up_arrow,back;
    private TextView text;
    private LinearLayout details;
    static Context context;
    private String s="Food & Beverage";
    private Toolbar tToolbar;
    ArrayList<ListModel> imagelist;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant__one);
        context=this;
        imagelist=new ArrayList<>();
        init();
        setAdapterData();
        listener();
    }

    private void listener() {
        dropdown.setOnClickListener(this);
        up_arrow.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void init() {
        imagesscroll=(RecyclerView)findViewById(R.id.imagesscroll);
        voucher_shows=(RecyclerView)findViewById(R.id.voucher_shows);
        dropdown=(ImageView)findViewById(R.id.dropdown);
        details=(LinearLayout)findViewById(R.id.details);
        up_arrow=(ImageView)findViewById(R.id.up_arrow);
        back=(ImageView)findViewById(R.id.back);
        text=(TextView)findViewById(R.id.text);
        tToolbar=(Toolbar) findViewById( R.id.tToolbar);
        setSupportActionBar(tToolbar);
        text.setText(s);
    }

    private void setAdapterData() {
        imagelist = DataStore.StringData();
        imagesscroll.setHasFixedSize(true);
        imagesscroll.invalidate();
        imagesscroll.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        adapterMerchantBottom = new AdapterMerchantBottom(context, imagelist);
        imagesscroll.setAdapter(adapterMerchantBottom);

        voucher_shows.setHasFixedSize(true);
        voucher_shows.invalidate();
        voucher_shows.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        adapterVouchershow = new AdapterVouchershow(context);
        voucher_shows.setAdapter(adapterVouchershow);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.dropdown:
                dropdown.setVisibility(View.GONE);
                details.setVisibility(View.VISIBLE);
                break;

            case R.id.up_arrow:
                details.setVisibility(View.GONE);
                dropdown.setVisibility(View.VISIBLE);
                break;
            case R.id.back:
                backpress();
                break;
        }

    }
    public static void openDialog()
    {
        final Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.redeemdiscount);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        Button bt_use=(Button)dialog.findViewById(R.id.bt_use);
        bt_use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                voucherdialog();
            }
        });
    }

    private static void voucherdialog() {

        final Dialog vdialog=new Dialog(context);
        vdialog.setContentView(R.layout.voucher);
        vdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        vdialog.show();
        Button bt_submit=(Button)vdialog.findViewById(R.id.bt_submit);
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,
                        ScannerActivity.class);
                context.startActivity(intent);
            }
        });
    }

    public static void showDialog() {
        final Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.fullimage);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
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
