package com.example.fluper.mai.Merchant;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.fluper.mai.R;

public class MerchantRatingActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView text;
    private ImageView back,dropdown,up_arrow;
    private LinearLayout details;
    private RatingBar ratingBar;
    RelativeLayout about;
    String s="Food & Beverage";
    private EditText comments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_rating);
        init();
        listner();
}

    private void listner() {

        dropdown.setOnClickListener(this);
        up_arrow.setOnClickListener(this);
        back.setOnClickListener(this);
//       ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//           @Override
//           public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
//               ratingBar.setProgress(Integer.parseInt((v)));
//           }
//       });
}

    private void init() {
        ScrollView view = (ScrollView)findViewById(R.id.scroll_view);
        dropdown=(ImageView)findViewById(R.id.dropdown);
//        ratingBar=(RatingBar)findViewById(R.id.ratingbar);
        view.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
       /* view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        });*/
        text=(TextView)findViewById(R.id.text);
        back=(ImageView)findViewById(R.id.back);
        details=(LinearLayout)findViewById(R.id.details);
        up_arrow=(ImageView)findViewById(R.id.up_arrow);
        about=(RelativeLayout)findViewById(R.id.about);
        text.setText(s);
        comments = findViewById(R.id.comments);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.back:
                startActivity(new Intent(MerchantRatingActivity.this,Merchant.class));
                finish();
                break;

            case R.id.dropdown:
                dropdown.setVisibility(View.GONE);
                about.setVisibility(View.GONE);
                details.setVisibility(View.VISIBLE);
                break;

            case R.id.up_arrow:
                details.setVisibility(View.GONE);
                about.setVisibility(View.VISIBLE);
                dropdown.setVisibility(View.VISIBLE);
                break;        }
    }
}
