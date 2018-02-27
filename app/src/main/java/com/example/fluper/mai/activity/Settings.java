package com.example.fluper.mai.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fluper.mai.R;
import com.example.fluper.mai.activity.Password.ChangePassword;

public class Settings extends AppCompatActivity
        implements View.OnClickListener{
    TextView text_title;
    String s="Settings";
    Context context;
    ImageView iv_back;
    TextView changepswrd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
     init();
      listener();
    }

    private void init() {
        text_title=(TextView)findViewById(R.id.text_title);
        iv_back=(ImageView)findViewById(R.id.iv_back);
        changepswrd=(TextView)findViewById(R.id.changepswrd);
        context=this;
        text_title.setText(s);
    }

    private void listener() {
        iv_back.setOnClickListener(this);
        changepswrd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.changepswrd:
                startActivity(new Intent(context,ChangePassword.class));
                break;

            case R.id.iv_back:
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
