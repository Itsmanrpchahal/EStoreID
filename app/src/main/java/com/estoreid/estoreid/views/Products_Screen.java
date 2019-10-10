package com.estoreid.estoreid.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.ProductAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Products_Screen extends BaseActivity {

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

    ActionBarDrawerToggle actionBarDrawerToggle;
    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.backontoolbar)
    ImageButton backontoolbar;
    @BindView(R.id.cart_toolbar)
    ImageButton cartToolbar;
    @BindView(R.id.serach_toolbar)
    ImageButton serachToolbar;
    @BindView(R.id.toolbartitle)
    TextView toolbartitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        @SuppressLint("InflateParams")
        View contentView = inflater.inflate(R.layout.activity_products__screen, null, false);
        drawer.addView(contentView, 0);
        ButterKnife.bind(this);
        searchEt.setVisibility(View.GONE);
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
                productListview.setImageDrawable(getResources().getDrawable(R.drawable.listview));
                productGrid.setImageDrawable(getResources().getDrawable(R.drawable.grid_active));
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
                productListview.setImageDrawable(getResources().getDrawable(R.drawable.listview_active));
                productGrid.setImageDrawable(getResources().getDrawable(R.drawable.grid));
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                itemsRecyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
                adapter = new ProductAdapter(getApplicationContext(), type);
                itemsRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

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
