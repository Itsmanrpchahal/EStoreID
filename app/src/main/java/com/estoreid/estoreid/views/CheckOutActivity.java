package com.estoreid.estoreid.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        ButterKnife.bind(this);

        backontoolbar.setVisibility(View.VISIBLE);
        toolbartitle.setVisibility(View.VISIBLE);
        toolbartitle.setText(R.string.checkout);


        paymentMethodAdapter=new PaymentMethodAdapter(this);
        paymentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        paymentRecyclerView.setAdapter(paymentMethodAdapter);

    }
}
