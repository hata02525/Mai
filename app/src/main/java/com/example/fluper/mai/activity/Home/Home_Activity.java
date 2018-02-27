package com.example.fluper.mai.activity.Home;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.fluper.mai.Helper.GPSTracker;
import com.example.fluper.mai.Helper.MapHelper;
import com.example.fluper.mai.R;
import com.example.fluper.mai.activity.NotificationActivity;
import com.example.fluper.mai.activity.ProfileActivity;
import com.example.fluper.mai.adapter.AdapterBottom;
import com.example.fluper.mai.adapter.AdapterList;
import com.example.fluper.mai.adapter.AdapterSearch;
import com.example.fluper.mai.adapter.AdapterTop;
import com.example.fluper.mai.adapter.CategoryAdapter;
import com.example.fluper.mai.adapter.CityAdapter;
import com.example.fluper.mai.modal.Categorylist;
import com.example.fluper.mai.modal.ListModel;
import com.example.fluper.mai.modal.ListStore;
import com.example.fluper.mai.utils.CategoryListData;
import com.example.fluper.mai.utils.DataStore;
import com.example.fluper.mai.utils.MerchantStore;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Home_Activity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    private RelativeLayout Container1;
    private RelativeLayout Container2;
    private boolean searchback=true;
    private static RecyclerView recycler_category;
    private boolean doubleBackToExitPressedOnce = false;
    private static RecyclerView recycler_city;
    private LinearLayout layout_search;
    private static LinearLayout dialog_layout;
    private static LinearLayout city_dialog_layout;
    private RelativeLayout frame;
    private static RelativeLayout opencat;
    private EditText ed_search;
    private ImageView leftarrowdialog;
    private ImageView search;
    private ImageView back;
    private ImageView profile;
    private ImageView leftarrow;
    private ImageView fav;
    private ImageView star;
    private ImageView iv_notification;
    private ImageView cross;
    private static ImageView imagev;
    private static Context context;
    private RecyclerView recyclerView, recyclerViewtop, recyclerViewsearch, recycler_list;
    private AdapterBottom adapterBottom;
    private AdapterTop adapterTop;
    private AdapterSearch adapterSearch;
    private AdapterList adapterList;
    private CategoryAdapter categoryadaapter;
    public static final int PERMISSIONS_MULTIPLE_REQUEST = 1;
    ArrayList<ListModel> list = new ArrayList<>();
    ArrayList<ListStore> liststore = new ArrayList<>();
    static ArrayList<Categorylist> catlist=new ArrayList<>();
    Activity activity;
    private GoogleMap map;
    GPSTracker gps;
    MapHelper mapHelper;
    String lt_current,lg_current;
    LatLng paniclatlong;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        checkAndroidVersion();
        context = this;
        activity = Home_Activity.this;
        gps = new GPSTracker(this);
        init();
        listener();
        setAdapterData();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }
    private void getcurrentloc() {


        if (gps.canGetLocation()) {
            lt_current = String.valueOf(gps.getLatitude());
            lg_current = String.valueOf(gps.getLongitude());
        }
    }

    private void listener() {
        search.setOnClickListener(this);
        back.setOnClickListener(this);
        profile.setOnClickListener(this);
        leftarrow.setOnClickListener(this);
        star.setOnClickListener(this);
        iv_notification.setOnClickListener(this);
        fav.setOnClickListener(this);
        frame.setOnClickListener(this);
    }

    private void init() {
        Container1 = (RelativeLayout) findViewById(R.id.rl_container);
        Container2 = (RelativeLayout) findViewById(R.id.rl_container2);
        frame = (RelativeLayout) findViewById(R.id.for_map);
        layout_search = (LinearLayout) findViewById(R.id.layout_search);

        dialog_layout = (LinearLayout) findViewById(R.id.dialog_layout);
        dialog_layout.setVisibility(View.GONE);

        city_dialog_layout = (LinearLayout) findViewById(R.id.citylayout);
        city_dialog_layout.setVisibility(View.GONE);

        Container1.setVisibility(View.VISIBLE);

        profile = findViewById(R.id.iv_profile);
        search = (ImageView) findViewById(R.id.iv_search);
        star = (ImageView) findViewById(R.id.star);
        opencat=(RelativeLayout)findViewById(R.id.opencat);
        back = (ImageView) findViewById(R.id.iv_back);
        imagev=(ImageView)findViewById(R.id.imagev);
        leftarrow = (ImageView) findViewById(R.id.leftarrow);
        leftarrowdialog=(ImageView)findViewById(R.id.leftarrowdialog);
        fav = (ImageView) findViewById(R.id.fav);
        iv_notification=(ImageView)findViewById(R.id.iv_notification);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerViewtop = (RecyclerView) findViewById(R.id.recyclerviewtop);
        recyclerViewsearch = (RecyclerView) findViewById(R.id.recyclerviewfor_search);
        recycler_list = (RecyclerView) findViewById(R.id.recycler_list);
        cross=(ImageView)findViewById(R.id.cross);
        ed_search=(EditText)findViewById(R.id.ed_search);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        showcurrentlocation();
        drawcircle();

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

               dialog_layout.setVisibility(View.GONE);
               city_dialog_layout.setVisibility(View.GONE);

            }
        });
