package com.estoreid.estoreid.views;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.ProductAdapter;
import com.estoreid.estoreid.views.apiResponseModel.ProductsAPI;
import com.estoreid.estoreid.views.controller.Controller;
import com.estoreid.estoreid.views.filter.FilterScreen;
import com.estoreid.estoreid.views.utils.Constants;
import com.estoreid.estoreid.views.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class Products_Screen extends BaseActivity implements Controller.Products {

    ProductAdapter adapter;
    Intent intent;
    String type = "list";
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
    @BindView(R.id.product_serach)
    ImageButton productSerach;
    @BindView(R.id.product_cart)
    ImageButton productCart;
    @BindView(R.id.toolbar_layout)
    RelativeLayout toolbarLayout;
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
    ArrayList<ProductsAPI.Datum> products = new ArrayList<>();
    ArrayList<ProductsAPI.Datum> brands = new ArrayList<>();
    String vendor_id;
    Controller controller;
    android.app.Dialog Dialog;
    @BindView(R.id.totalproducts)
    TextView totalproducts;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        @SuppressLint("InflateParams")
        View contentView = inflater.inflate(R.layout.activity_products__screen, null, false);
        controller = new Controller(this);
        drawer.addView(contentView, 0);
        ButterKnife.bind(this);
        Dialog = Utils.showDialog(this);
        Dialog.show();
        productTv.setFocusable(true);
        itemsRecyclerView.setFocusable(false);
        searchEt.setVisibility(View.GONE);
        listeners();
        intent = getIntent();
        if (intent != null) {
            vendor_id = intent.getStringExtra("vendor_id");
            type = intent.getStringExtra("type");
            controller.Products("Bearer " + getStringVal(Constants.TOKEN), vendor_id);

        }

    }

    @SuppressLint("WrongConstant")
    private void setListGrid(String type, ArrayList<ProductsAPI.Datum> products) {
        if (type.equals("list")) {
            LinearLayoutManager linearLayout = new LinearLayoutManager(this);
            productListview.setImageDrawable(getResources().getDrawable(R.drawable.listview_active));
            productGrid.setImageDrawable(getResources().getDrawable(R.drawable.grid));

            linearLayout.setOrientation(LinearLayout.VERTICAL);
            itemsRecyclerView.setHasFixedSize(true);
            itemsRecyclerView.setLayoutManager(linearLayout);
            adapter = new ProductAdapter(this, type, products);
            itemsRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            productListview.setImageDrawable(getResources().getDrawable(R.drawable.listview));
            productGrid.setImageDrawable(getResources().getDrawable(R.drawable.grid_active));

            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            itemsRecyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
            adapter = new ProductAdapter(this, type, products);
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
                adapter = new ProductAdapter(getApplicationContext(), type, products);
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
                adapter = new ProductAdapter(getApplicationContext(), type, products);
                itemsRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        productSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Products_Screen.this, FilterScreen.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

        }
    }


    @Override
    public void onSuccessProduct(Response<ProductsAPI> productsAPIResponse) {
        Dialog.dismiss();
        if (productsAPIResponse.body().getStatus() == 200) {
            for (int i = 0; i < productsAPIResponse.body().getData().size(); i++) {
                ProductsAPI.Datum datum = productsAPIResponse.body().getData().get(i);
                products.add(datum);
                setListGrid(type, products);

            }
            totalproducts.setText(String.valueOf(products.size()));
        } else {
            Utils.showToastMessage(Products_Screen.this, productsAPIResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_black_24dp));
        }


    }

    @Override
    public void onError(String error) {
        Dialog.dismiss();
        Utils.showToastMessage(Products_Screen.this, error, getResources().getDrawable(R.drawable.ic_error_black_24dp));
    }

}
