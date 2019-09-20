package com.estoreid.estoreid.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.DashBoardShopAdapter;
import com.estoreid.estoreid.views.utils.AppMapView;
import com.estoreid.estoreid.views.utils.Utils;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

   public static ActionBarDrawerToggle actionBarDrawerToggle;

    public static NavigationView navigationView;
    public static Toolbar toolbar;
    public static DrawerLayout drawerLayout;

    DashBoardShopAdapter adapter;
//    @BindView(R.id.navigation_view)
//    public static NavigationView navigationView;
    ImageButton serachToolbar;
    @BindView(R.id.mapview)
    AppMapView mapview;
    @BindView(R.id.your_loc_tv)
    TextView yourLocTv;
    @BindView(R.id.loaction_tv)
    TextView loactionTv;
    @BindView(R.id.change_address)
    TextView changeAddress;
    @BindView(R.id.location_view)
    CardView locationView;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.sortby_tv)
    TextView sortbyTv;
    @BindView(R.id.sortimage)
    ImageView sortimage;
    @BindView(R.id.sort_layout)
    RelativeLayout sortLayout;
    @BindView(R.id.filter_tv)
    TextView filterTv;
    @BindView(R.id.filterimage)
    ImageView filterimage;
    @BindView(R.id.filter_layout)
    RelativeLayout filterLayout;
    @BindView(R.id.view2)
    LinearLayout view2;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.dashboard_recylerview)
    RecyclerView dashboardRecylerview;
    @BindView(R.id.item_layout)
    RelativeLayout itemLayout;
    @BindView(R.id.houseno)
    EditText houseno;
    @BindView(R.id.add_view1)
    View addView1;
    @BindView(R.id.street)
    EditText street;
    @BindView(R.id.add_view2)
    View addView2;
    @BindView(R.id.city)
    EditText city;
    @BindView(R.id.add_view3)
    View addView3;
    @BindView(R.id.pincode)
    EditText pincode;
    @BindView(R.id.add_view4)
    View addView4;
//    @BindView(R.id.drawer_layout)
//    DrawerLayout drawerLayout;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    @BindView(R.id.serach_et)
    EditText serachEt;
    @BindView(R.id.serach_ib)
    ImageButton serachIb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Utils.abc(serachEt, MainActivity.this);

        init();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        this.toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));


        listeners();
        setAdapter();
    }

    private void init() {
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    private void listeners() {

        navigationView.setItemIconTintList(null);
        navigationView.setVerticalScrollBarEnabled(false);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        });

    }

    @SuppressLint("WrongConstant")
    private void setAdapter() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        dashboardRecylerview.setHasFixedSize(true);
        dashboardRecylerview.setLayoutManager(linearLayout);
        adapter = new DashBoardShopAdapter(this);
        dashboardRecylerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        } else {
            super.onBackPressed();
        }
    }
}
