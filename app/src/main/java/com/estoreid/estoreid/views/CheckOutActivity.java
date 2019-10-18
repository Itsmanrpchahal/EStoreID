package com.estoreid.estoreid.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.PaymentMethodAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckOutActivity extends AppCompatActivity {

    @BindView(R.id.payment_recyclerView)
    RecyclerView paymentRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.backontoolbar)
    ImageButton backontoolbar;

    @BindView(R.id.toolbartitle)
    TextView toolbartitle;

    PaymentMethodAdapter paymentMethodAdapter;
    @BindView(R.id.btn_payment)
    Button btnPayment;
    @BindView(R.id.tv_paymentMethod)
    TextView tvPaymentMethod;
    @BindView(R.id.layout_paymentMethod)
    RelativeLayout layoutPaymentMethod;
    @BindView(R.id.img_checked)
    ImageView imgChecked;
    @BindView(R.id.tv_placeAddress)
    TextView tvPlaceAddress;
    @BindView(R.id.placeAddress)
    TextView placeAddress;
    @BindView(R.id.layout_address)
    RelativeLayout layoutAddress;
    @BindView(R.id.tv_deleveryAddress)
    TextView tvDeleveryAddress;
    @BindView(R.id.layout_deliveryAddress)
    RelativeLayout layoutDeliveryAddress;
    @BindView(R.id.layout_main)
    RelativeLayout layoutMain;
    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.cart_toolbar)
    ImageButton cartToolbar;
    @BindView(R.id.serach_toolbar)
    ImageButton serachToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        ButterKnife.bind(this);

        backontoolbar.setVisibility(View.VISIBLE);
        toolbartitle.setVisibility(View.VISIBLE);
        toolbartitle.setText(R.string.checkout);


        paymentMethodAdapter = new PaymentMethodAdapter(this);
        paymentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        paymentRecyclerView.setAdapter(paymentMethodAdapter);


        listeners();

    }

    private void listeners() {
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckOutActivity.this,OrderPlaced.class);
                startActivity(intent);
            }
        });
    }
}
