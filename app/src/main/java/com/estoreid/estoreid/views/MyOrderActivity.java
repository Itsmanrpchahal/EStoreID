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
import com.estoreid.estoreid.views.adapter.FavoriteStoreAdapter;
import com.estoreid.estoreid.views.adapter.MyOrderAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderActivity extends AppCompatActivity {

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
    MyOrderAdapter myOrderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);

        backontoolbar.setVisibility(View.VISIBLE);
        toolbartitle.setVisibility(View.VISIBLE);
        toolbartitle.setText(R.string.my_orders);

        myOrderAdapter = new MyOrderAdapter(this);
        recyclerOrder.setLayoutManager(new LinearLayoutManager(this));
        recyclerOrder.setAdapter(myOrderAdapter);

        backontoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
