package com.estoreid.estoreid.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.OrderDiscountAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderDetailsActivity extends AppCompatActivity {

    String text_string = "";
    @BindView(R.id.tv_OrderID)
    TextView tvOrderID;
    @BindView(R.id.tv_orderStatus)
    TextView tvOrderStatus;
    @BindView(R.id.tv_orderTiming)
    TextView tvOrderTiming;
    @BindView(R.id.recycler_order)
    RecyclerView recyclerOrder;
    @BindView(R.id.backontoolbar)
    ImageButton backontoolbar;
    @BindView(R.id.toolbartitle)
    TextView toolbartitle;
    @BindView(R.id.cart_toolbar)
    ImageButton cartToolbar;
    @BindView(R.id.serach_toolbar)
    ImageButton serachToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    OrderDiscountAdapter orderDiscountAdapter;
    @BindView(R.id.orderCancel)
    Button orderCancel;
    @BindView(R.id.layout_main)
    RelativeLayout layoutMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_details);
        ButterKnife.bind(this);

        backontoolbar.setVisibility(View.VISIBLE);

        text_string = getIntent().getStringExtra("text_string");
        tvOrderStatus.setText(text_string);
        if (text_string.equalsIgnoreCase("On it's way")) {
            tvOrderStatus.setTextColor(Color.parseColor("#FDD835"));

        } else if (text_string.equalsIgnoreCase("Delivered")) {
            tvOrderStatus.setTextColor(Color.parseColor("#63BB56"));

        } else if (text_string.equalsIgnoreCase("Canceled")) {
            tvOrderStatus.setTextColor(Color.parseColor("#D81B60"));
        }

        orderDiscountAdapter = new OrderDiscountAdapter(this);
        recyclerOrder.setLayoutManager(new LinearLayoutManager(this));
        recyclerOrder.setAdapter(orderDiscountAdapter);

        backontoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        orderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrderDetailsActivity.this, CancelOrderActivity.class);
                startActivity(intent);
            }
        });
    }
}
