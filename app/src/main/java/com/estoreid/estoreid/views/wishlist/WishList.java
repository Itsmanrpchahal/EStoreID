package com.estoreid.estoreid.views.wishlist;

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
import com.estoreid.estoreid.views.BaseActivity;
import com.estoreid.estoreid.views.MyFavoriteStoreActivity;
import com.estoreid.estoreid.views.adapter.WishlistProducts;
import com.estoreid.estoreid.views.apiResponseModel.GetWishlistProducts;
import com.estoreid.estoreid.views.controller.Controller;
import com.estoreid.estoreid.views.utils.Constants;
import com.estoreid.estoreid.views.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class WishList extends BaseActivity implements Controller.GetWishlistProducts{

    @BindView(R.id.recycler_wishlistproducts)
    RecyclerView recyclerWishlistproducts;
    @BindView(R.id.back_wishlist)
    ImageButton back_wishlist;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    Controller controller;
    android.app.Dialog Dialog;
    ArrayList<GetWishlistProducts.Datum> wishlist = new ArrayList<>();
    WishlistProducts wishlistProducts ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        ButterKnife.bind(this);
        controller = new Controller(this);
        Dialog = Utils.showDialog(this);
        Dialog.show();

        if (Utils.isOnline()!=false)
        {
            Dialog.show();
            controller.GetWishlist("Bearer "+getStringVal(Constants.TOKEN));
        }else {
            Dialog.dismiss();
            Utils.showToastMessage(WishList.this,"No Internet Connection",getResources().getDrawable(R.drawable.ic_nointernet));
        }

        listeners();
    }

    private void listeners() {

        back_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onSuccessGetWishlist(Response<GetWishlistProducts> getWishlistProductsResponse) {
        Dialog.dismiss();
        if (getWishlistProductsResponse.body().getStatus()==200)
        {
            for (int i=0;i<getWishlistProductsResponse.body().getData().size();i++)
            {
                GetWishlistProducts.Datum datum = getWishlistProductsResponse.body().getData().get(i);
                wishlist.add(datum);
                setAdapter(wishlist);
            }
        }else {
            Utils.showToastMessage(this,getWishlistProductsResponse.body().getMessage(),getResources().getDrawable(R.drawable.ic_error_black_24dp));
        }
    }

    private void setAdapter(ArrayList<GetWishlistProducts.Datum> wishlist) {
            wishlistProducts = new WishlistProducts(this,wishlist);
            recyclerWishlistproducts.setLayoutManager(new LinearLayoutManager(this));
            recyclerWishlistproducts.setAdapter(wishlistProducts);
    }

    @Override
    public void onError(String error) {
        Dialog.dismiss();
        Utils.showToastMessage(this,error,getResources().getDrawable(R.drawable.ic_error_black_24dp));
    }
}
