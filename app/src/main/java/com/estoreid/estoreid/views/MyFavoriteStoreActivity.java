package com.estoreid.estoreid.views;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.FavoriteStoreAdapter;
import com.estoreid.estoreid.views.adapter.GetVendorIDIF;
import com.estoreid.estoreid.views.apiResponseModel.FavVendorsResponse;
import com.estoreid.estoreid.views.apiResponseModel.FollowAPIResponse;
import com.estoreid.estoreid.views.controller.Controller;
import com.estoreid.estoreid.views.utils.Constants;
import com.estoreid.estoreid.views.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class MyFavoriteStoreActivity extends BaseActivity implements Controller.FavStore,Controller.FollowUnfollow{


    FavoriteStoreAdapter favoriteStoreAdapter;
    @BindView(R.id.recycler_favoriteStore)
    RecyclerView recyclerFavoriteStore;
    @BindView(R.id.back_onfav_store)
    ImageButton backOnfavStore;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    ArrayList<FavVendorsResponse.Datum> arrayList = new ArrayList<>();
    Controller controller;
    Dialog Dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite_store);
        ButterKnife.bind(this);
        Dialog = Utils.showDialog(this);
        Dialog.show();
        controller = new Controller((Controller.FavStore) this,(Controller.FollowUnfollow)this);

        controller.FavStore("Bearer "+getStringVal(Constants.TOKEN));
        backOnfavStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onSuccessFavStore(Response<FavVendorsResponse> favVendorsResponseResponse) {
            Dialog.dismiss();
            if (favVendorsResponseResponse.body().getStatus()==200)
            {
                for (int i=0;i<favVendorsResponseResponse.body().getData().size();i++)
                {
                    FavVendorsResponse.Datum datum = favVendorsResponseResponse.body().getData().get(i);
                    arrayList.add(datum);
                    setAdapter(arrayList);
                }
            }
    }

    private void setAdapter(ArrayList<FavVendorsResponse.Datum> arrayList) {
        favoriteStoreAdapter = new FavoriteStoreAdapter(this,arrayList);
        recyclerFavoriteStore.setLayoutManager(new LinearLayoutManager(this));
        recyclerFavoriteStore.setAdapter(favoriteStoreAdapter);
        favoriteStoreAdapter.FavoriteStoreAdapter(new GetVendorIDIF() {
            @Override
            public void venDorID(String vendorID) {
                controller.setFollowUnfollow("Bearer " + getStringVal(Constants.TOKEN),vendorID);
            }
        });
    }

    @Override
    public void onSucessFollow(Response<FollowAPIResponse> followAPIResponseResponse) {
        Dialog.dismiss();
        if (followAPIResponseResponse.body().getStatus()==200)
        {
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
            Utils.showToastMessage(this,followAPIResponseResponse.body().getMessage(),getResources().getDrawable(R.drawable.ic_check_black_24dp));
        }
    }

    @Override
    public void onError(String error) {
        Dialog.dismiss();
        Utils.showToastMessage(this,error,getResources().getDrawable(R.drawable.ic_error_black_24dp));

    }
}
