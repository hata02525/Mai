package com.example.fluper.mai.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.countrypicker.CountryPicker;
import com.countrypicker.CountryPickerListener;
import com.example.fluper.mai.Helper.Helper;
import com.example.fluper.mai.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateProfile extends AppCompatActivity implements View.OnClickListener {


    Button submit;
    ImageView country_select_icon, flag_image, overlapImage, icon;
    TextView show;
    Context context;
    EditText name, mobile, dob, choosepasscode, confirmpasscode;
    private Bitmap bitmap;
    private File destination = null;
    private InputStream inputStreamImg;
    private String imgPath = null;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;
    final CountryPicker picker = CountryPicker.newInstance("Select Country");


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__profile);
        context = this;
        icon = (ImageView) findViewById(R.id.dropdown);
        flag_image = (ImageView) findViewById(R.id.flag);
        country_select_icon = (ImageView) findViewById(R.id.dropdown_country);
        dob = (EditText) findViewById(R.id.dob);
        submit = (Button) findViewById(R.id.submit);
        show = (TextView) findViewById(R.id.showtxt);
        overlapImage = (ImageView) findViewById(R.id.overlapImage);
        name = (EditText) findViewById(R.id.name);
        mobile = (EditText) findViewById(R.id.mobile);
        choosepasscode = (EditText) findViewById(R.id.choosepasscode);
        confirmpasscode = (EditText) findViewById(R.id.confirmpasscode);
        country_select_icon.setOnClickListener(this);
        submit.setOnClickListener(this);
        icon.setOnClickListener(this);
        overlapImage.setOnClickListener(this);


    }

    public boolean isValid() {
        if (name.getText().toString().trim().isEmpty()) {
            name.setError("Please enter full name");
            return false;

        } else if (mobile.getText().toString().trim().isEmpty()) {
            mobile.setError("Please enter mobile number");
            return false;

        } else if (dob.getText().toString().trim().isEmpty()) {
            dob.setError("Please enter date of birth");
            return false;
        } else if (choosepasscode.getText().toString().trim().isEmpty()) {
            choosepasscode.setError("please enter passcode");
            return false;

        } else if (choosepasscode.getText().toString().length() > 4) {
            choosepasscode.setError("must be 4 characters long");
            return false;
        } else if (confirmpasscode.getText().toString().trim().isEmpty()) {
            confirmpasscode.setError("please enter confirm passcode");
            return false;
        } else if (!choosepasscode.getText().toString().equals(confirmpasscode.getText().toString())) {
            confirmpasscode.setError("passcode does not match");
            return false;
        } else if (!Helper.validMobile(mobile.getText().toString())) {
            mobile.setError("Please enter valid mobile number");
            return false;
        }

        return true;
    }


    private void openCountryPicker() {

        picker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String code) {
                String drawableName = "flag_" + GetCountryCode(code).toLowerCase(Locale.ENGLISH);
//                SharedPreference sharedPreference = SharedPreference.getInstance(context);
//                sharedPreference.putString(Constant.COUNTRY_CODE, "+" + GetCountryPhoneCode(code));
                flag_image.setImageResource(getResId(drawableName));
                picker.dismiss();
            }
        });
        picker.show(this.getSupportFragmentManager(), "COUNTRY_PICKER");
    }

    private int getResId(String drawableName) {
        try {
            Class<com.countrypicker.R.drawable> res = com.countrypicker.R.drawable.class;
            Field field = res.getField(drawableName);
            int drawableId = field.getInt(null);
            return drawableId;
        } catch (Exception e) {
            Log.e("COUNTRYPICKER", "Failure to get drawable id.", e);
        }
        return -1;
    }

    /*get country phone code*/
    public String GetCountryCode(String CountryCODE) {
        String CountryZipCode = "";
        Log.e("CountryID", "=" + CountryCODE);
        String[] rl = this.getResources().getStringArray(R.array.CountryCodes);
        for (int i = 0; i < rl.length; i++) {
            String[] g = rl[i].split(",");
            if (g[1].trim().equals(CountryCODE.trim())) {
                CountryZipCode = g[1];
                return CountryZipCode;
            }
        }
        return "";
    }

    @Override
    protected void onStart() {
        super.onStart();

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                DatePickerDialog d = new DatePickerDialog(CreateProfile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = year + "-" + (month + 1) + "-" + dayOfMonth;
                        dob.setText(date);
                        dob.setError(null);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                d.getDatePicker().setMaxDate(System.currentTimeMillis());

                d.setCancelable(false);
                d.show();
            }
        });

    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event)  {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            // do something on back.
//            finish();
//            return true;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CreateProfile.this, SignUp.class));
        finish();

    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.submit:
                if (isValid()) {
                    startActivity(new Intent(context, EnterOTP.class));
                    finish();
                }
                break;
            case R.id.dropdown:
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateProfile.this);

                final String[] gender = {"Mr.", "Mrs.", "Ms."};
                builder.setItems(gender, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        show.setText(gender[which]);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                break;

            case R.id.dropdown_country:
                openCountryPicker();
                break;

            case R.id.overlapImage:
                selectImage();
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose From Gallery", "Cancel"};
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setTitle("Select Option");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    dialog.dismiss();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra("crop", "true");
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    intent.putExtra("outputX", 200);
                    intent.putExtra("outputY", 200);
                    intent.putExtra("return-data", true);
                    startActivityForResult(intent, PICK_IMAGE_CAMERA);
                } else if (options[item].equals("Choose From Gallery")) {
                    dialog.dismiss();
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        inputStreamImg = null;
        if (requestCode == PICK_IMAGE_CAMERA) {
            try {
                Uri selectedImage = data.getData();
                bitmap = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);

                Log.e("Activity", "Pick from Camera::>>> ");

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                destination = new File(Environment.getExternalStorageDirectory() + "/" +
                        getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                imgPath = destination.getAbsolutePath();
                overlapImage.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICK_IMAGE_GALLERY) {
            Uri selectedImage = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                Log.e("Activity", "Pick from Gallery::>>> ");

                imgPath = getRealPathFromURI(selectedImage);
                destination = new File(imgPath.toString());
                overlapImage.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}