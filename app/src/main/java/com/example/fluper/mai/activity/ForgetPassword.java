package com.example.fluper.mai.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.fluper.mai.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener{

    TextView text;
    ImageView back;
    Button next;
    Context context;
    EditText email;
    String s ="Forgot Password ?";
    Toolbar tToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        context=this;
        tToolbar=(Toolbar) findViewById( R.id.tToolbar);
        setSupportActionBar(tToolbar);
        back=(ImageView)findViewById( R.id.back);
        email=(EditText)findViewById(R.id.ed_email);
        text=(TextView)findViewById(R.id.text) ;
        next=(Button)findViewById(R.id.nextbtn);
        back.setOnClickListener(this);
        next.setOnClickListener(this);


        text.setText(s);
    }
    public boolean isValid(){
        if (email.getText().toString().trim().isEmpty()){
            email.setError("Please enter email id");
            return false;

        }
        else if (!emailValidator(email.getText().toString())) {
            email.setError("Please enter valid email id");
            return false;
        }
        return true;
    }
    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }


    @Override
    public void onClick(View view)
    {
        switch (view.getId()){
            case R.id.back:
              backpress();
                break;
            case R.id.nextbtn:
                if(isValid()) {
                    startActivity(new Intent(context, OTPForgetPass.class));
                    finish();
                }
                break;

        }

    }


    @Override
    public void onBackPressed() {

        backpress();
    }

    private void backpress() {
        super.onBackPressed();
        startActivity(new Intent(context,LoginActivity.class));
        finish();

    }
}
