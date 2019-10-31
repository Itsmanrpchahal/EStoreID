package com.estoreid.estoreid.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.MyOrderAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderActivity extends AppCompatActivity {


    @BindView(R.id.recycler_order)
    RecyclerView recyclerOrder;
    @BindView(R.id.back_onfav_store)
    ImageButton backOnfavStore;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    MyOrderAdapter myOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);


        myOrderAdapter = new MyOrderAdapter(this);
        recyclerOrder.setLayoutManager(new LinearLayoutManager(this));
        recyclerOrder.setAdapter(myOrderAdapter);

        backOnfavStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
