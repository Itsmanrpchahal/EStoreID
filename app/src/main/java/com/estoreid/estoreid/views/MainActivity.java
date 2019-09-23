package com.estoreid.estoreid.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
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

public class MainActivity extends BaseActivity {

    public static ActionBarDrawerToggle actionBarDrawerToggle;


    DashBoardShopAdapter adapter;
    @BindView(R.id.cart_toolbar)
    ImageButton cartToolbar;
    @BindView(R.id.serach_toolbar)
    ImageButton serachToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
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


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        @SuppressLint("InflateParams")
        View contentView = inflater.inflate(R.layout.activity_main, null, false);
        drawer.addView(contentView, 0);
        ButterKnife.bind(this);
//        Utils.abc(serachEt, MainActivity.this);

        setAdapter();
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

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();
            } else {
                super.onBackPressed();
            }
        }
    }

}
