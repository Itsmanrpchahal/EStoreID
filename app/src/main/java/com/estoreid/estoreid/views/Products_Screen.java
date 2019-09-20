package com.estoreid.estoreid.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.ProductAdapter;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Products_Screen extends AppCompatActivity {

    ProductAdapter adapter;
    @BindView(R.id.product_tv)
    TextView productTv;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.product_listview)
    ImageButton productListview;
    @BindView(R.id.product_grid)
    ImageButton productGrid;
    @BindView(R.id.product_sort)
    ImageButton productSort;
    @BindView(R.id.view2)
    LinearLayout view2;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.items_recycler_view)
    RecyclerView itemsRecyclerView;
    String type = "list";
    @BindView(R.id.serach_et)
    EditText serachEt;
    @BindView(R.id.serach_ib)
    ImageButton serachIb;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    ActionBarDrawerToggle actionBarDrawerToggle;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products__screen);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, MainActivity.drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        MainActivity.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        this.toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        listeners();

        if (type.equals("list")) {
            LinearLayoutManager linearLayout = new LinearLayoutManager(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            itemsRecyclerView.setHasFixedSize(true);
            itemsRecyclerView.setLayoutManager(linearLayout);
            adapter = new ProductAdapter(this, type);
            itemsRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            itemsRecyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
            adapter = new ProductAdapter(this, type);
            itemsRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    private void listeners() {

        productGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "grid";
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                itemsRecyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
                adapter = new ProductAdapter(getApplicationContext(), type);
                itemsRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        productListview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "list";
            }
        });

        MainActivity.navigationView.setItemIconTintList(null);
        MainActivity.navigationView.setVerticalScrollBarEnabled(false);
        MainActivity.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        });
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {

        if (MainActivity.drawerLayout.isDrawerOpen(Gravity.START)) {
            MainActivity.drawerLayout.closeDrawer(Gravity.START);
        } else {
            super.onBackPressed();
        }
    }

}
