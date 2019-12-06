package com.estoreid.estoreid.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.OrderDiscountAdapter;
import com.estoreid.estoreid.views.utils.Constants;
import com.estoreid.estoreid.views.utils.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.internal.Util;

public class MyOrderDetailsActivity extends BaseActivity {

    String text_string = "";


    OrderDiscountAdapter orderDiscountAdapter;
    @BindView(R.id.orderCancel)
    Button orderCancel;
    @BindView(R.id.layout_main)
    RelativeLayout layoutMain;
    @BindView(R.id.tv_OrderName)
    TextView tvOrderName;
    @BindView(R.id.tv_OrderPrice)
    TextView tvOrderPrice;
    @BindView(R.id.tv_devlerStatus)
    TextView tvDevlerStatus;
    @BindView(R.id.tv_orderTimming)
    TextView tvOrderTimming;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_totalPrice)
    TextView tvTotalPrice;
    @BindView(R.id.tv_OrderID)
    TextView tvOrderID;
    @BindView(R.id.tv_orderStatus)
    TextView tvOrderStatus;
    @BindView(R.id.tv_orderTiming)
    TextView tvOrderTiming;
    @BindView(R.id.recycler_order)
    RecyclerView recyclerOrder;
    @BindView(R.id.orders_recyclerview)
    RelativeLayout ordersRecyclerview;
    @BindView(R.id.profile_back)
    ImageButton profileBack;
    @BindView(R.id.include_toolbar)
    Toolbar includeToolbar;
    @BindView(R.id.total_price_layout)
    RelativeLayout totalPriceLayout;
    @BindView(R.id.qr_text)
    TextView qrText;
    @BindView(R.id.qr_text1)
    TextView qrText1;
    @BindView(R.id.qr_image)
    ImageView qrImage;
    @BindView(R.id.qr_share)
    Button qrShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_details);
        ButterKnife.bind(this);

        profileBack.setVisibility(View.VISIBLE);

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
        Glide.with(context).load(R.drawable.qr).into(qrImage);
        profileBack.setOnClickListener(new View.OnClickListener() {
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

        qrShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.shareContent(MyOrderDetailsActivity.this,qrImage);
            }
        });
    }
}
