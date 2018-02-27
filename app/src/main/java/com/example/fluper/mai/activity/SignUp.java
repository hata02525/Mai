package com.example.fluper.mai.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fluper.mai.Helper.Helper;
import com.example.fluper.mai.R;
import com.example.fluper.mai.activity.Home.Home_Activity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    TextView login_back;
    Button Signup;
    Context context;
    CallbackManager callbackManager;
    TextView fcbk;
    EditText pass_signup;
    EditText email_signup;
    EditText confirm_pass;




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
        setContentView(R.layout.activity_sign__up);
        context = this;

        Signup = (Button) findViewById(R.id.signup_btn);
        login_back = (TextView) findViewById(R.id.loginback);
        email_signup=(EditText)findViewById(R.id.email_signup);
        pass_signup=(EditText)findViewById(R.id.pass_signup);
        confirm_pass=(EditText)findViewById(R.id.confirm_pass);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        fcbk = (TextView) findViewById(R.id.fb_signin);
        facebookLogin();
        fcbk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFacebook();
            }
        });
        login_back.setOnClickListener(this);
        Signup.setOnClickListener(this);



    }
    public boolean isValid(){
        if (email_signup.getText().toString().trim().isEmpty()){
            email_signup.setError("Please enter email id");
            return false;

        } if (pass_signup.getText().toString().trim().isEmpty()){
            pass_signup.setError("Please enter password");
            return false;

        } if (confirm_pass.getText().toString().trim().isEmpty()){
            confirm_pass.setError("Please enter password");
            return false;

        }
         if (!Helper.validEmail(email_signup.getText().toString())) {
            email_signup.setError("Please enter valid email id");
            return false;
        }
         if(!pass_signup.getText().toString().equals(confirm_pass.getText().toString())) {
            confirm_pass.setError("Password does not match");
            return false;
        }


        if (pass_signup != null && (!(pass_signup.length() <= 16 && pass_signup.length() >=8)))
        {
            pass_signup.setError("Password must be atleast 8 characters long");
            return false;
        }

        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private void facebookLogin() {

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.e("LoginActivity Response ", response.toString());
                                /*{Response:  responseCode: 200, graphObject: {"id":"688943871275382","first_name":"sandeep","last_name":"Kushwah","email":"sandeep.kus022@gmail.com",
                                "picture":{"data":{"is_silhouette":false,"url":"https:\/\/scontent.xx.fbcdn.net\/v\/t1.0-1\/p200x200\/14495345_630162317153538_6320408807838912776_n.jpg?oh=905eb63f8559c63bf041c2154846b7e0&oe=5923B434"}}}, error: null}*/
                                try {
                                    String id = object.optString("id");
                                    String fName = object.optString("first_name");
                                    String lName = object.optString("last_name");
                                    String email = object.optString("email");
                                    String birthday = object.optString("birthday");
                                    JSONObject picture = object.getJSONObject("picture").getJSONObject("data");
                                    String pictureUrl = picture.getString("url");
//                                    Toast.makeText(SignUp.this, "Welcome :" + fName + " " + lName + ", Email :" + email + " Birthday :" + birthday + "," + " image-url :" + pictureUrl, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this, Home_Activity.class));
                               finish();
                                 } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,first_name,last_name,email,picture.type(large),birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(SignUp.this, "Login Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(SignUp.this, "Problem connecting to Facebook", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void loginFacebook() {
        LoginManager.getInstance().logOut();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email", "user_birthday"));
    }


    @Override
    public void onBackPressed() {
        backpress();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginback:
                backpress();
                break;
            case R.id.signup_btn:
                if(isValid()) {
                    startActivity(new Intent(context, CreateProfile.class));
                    finish();
                }
                break;

        }
    }

    private void backpress() {
        super.onBackPressed();
        startActivity(new Intent(context,LoginActivity.class));
        finish();
    }
}