//        map.setMyLocationEnabled(true);
//        map.setMaxZoomPreference(15);
//        LatLng sydney = new LatLng(-33.852, 151.211);
//        map.addMarker(new MarkerOptions().position(sydney)
//                .title("Marker in Sydney"));
//         map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        map.animateCamera(CameraUpdateFactory.zoomTo(11));
//        drawCircle(new LatLng(-33.852, 151.211));

    }

    private void drawcircle() {

        Circle circle = map.addCircle(new CircleOptions()
                .center(paniclatlong)
                .radius(1000)
                .strokeColor(getResources().getColor(R.color.base))
                .strokeWidth(3)
                .fillColor(Color.TRANSPARENT));
    }

    private void showcurrentlocation() {
        getcurrentloc();
        paniclatlong = new LatLng(Double.valueOf(lt_current),Double.valueOf(lg_current));
        Marker panicmarker = map.addMarker(new MarkerOptions().position(paniclatlong));
      panicmarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.location_pin));
        map.getUiSettings().setZoomControlsEnabled(true);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(paniclatlong).tilt(30).zoom(14.0f).bearing(300).build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        map.addPolygon(MapHelper.createPolygonWithCircle(this,paniclatlong, 1));
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

    private void setAdapterData() {
        list = DataStore.StringData();
        liststore = MerchantStore.StringData();
        Log.e("ftf", "setAdapterData: " + list.size());
        recyclerView.setHasFixedSize(true);
        recyclerView.invalidate();
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        adapterBottom = new AdapterBottom(context, list);
        recyclerView.setAdapter(adapterBottom);

        recycler_category = (RecyclerView) findViewById(R.id.recycler_category);
        recycler_city=(RecyclerView)findViewById(R.id.recycler_city);

        recyclerViewtop.setHasFixedSize(true);
        recyclerViewtop.invalidate();
        recyclerViewtop.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        adapterTop = new AdapterTop(context, list, activity);
        recyclerViewtop.setAdapter(adapterTop);

    }


    private void checkAndroidVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        } else {

        }

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) + ContextCompat
                .checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (this, Manifest.permission.ACCESS_COARSE_LOCATION) ||
                    ActivityCompat.shouldShowRequestPermissionRationale
                            (this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                requestPermissions(
                        new String[]{Manifest.permission
                                .ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_MULTIPLE_REQUEST);


            } else {
                requestPermissions(
                        new String[]{Manifest.permission
                                .ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_MULTIPLE_REQUEST);
            }
        } else {
            // write your logic code if permission already granted
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case PERMISSIONS_MULTIPLE_REQUEST:
                if (grantResults.length > 0) {
                    boolean CoarseLocation = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean FineLocation = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (CoarseLocation && FineLocation) {

                        // write your logic here
                    } else {
                        requestPermissions(
                                new String[]{Manifest.permission
                                        .ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_MULTIPLE_REQUEST);
                    }
                }
                break;
        }
    }


    @Override
    public void onBackPressed() {
        if (searchback){
            backpresh();
        } else {
            searchback=true;
            Container2.setVisibility(View.GONE);
            Container1.setVisibility(View.VISIBLE);
            dialog_layout.setVisibility(View.GONE);
            city_dialog_layout.setVisibility(View.GONE);
            frame.setVisibility(View.VISIBLE);
        }


    }

    private void backpresh() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finishAffinity();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this,R.string.backagain, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                searchback=false;
                loaddata();
                Container1.setVisibility(View.GONE);
                frame.setVisibility(View.GONE);
                layout_search.setVisibility(View.VISIBLE);
                Container2.setVisibility(View.VISIBLE);
                ed_search.setText("");
                showSoftKeyBoard(this);
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
                            hideSoftKeyBoard(Home_Activity.this);
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
                        hideSoftKeyBoard(Home_Activity.this);
                        ed_search.setText("");
                        recycler_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                        adapterList = new AdapterList(activity, liststore);
                        recycler_list.setAdapter(adapterList);

                    }
                });




                break;
            case R.id.iv_back:
                searchback=true;
                Container2.setVisibility(View.GONE);
                Container1.setVisibility(View.VISIBLE);
                dialog_layout.setVisibility(View.GONE);
                city_dialog_layout.setVisibility(View.GONE);
                frame.setVisibility(View.VISIBLE);
                hideSoftKeyBoard(this);
                break;
            case R.id.iv_profile:
                Intent i = new Intent(Home_Activity.this, ProfileActivity.class);
                startActivity(i);
               break;

            case R.id.fav:
                Intent it=new Intent(Home_Activity.this, FavouriteActivity.class);
                startActivity(it);
                finish();
                break;
            case R.id.star:
                Intent itt=new Intent(Home_Activity.this, StarActivity.class);
                startActivity(itt);
                finish();
                break;
            case R.id.iv_notification:
                Intent in=new Intent(Home_Activity.this,NotificationActivity.class);
                startActivity(in);
                break;
            case R.id.leftarrowdialog:
                city_dialog_layout.setVisibility(View.GONE);
                dialog_layout.setVisibility(View.VISIBLE);
                break;
            case R.id.for_map:
                city_dialog_layout.setVisibility(View.GONE);
                dialog_layout.setVisibility(View.GONE);
                break;
        }
    }


    public static void openDialog() {
        catlist= CategoryListData.StringData();
        city_dialog_layout.setVisibility(View.GONE);
        dialog_layout.setVisibility(View.VISIBLE);
        recycler_category.setHasFixedSize(true);
        recycler_category.invalidate();
        recycler_category.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        CategoryAdapter categoryAdapter = new CategoryAdapter(context,catlist);
        recycler_category.setAdapter(categoryAdapter);


    }

    public static void openCityDialog()
    {
        dialog_layout.setVisibility(View.GONE);
        city_dialog_layout.setVisibility(View.VISIBLE);
        recycler_city.setHasFixedSize(true);
        recycler_city.invalidate();
        recycler_city.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        CityAdapter cityAdapter=new CityAdapter(context);
        recycler_city.setAdapter(cityAdapter);

        opencat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

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

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        InputMethodManager imm ;
//        View view = this.getCurrentFocus();
//        if (view != null) {
//            imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//        return true;
//    }

}
