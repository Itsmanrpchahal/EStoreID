package com.estoreid.estoreid.views;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.OrderDiscountAdapter;
import com.estoreid.estoreid.views.apiResponseModel.GetBookOrderDetail;
import com.estoreid.estoreid.views.controller.Controller;
import com.estoreid.estoreid.views.utils.Constants;
import com.estoreid.estoreid.views.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class MyOrderDetailsActivity extends BaseActivity implements Controller.GetBookedOrderData {

    String text_string = "", order_id,total_amount;
    OrderDiscountAdapter orderDiscountAdapter;
    @BindView(R.id.orderCancel)
    Button orderCancel;
    @BindView(R.id.layout_main)
    RelativeLayout layoutMain;
    @BindView(R.id.tv_OrderName)
    TextView tvOrderName;
    @BindView(R.id.tv_SubPrice)
    TextView tvSubPrice;
    @BindView(R.id.tv_devlerStatus)
    TextView tvDevlerStatus;
    @BindView(R.id.tv_delivery1)
    TextView tv_delivery1;
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
    @BindView(R.id.track_order)
    Button track_order;
    Controller controller;
    Dialog Dialog;
    ArrayList<GetBookOrderDetail.Datum> orders = new ArrayList<GetBookOrderDetail.Datum>();
    Intent intent = getIntent();
    @BindView(R.id.tv_tax)
    TextView tvTax;
    @BindView(R.id.tv_tax1)
    TextView tvTax1;
    double sum =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_details);
        ButterKnife.bind(this);
        Dialog = Utils.showDialog(this);
        Dialog.show();
        controller = new Controller(this);
        profileBack.setVisibility(View.VISIBLE);

        text_string = getIntent().getStringExtra("text_string");
        order_id = getIntent().getStringExtra("order_id");
        total_amount = getIntent().getStringExtra("total_amount");
        tvOrderStatus.setText(text_string);
        if (text_string.equalsIgnoreCase("On it's way")) {
            tvOrderStatus.setTextColor(Color.parseColor("#FDD835"));

        } else if (text_string.equalsIgnoreCase("Delivered")) {
            tvOrderStatus.setTextColor(Color.parseColor("#63BB56"));

        } else if (text_string.equalsIgnoreCase("Canceled")) {
            tvOrderStatus.setTextColor(Color.parseColor("#D81B60"));
        }

        if (Utils.isOnline() != false) {
            Dialog.show();
            controller.GetBookedOrderData("Bearer " + getStringVal(Constants.TOKEN), order_id);
        } else {
            Dialog.dismiss();
            Utils.showToastMessage(MyOrderDetailsActivity.this, "No Internet Connection", getResources().getDrawable(R.drawable.ic_nointernet));
        }


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
                Utils.shareContent(MyOrderDetailsActivity.this, qrImage);
            }
        });
    }

    @Override
    public void onSuccessBookedOrderData(Response<GetBookOrderDetail> getBookedOrderDataResponse) {
        Dialog.dismiss();
        if (getBookedOrderDataResponse.body().getStatus() == 200) {
            for (int i = 0; i < getBookedOrderDataResponse.body().getData().size(); i++) {
                GetBookOrderDetail.Datum datum = getBookedOrderDataResponse.body().getData().get(i);
                orders.add(datum);
                tvTax1.setText("%"+getBookedOrderDataResponse.body().getTaxPrice().toString());
                tvOrderID.setText("Order #"+datum.getOrderId().toString());
                tvSubPrice.setText("₹"+getBookedOrderDataResponse.body().getSubtotal().toString());
                tv_delivery1.setText("₹"+getBookedOrderDataResponse.body().getDeliveryPrice().toString());
                tvTotalPrice.setText("₹"+total_amount);


                setAdapter(orders);
            }

        }
    }


    @Override
    public void onError(String error) {
        Dialog.dismiss();
        Utils.showToastMessage(MyOrderDetailsActivity.this, error, getResources().getDrawable(R.drawable.ic_error_black_24dp));
    }

    private void setAdapter(ArrayList<GetBookOrderDetail.Datum> datum) {
        orderDiscountAdapter = new OrderDiscountAdapter(this, datum);
        recyclerOrder.setLayoutManager(new LinearLayoutManager(this));
        recyclerOrder.setAdapter(orderDiscountAdapter);

    }
}
