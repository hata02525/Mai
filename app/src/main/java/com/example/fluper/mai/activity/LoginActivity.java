package com.example.fluper.mai.activity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextView facebook;
    CallbackManager callbackManager;
    TextView Create_account;
    TextView forgot_password;
    Button login;
    private boolean doubleBackToExitPressedOnce = false;
    Context context;
    EditText email,password;


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
        setContentView(R.layout.activity_login);
        context=LoginActivity.this;
        listner();
        String s=getDebugKeyHash(context);
        Log.e("gyg", "keyhash: " + s);



    }

    private String getDebugKeyHash(Context ctx) {
        try {
            @SuppressLint("WrongConstant") PackageInfo e = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(),64);
            Signature[] var3 = e.signatures;
            int var4 = var3.length;
            byte var5 = 0;
            if (var5 < var4) {
                Signature signature = var3[var5];
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), 0);
            } else {
                return "SHA-1 generation: epic failed";
            }
        } catch (PackageManager.NameNotFoundException var8) {
            return "SHA-1 generation: the key count not be generated: NameNotFoundException thrown";
        } catch (NoSuchAlgorithmException var9) {
            return "SHA-1 generation: the key count not be generated: NoSuchAlgorithmException thrown";
        }
    }

    private void listner() {
        Create_account=(TextView)findViewById(R.id.create);
        login=(Button)findViewById(R.id.login);
        forgot_password=(TextView)findViewById(R.id.forgotpass);
        facebook = (TextView) findViewById(R.id.fb_signin);
        email =  findViewById(R.id.email);
        password = findViewById(R.id.password);
        login.setOnClickListener(this);
        Create_account.setOnClickListener(this);
        forgot_password.setOnClickListener(this);


        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        facebookLogin();
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFacebook();
            }
        });

    }

    public boolean isValid(){
       if (email.getText().toString().trim().isEmpty()){
           email.setError("Please enter email id");
          return false;

       }else if (password.getText().toString().trim().isEmpty()){
           password.setError("Please enter password");
          return false;}

       else if (!Helper.validEmail(email.getText().toString()))
       {
           email.setError("Please enter valid email id");
            return false;
        }
        return true;
   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    /*******
     * -Facebook Login code Start-
     *****/

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
                                    String femail = object.optString("email");
                                    String birthday = object.optString("birthday");
                                    JSONObject picture = object.getJSONObject("picture").getJSONObject("data");
                                    String pictureUrl = picture.getString("url");
//                                    Toast.makeText(LoginActivity.this, "Welcome :" + fName + " " + lName + ", Email :" + femail + " Birthday :" + birthday + "," + " image-url :" + pictureUrl, Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, Home_Activity.class));
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
                Toast.makeText(LoginActivity.this, "Login Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(LoginActivity.this, "Problem connecting to Facebook", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void loginFacebook() {
        LoginManager.getInstance().logOut();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email", "user_birthday"));
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this,R.string.backagain, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                if(isValid()) {
                    startActivity(new Intent(context, Home_Activity.class));
                    finishAffinity();
                }
                break;
            case R.id.forgotpass:
                startActivity(new Intent(context,ForgetPassword.class));
                finish();
                break;
            case R.id.create:
                startActivity(new Intent(context,SignUp.class));
                finish();
                break;

        }
    }
}
