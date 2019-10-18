package com.estoreid.estoreid.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.CartAddedItemAdapter;
import com.estoreid.estoreid.views.adapter.ReletedProductAdpater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Cart_Activity extends BaseActivity {


    @BindView(R.id.toolbar_back)
    ImageButton toolbarBack;
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
    @BindView(R.id.placeorder)
    Button placeorder;
    @BindView(R.id.scrollview)
    ScrollView scrollview;
    @BindView(R.id.safetext)
    TextView safetext;
    ReletedProductAdpater reletedProductAdpater;
    CartAddedItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_);
        ButterKnife.bind(this);
        additemRecyclerviw.setFocusable(false);
        setAdapter();
        listeners();
    }

    private void listeners() {
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart_Activity.this, CheckOutActivity.class);
                startActivity(intent);
            }
        });

        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
