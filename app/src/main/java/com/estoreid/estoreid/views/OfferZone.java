package com.estoreid.estoreid.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.OfferRecyclerview;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OfferZone extends AppCompatActivity {

    @BindView(R.id.offerzoneback)
    ImageButton offerzoneback;
    @BindView(R.id.offerrecyler)
    RecyclerView offerrecyler;
    OfferRecyclerview offerRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_zone);
        ButterKnife.bind(this);

        listerners();
        setAdapter();
    }

    private void setAdapter() {
        offerRecyclerview = new OfferRecyclerview(this);
        offerrecyler.setLayoutManager(new LinearLayoutManager(this));
        offerrecyler.setAdapter(offerRecyclerview);
    }

    private void listerners() {

        offerzoneback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
