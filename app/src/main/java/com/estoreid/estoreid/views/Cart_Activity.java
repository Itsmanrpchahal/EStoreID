package com.estoreid.estoreid.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.CartAddedItemAdapter;
import com.estoreid.estoreid.views.adapter.ReletedProductAdpater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Cart_Activity extends BaseActivity {

    @BindView(R.id.cart_toolbar)
    ImageButton cartToolbar;
    @BindView(R.id.serach_toolbar)
    ImageButton serachToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.carttext)
    TextView carttext;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.cart_itemscount)
    TextView cartItemscount;
    @BindView(R.id.additem_recyclerviw)
    RecyclerView additemRecyclerviw;
    @BindView(R.id.sub_total_tv)
    TextView subTotalTv;
    @BindView(R.id.sub_total_tv1)
    TextView subTotalTv1;
    @BindView(R.id.tax_tv)
    TextView taxTv;
    @BindView(R.id.tax_tv1)
    TextView taxTv1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.total_tv)
    TextView totalTv;
    @BindView(R.id.total_tv1)
    TextView totalTv1;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.relatedproduct_tv)
    TextView relatedproductTv;
    @BindView(R.id.related_item_recyclerview)
    RecyclerView relatedItemRecyclerview;
    @BindView(R.id.cardview)
    CardView cardview;
    CartAddedItemAdapter adapter;
    ReletedProductAdpater reletedProductAdpater;
    @BindView(R.id.placeorder)
    Button placeorder;
    @BindView(R.id.safetext)
    TextView safetext;
    @BindView(R.id.scrollview)
    ScrollView scrollview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        @SuppressLint("InflateParams")
        View contentView = inflater.inflate(R.layout.activity_cart_, null, false);
        drawer.addView(contentView, 0);
        ButterKnife.bind(this);
        additemRecyclerviw.setFocusable(false);
        setAdapter();
        listeners();
    }

    private void listeners() {
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart_Activity.this, OrderPlaced.class);
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();
            } else {
                super.onBackPressed();
            }
        }
    }

    @SuppressLint("WrongConstant")
    private void setAdapter() {

        //Cart Addet items
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        additemRecyclerviw.setHasFixedSize(true);
        additemRecyclerviw.setLayoutManager(linearLayout);
        adapter = new CartAddedItemAdapter(this);
        additemRecyclerviw.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //Releted Items
        LinearLayoutManager linearLayout1 = new LinearLayoutManager(this);
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        relatedItemRecyclerview.setHasFixedSize(true);
        relatedItemRecyclerview.setLayoutManager(linearLayout1);
        reletedProductAdpater = new ReletedProductAdpater(this);
        relatedItemRecyclerview.setAdapter(reletedProductAdpater);
        reletedProductAdpater.notifyDataSetChanged();
    }
}
