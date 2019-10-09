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
import com.estoreid.estoreid.views.adapter.PaymentMethodAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoriteStoreActivity extends AppCompatActivity {

    @BindView(R.id.recycler_favoriteStore)
    RecyclerView recyclerFavoriteStore;
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

    FavoriteStoreAdapter favoriteStoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite_store);
        ButterKnife.bind(this);

        backontoolbar.setVisibility(View.VISIBLE);
        toolbartitle.setVisibility(View.VISIBLE);
        toolbartitle.setText(R.string.favorite_store);

        favoriteStoreAdapter = new FavoriteStoreAdapter(this);
        recyclerFavoriteStore.setLayoutManager(new LinearLayoutManager(this));
        recyclerFavoriteStore.setAdapter(favoriteStoreAdapter);

        backontoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
