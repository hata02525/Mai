package com.example.fluper.mai.activity.Password;

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
import com.example.fluper.mai.activity.Settings;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener{
    TextView text_title;
    ImageView iv_back;
    Button savebutton;
    EditText oldpasswrd,newpasswrd,confirmpass;
    String st="Change Password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        init();
        listener();
    }


    private void init() {
        text_title=(TextView)findViewById(R.id.text_title);
        savebutton=(Button)findViewById(R.id.savebutton);
        iv_back=(ImageView)findViewById(R.id.iv_back);
        oldpasswrd=(EditText)findViewById(R.id.oldpasswrd);
        newpasswrd=(EditText)findViewById(R.id.newpasswrd);
        confirmpass=(EditText)findViewById(R.id.confirmpass);
        text_title.setText(st);
    }

    private void listener() {
        iv_back.setOnClickListener(this);
        savebutton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.iv_back:
                backpress();
                break;
            case R.id.savebutton:
                if(isvalid())
                {
                    Intent i=new Intent(ChangePassword.this,Settings.class);
                    startActivity(i);
                    finish();
                }
                break;
        }

    }

    private boolean isvalid() {



        if (oldpasswrd.getText().toString().trim().isEmpty()) {
            oldpasswrd.setError("Please enter old password");
            return false;
        }
        if (newpasswrd.getText().toString().trim().isEmpty()) {
            newpasswrd.setError("Please enter new password");
            return false;
        }
        if (confirmpass.getText().toString().trim().isEmpty()) {
            confirmpass.setError("Please enter confirm password");
            return false;
        }

        if(!newpasswrd.getText().toString().equals(confirmpass.getText().toString())) {
            confirmpass.setError("password does not match");
            return false;
        }

        if (newpasswrd != null && confirmpass !=null && (!(newpasswrd.length() <= 16 && newpasswrd.length() >=8)))
        {
            newpasswrd.setError("Password must be atleast 8 characters long");
            return false;
        }


        return true;
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
        finish();

    }
}
