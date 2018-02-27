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
import com.example.fluper.mai.activity.Password.NewPassword;

public class OTPForgetPass extends AppCompatActivity implements View.OnClickListener {
    TextView text;
    Button next;
    ImageView back;
    EditText email_otp;
    String s ="Enter OTP";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp__forget__pass);
        back=(ImageView)findViewById(R.id.back);
        text=(TextView)findViewById(R.id.text) ;
        next=(Button)findViewById(R.id.next);
        email_otp=(EditText)findViewById(R.id.email_otp);
        context=this;

      next.setOnClickListener(this);
      back.setOnClickListener(this);


        text.setText(s);
    }
        public boolean isvalid() {
            if(email_otp.getText().toString().trim().isEmpty())
            {
                email_otp.setError("OTP required!");
                return false;
            }
            if (email_otp != null && (!(email_otp.length()==6)))
            {
                email_otp.setError("must have 6 digit in your OTP");
                return false;
            }
            return true;

        }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.next:
                if(isvalid())
                {
                    startActivity(new Intent(context,NewPassword.class));
                    finish();
                }
                break;
            case R.id.back:
                backpress();
                break;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    @Override
    public void onBackPressed() {

        backpress();
    }

    private void backpress() {
        super.onBackPressed();
        startActivity(new Intent(context,ForgetPassword.class));
        finish();

    }
}


