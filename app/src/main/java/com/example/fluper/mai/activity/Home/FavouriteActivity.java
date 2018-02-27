package com.example.fluper.mai.activity.Home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.fluper.mai.R;
import com.example.fluper.mai.activity.NotificationActivity;
import com.example.fluper.mai.activity.ProfileActivity;
import com.example.fluper.mai.adapter.AdapterList;
import com.example.fluper.mai.adapter.AdapterSearch;
import com.example.fluper.mai.adapter.AdapterTop;
import com.example.fluper.mai.adapter.FavouriteAdapter;
import com.example.fluper.mai.modal.ListModel;
import com.example.fluper.mai.modal.ListStore;
import com.example.fluper.mai.utils.DataStore;
import com.example.fluper.mai.utils.MerchantStore;

import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recyclerViewtop,recycler_fav,recyclerViewsearch,recycler_list;
    Context context;
    Activity activity;
    private RelativeLayout Container1;
    private RelativeLayout Container2;
    private RelativeLayout favouritelayout;
    private LinearLayout layout_search;
    private AdapterTop adapterTop;
    private AdapterSearch adapterSearch;
    private AdapterList adapterList;
    private EditText ed_search;
    ImageView home,star,search,back,iv_profile,iv_notification,cross;
    private FavouriteAdapter favouriteAdapter;
    ArrayList<ListStore> liststore = new ArrayList<>();
    ArrayList<ListModel> list = new ArrayList<>();
    private boolean doubleBackToExitPressedOnce=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        context = this;
        activity=FavouriteActivity.this;
        home=(ImageView)findViewById(R.id.home);
        star=(ImageView)findViewById(R.id.star);
        cross=(ImageView)findViewById(R.id.cross);
        layout_search = (LinearLayout) findViewById(R.id.layout_search);
        search = (ImageView) findViewById(R.id.iv_search);
        back = (ImageView) findViewById(R.id.iv_back);
        iv_notification=(ImageView)findViewById(R.id.iv_notification);
        iv_profile=(ImageView)findViewById(R.id.iv_profile);
        ed_search=(EditText)findViewById(R.id.ed_search);
        favouritelayout=(RelativeLayout)findViewById(R.id.favouritelayout);
        Container1 = (RelativeLayout) findViewById(R.id.rl_container);
        Container2 = (RelativeLayout) findViewById(R.id.rl_container2);
        recyclerViewtop = (RecyclerView) findViewById(R.id.recyclerviewtop);
        recycler_fav=(RecyclerView)findViewById(R.id.recyclerfav);
        recyclerViewsearch=(RecyclerView)findViewById(R.id.recyclerviewfor_search);
        recycler_list=(RecyclerView)findViewById(R.id.recycler_list);
        setAdapterData();
        Container1.setVisibility(View.VISIBLE);
        search.setOnClickListener(this);
        back.setOnClickListener(this);
        home.setOnClickListener(this);
        star.setOnClickListener(this);
        iv_profile.setOnClickListener(this);
        iv_notification.setOnClickListener(this);
    }

    private void setAdapterData() {

        list = DataStore.StringData();
        liststore = MerchantStore.StringData();
        recyclerViewtop.setHasFixedSize(true);
        recyclerViewtop.invalidate();
        recyclerViewtop.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        adapterTop = new AdapterTop(context, list, activity);
        recyclerViewtop.setAdapter(adapterTop);


        recycler_fav.setHasFixedSize(true);
        recycler_fav.invalidate();
        recycler_fav.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        favouriteAdapter = new FavouriteAdapter(context, liststore);
        recycler_fav.setAdapter(favouriteAdapter);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.home:
                Intent h=new Intent(FavouriteActivity.this,Home_Activity.class);
                startActivity(h);
                finish();
                break;

            case R.id.star:
                Intent st=new Intent(FavouriteActivity.this,StarActivity.class);
                startActivity(st);
                finish();
                break;

            case R.id.iv_search:
                loaddata();
                Container1.setVisibility(View.GONE);
                Container2.setVisibility(View.VISIBLE);
                layout_search.setVisibility(View.VISIBLE);
                favouritelayout.setVisibility(View.GONE);
                showSoftKeyBoard(this);
                ed_search.setText("");
                ed_search.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        cross.setVisibility(View.GONE);
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        cross.setVisibility(View.GONE);

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        filter(editable.toString());
                        String e=ed_search.getText().toString();
                        if(e.isEmpty())
                        {
                            cross.setVisibility(View.GONE);
                            hideSoftKeyBoard(FavouriteActivity.this);
                        }
                        else
                        {
                            cross.setVisibility(View.VISIBLE);}
                    }

                    private void filter(String text) {


                        ArrayList<ListStore> temp = new ArrayList();
                        for (ListStore d : liststore) {
                            if (d.getMerchantname().toLowerCase().contains(text.toLowerCase())) {
                                temp.add(d);
                            }
                        }
                        recycler_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                        adapterList = new AdapterList(activity, temp);
                        recycler_list.setAdapter(adapterList);
                    }

                });


                cross.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        hideSoftKeyBoard(FavouriteActivity.this);
                        ed_search.setText("");
                        recycler_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                        adapterList = new AdapterList(activity, liststore);
                        recycler_list.setAdapter(adapterList);

                    }
                });



                break;

            case R.id.iv_back:
                Container2.setVisibility(View.GONE);
                Container1.setVisibility(View.VISIBLE);
                layout_search.setVisibility(View.GONE);
                favouritelayout.setVisibility(View.VISIBLE);
                hideSoftKeyBoard(this);
                break;

            case R.id.iv_profile:
                startActivity(new Intent(FavouriteActivity.this,ProfileActivity.class));
                break;

            case R.id.iv_notification:
                Intent in=new Intent(FavouriteActivity.this,NotificationActivity.class);
                startActivity(in);
                break;

        }

    }

    private void loaddata() {


        recyclerViewsearch.setHasFixedSize(true);
        recyclerViewsearch.invalidate();
        recyclerViewsearch.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        adapterSearch = new AdapterSearch(context, list);
        recyclerViewsearch.setAdapter(adapterSearch);

        recycler_list.setHasFixedSize(true);
        recycler_list.invalidate();
        recycler_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        adapterList = new AdapterList(context, liststore);
        recycler_list.setAdapter(adapterList);
    }

    @Override
    public void onBackPressed() {

        backpress();
    }

    private void backpress() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finish();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this,R.string.backagain, Toast.LENGTH_SHORT).show();
    }


    public static void hideSoftKeyBoard(Activity ctx) {
        View focusedView = ctx.getCurrentFocus();
        if (focusedView != null) {
            InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(focusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    public static void showSoftKeyBoard(Activity ctx) {
        View focusedView = ctx.getCurrentFocus();
        if (focusedView != null) {
            InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
            // imm.hideSoftInputFromWindow(focusedView.getWindowToken(), InputMethodManager.SHOW_FORCED);
        }
    }
}
