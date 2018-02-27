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
import com.example.fluper.mai.adapter.AdapterNotification;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    AdapterNotification adapterNotification;
    RecyclerView notification_recycler;
    Context context;
    TextView text_title;
    ImageView iv_back;
    String st = "Notifications";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        context = this;
        init();
        listener();
        setAdapterData();

    }

    private void listener() {
        iv_back.setOnClickListener(this);
    }


    private void init() {

        notification_recycler = (RecyclerView) findViewById(R.id.notification_recycler);
        text_title = (TextView) findViewById(R.id.text_title);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        text_title.setText(st);
    }


    private void setAdapterData() {

        notification_recycler.setHasFixedSize(true);
        notification_recycler.invalidate();
        notification_recycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        adapterNotification = new AdapterNotification(context);
        notification_recycler.setAdapter(adapterNotification);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_back:
                backpress();

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
