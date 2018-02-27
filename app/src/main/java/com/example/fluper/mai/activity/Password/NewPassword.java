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
import com.example.fluper.mai.activity.LoginActivity;

public class NewPassword extends AppCompatActivity implements View.OnClickListener {
    TextView text;
    Button submit;
    ImageView back;
    Context context;
    EditText newpass, confirmpassword;
    String s = "New Password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__password);

        text = (TextView) findViewById(R.id.text);
        submit = (Button) findViewById(R.id.submit_btn);
        newpass = (EditText) findViewById(R.id.newpass);
        confirmpassword = (EditText) findViewById(R.id.confirmpassword);
        back = (ImageView) findViewById(R.id.back);
        context = this;

        text.setText(s);
        submit.setOnClickListener(this);
      back.setOnClickListener(this);
    }

    public boolean isValid() {
        if (newpass.getText().toString().trim().isEmpty()) {
            newpass.setError("Please enter password");
            return false;
        }
        if (confirmpassword.getText().toString().trim().isEmpty()) {
            confirmpassword.setError("Please enter confirm password");
            return false;
        }
        if (!(newpass.getText().toString().equals(confirmpassword.getText().toString()))) {
            confirmpassword.setError("Password does not match");
            return false;
        }

        if (newpass != null && (!(newpass.length() <= 16 && newpass.length() >=8)))
        {
            newpass.setError("Password must be atleast 8 characters long");
            return false;
        }

            return true;

    }



    @Override
    public void onClick(View view) {


        switch (view.getId())
        {
            case R.id.submit_btn:
            if(isValid())
            {
                startActivity(new Intent(context,LoginActivity.class));
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
        startActivity(new Intent(context,LoginActivity.class));
        finish();

    }
}
