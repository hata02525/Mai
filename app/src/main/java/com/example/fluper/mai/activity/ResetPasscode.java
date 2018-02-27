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

public class ResetPasscode extends AppCompatActivity implements View.OnClickListener {

    ImageView iv_back;
    Context context;
    Button savebtn;
    TextView text_title;
    EditText oldpasscode, newpasscode, confirmpass;
    String s = "Reset Passcode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_passcode);
        context = this;
        init();
        listener();
    }

    private void listener() {
        iv_back.setOnClickListener(this);
        savebtn.setOnClickListener(this);
    }

    private void init() {

        iv_back = (ImageView) findViewById(R.id.iv_back);
        text_title = (TextView) findViewById(R.id.text_title);
        text_title.setText(s);
        savebtn = (Button) findViewById(R.id.savebtn);
        oldpasscode = (EditText) findViewById(R.id.oldpasscode);
        newpasscode = (EditText) findViewById(R.id.newpasscode);
        confirmpass = (EditText) findViewById(R.id.confirmpass);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
               backpress();
                break;
            case R.id.savebtn:
               if(isvalid())
             {
                startActivity(new Intent(context, ProfileActivity.class));
                    finish();}
                break;}


    }

    private boolean isvalid() {
        if (oldpasscode.getText().toString().trim().isEmpty()) {
            oldpasscode.setError("Please enter old passcode");
            return false;
        }
       else if (newpasscode.getText().toString().trim().isEmpty()) {
            newpasscode.setError("Please enter new passcode");
            return false;
        }
        else if (confirmpass.getText().toString().trim().isEmpty()) {
            confirmpass.setError("Please enter confirm passcode");
            return false;
        }

        else if(!newpasscode.getText().toString().equals(confirmpass.getText().toString())) {
            confirmpass.setError("password does not match");
            return false;
        }
        else if(newpasscode.getText().toString().length()>4||newpasscode.getText().toString().length()<4)
        {
            newpasscode.setError("Passcode must be 4 characters long");
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
