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
import com.example.fluper.mai.adapter.StarAdapter;
import com.example.fluper.mai.modal.ListModel;
import com.example.fluper.mai.modal.ListStore;
import com.example.fluper.mai.utils.DataStore;
import com.example.fluper.mai.utils.MerchantStore;

import java.util.ArrayList;

public class StarActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerViewtop,recycler_star,recyclerViewsearch,recycler_list;
    Context context;
    Activity activity;
    private RelativeLayout Container1;
    private RelativeLayout Container2;
    private RelativeLayout starlayout;
    private LinearLayout layout_search;
    private AdapterTop adapterTop;
    private AdapterSearch adapterSearch;
    private AdapterList adapterList;
    private EditText ed_search;
    private ImageView search,home,fav,iv_profile,back,iv_notification,cross;
    StarAdapter starAdapter;

    ArrayList<ListStore> liststore = new ArrayList<>();
    ArrayList<ListModel> list = new ArrayList<>();
    private boolean doubleBackToExitPressedOnce=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star);
        context = this;
        activity=StarActivity.this;
        search = (ImageView) findViewById(R.id.iv_search);
        home=(ImageView)findViewById(R.id.home);
        fav=(ImageView)findViewById(R.id.fav);
        back = (ImageView) findViewById(R.id.iv_back);
        cross=(ImageView)findViewById(R.id.cross);
        ed_search=(EditText)findViewById(R.id.ed_search);
        iv_profile=(ImageView)findViewById(R.id.iv_profile);
        Container1 = (RelativeLayout) findViewById(R.id.rl_container);
        starlayout=(RelativeLayout)findViewById(R.id.starlayout);
        iv_notification=(ImageView)findViewById(R.id.iv_notification);
        layout_search = (LinearLayout) findViewById(R.id.layout_search);
        Container2 = (RelativeLayout) findViewById(R.id.rl_container2);
        recyclerViewtop = (RecyclerView) findViewById(R.id.recyclerviewtop);
        recycler_star=(RecyclerView)findViewById(R.id.recyclerfav);
        recyclerViewsearch=(RecyclerView)findViewById(R.id.recyclerviewfor_search);
        recycler_list=(RecyclerView)findViewById(R.id.recycler_list);
        setAdapterData();
        Container1.setVisibility(View.VISIBLE);
        starlayout.setVisibility(View.VISIBLE);
        home.setOnClickListener(this);
        fav.setOnClickListener(this);
        iv_notification.setOnClickListener(this);
        search.setOnClickListener(this);
        iv_profile.setOnClickListener(this);
        back.setOnClickListener(this);


    }

    private void setAdapterData() {

        list = DataStore.StringData();
        liststore = MerchantStore.StringData();
        recyclerViewtop.setHasFixedSize(true);
        recyclerViewtop.invalidate();
        recyclerViewtop.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        adapterTop = new AdapterTop(context, list, activity);
        recyclerViewtop.setAdapter(adapterTop);


        recycler_star.setHasFixedSize(true);
        recycler_star.invalidate();
        recycler_star.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        starAdapter = new StarAdapter(context, liststore);
        recycler_star.setAdapter(starAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                loaddata();
                Container1.setVisibility(View.GONE);
                Container2.setVisibility(View.VISIBLE);
                layout_search.setVisibility(View.VISIBLE);
                starlayout.setVisibility(View.GONE);
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
                            hideSoftKeyBoard(StarActivity.this);
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
                        hideSoftKeyBoard(StarActivity.this);
                        ed_search.setText("");
                        recycler_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                        adapterList = new AdapterList(activity, liststore);
                        recycler_list.setAdapter(adapterList);

                    }
                });
                break;
            case  R.id.home:
                startActivity(new Intent(context,Home_Activity.class));
               finish();
                break;

            case  R.id.fav:
                startActivity(new Intent(context,FavouriteActivity.class));
                finish();
                break;

            case R.id.iv_profile:
                startActivity(new Intent(context,ProfileActivity.class));
                break;
            case R.id.iv_back:
                Container2.setVisibility(View.GONE);
                Container1.setVisibility(View.VISIBLE);
                layout_search.setVisibility(View.GONE);
                starlayout.setVisibility(View.VISIBLE);
                hideSoftKeyBoard(StarActivity.this);
                backpress();
                break;
            case R.id.iv_notification:
                startActivity(new Intent(StarActivity.this,NotificationActivity.class));

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
