package com.estoreid.estoreid.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.FavoriteStoreAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoriteStoreActivity extends AppCompatActivity {


    FavoriteStoreAdapter favoriteStoreAdapter;
    @BindView(R.id.recycler_favoriteStore)
    RecyclerView recyclerFavoriteStore;
    @BindView(R.id.back_onfav_store)
    ImageButton backOnfavStore;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite_store);
        ButterKnife.bind(this);


        favoriteStoreAdapter = new FavoriteStoreAdapter(this);
        recyclerFavoriteStore.setLayoutManager(new LinearLayoutManager(this));
        recyclerFavoriteStore.setAdapter(favoriteStoreAdapter);

        backOnfavStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
