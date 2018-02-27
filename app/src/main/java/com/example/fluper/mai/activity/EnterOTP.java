package com.example.fluper.mai.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fluper.mai.R;
import com.example.fluper.mai.activity.Home.Home_Activity;

public class EnterOTP extends AppCompatActivity {

    private ImageView back;
    private TextView change;
    private TextView text;
    private EditText et_otp;
    private Button nexttohome;
    String s ="Enter OTP";



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);

        change=(TextView)findViewById(R.id.change);
        nexttohome=(Button)findViewById(R.id.nextto_home);
        et_otp=(EditText)findViewById(R.id.et_otp);
        back=(ImageView)findViewById(R.id.back);
        text=(TextView)findViewById(R.id.text) ;

        text.setText(s);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent=new Intent(EnterOTP.this,CreateProfile.class);
            startActivity(intent);
            finish();

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backpress();
            }
        });

        nexttohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isvalid()) {
                    Intent it = new Intent(EnterOTP.this, Home_Activity.class);
                    startActivity(it);
                    finish();

                }
            }
        });
    }

    public boolean isvalid() {
        if(et_otp.getText().toString().trim().isEmpty())
        {
            et_otp.setError("OTP required!");
            return false;
        }
        if (et_otp != null && (!(et_otp.length()==6)))
        {
            et_otp.setError("must have 6 digit in your OTP");
            return false;
        }
        return true;
    }


    public void onBackPressed() {

        backpress();
    }

    private void backpress() {

        super.onBackPressed();
        finish();

    }

}
