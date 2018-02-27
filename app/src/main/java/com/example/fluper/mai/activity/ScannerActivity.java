package com.example.fluper.mai.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fluper.mai.Merchant.Merchant;
import com.example.fluper.mai.Merchant.MerchantRatingActivity;
import com.example.fluper.mai.R;

import javax.xml.transform.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler,View.OnClickListener {

    Context contect;
    private static final String TAG = "scanner";
    private ZXingScannerView mScannerView;
    String fire_result;
    private TextView text;
    ImageView back;
    String s="Scan QR Code";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        contect= ScannerActivity.this;
        init();
        listener();
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            fire_result = bundle.getString("fire");
        }
    }

    private void listener() {
        showScanner();
        back.setOnClickListener(this);
    }

    private void init() {
        text=(TextView)findViewById(R.id.text);
        back=(ImageView)findViewById(R.id.back);
        mScannerView = findViewById(R.id.qr_scanner_view);
        text.setText(s);

    }


    private void showScanner() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                contect.checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    1);
        } else {
            Show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Show();
            }
        }
    }
    private void Show() {
        try{
            mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
            mScannerView.startCamera();          // Start camera on resume
            mScannerView.setKeepScreenOn(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        try{
            mScannerView.stopCamera();           // Stop camera on pause
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void handleResult(com.google.zxing.Result result) {
        Log.e(TAG, "handleResult: "+result.getText() ); // Prints scan results
        Log.v(TAG, result.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        mScannerView.removeAllViews(); //<- here remove all the views, it will make an Activity having no View
        mScannerView.stopCamera(); //<- then stop the camera

        startActivity(new Intent(contect,MerchantRatingActivity.class));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.back:

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
