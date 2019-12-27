package com.estoreid.estoreid.views.cart;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.BaseActivity;
import com.estoreid.estoreid.views.adapter.CartAddedItemAdapter;
import com.estoreid.estoreid.views.adapter.ReletedProductAdpater;
import com.estoreid.estoreid.views.apiResponseModel.AddCartQuantityResponse;
import com.estoreid.estoreid.views.apiResponseModel.CartItemsResponse;
import com.estoreid.estoreid.views.apiResponseModel.RemoveCartItemResponse;
import com.estoreid.estoreid.views.cart.paypalintegrate.StartPaymentActivity;
import com.estoreid.estoreid.views.controller.Controller;
import com.estoreid.estoreid.views.utils.Constants;
import com.estoreid.estoreid.views.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class Cart_Activity extends BaseActivity implements Controller.CartItems, Controller.AddCartItemQuantity, Controller.RemoveFromCart {


    ReletedProductAdpater reletedProductAdpater;
    CartAddedItemAdapter adapter;
    @BindView(R.id.product_serach)
    ImageButton productSerach;
    @BindView(R.id.toolbar_layout)
    RelativeLayout toolbarLayout;
    @BindView(R.id.carttext)
    TextView carttext;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.cart_itemscount)
    TextView cartItemscount;
    @BindView(R.id.additem_recyclerviw)
    RecyclerView additemRecyclerviw;
    @BindView(R.id.sub_total_tv)
    TextView subTotalTv;
    @BindView(R.id.sub_total_tv1)
    TextView subTotalTv1;
    @BindView(R.id.tax_tv)
    TextView taxTv;
    @BindView(R.id.tax_tv1)
    TextView taxTv1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.total_tv)
    TextView totalTv;
    @BindView(R.id.total_tv1)
    TextView totalTv1;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.placeorder)
    Button placeorder;
    @BindView(R.id.relatedproduct_tv)
    TextView relatedproductTv;
    @BindView(R.id.related_item_recyclerview)
    RecyclerView relatedItemRecyclerview;
    @BindView(R.id.cardview)
    CardView cardview;
    @BindView(R.id.scrollview)
    ScrollView scrollview;
    @BindView(R.id.safetext)
    TextView safetext;
    ArrayList<CartItemsResponse.Data.Cart> cartitems = new ArrayList<>();
    ArrayList<CartItemsResponse.Data.Related> relatedItems = new ArrayList<>();
    static Integer counter = 0;
    Controller controller;
    Dialog Dialog;
    String size;
    @BindView(R.id.devliverycharge_tv)
    TextView devliverychargeTv;
    @BindView(R.id.devliverycharge_tv1)
    TextView devliverychargeTv1;
    @BindView(R.id.cart_back)
    ImageButton cartBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_);
//        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View contentView = inflater.inflate(R.layout.activity_cart_, null, false);
//        drawer.addView(contentView, 0);
        ButterKnife.bind(this);
        Dialog = Utils.showDialog(this);
        Dialog.show();
        additemRecyclerviw.setFocusable(false);
        controller = new Controller((Controller.CartItems) this, (Controller.AddCartItemQuantity) this, (Controller.RemoveFromCart) this);

        listeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.isOnline() != false) {
            Dialog.show();
            cartitems.clear();
            controller.CartItems("Bearer " + getStringVal(Constants.TOKEN));
        } else {
            Dialog.dismiss();
            Utils.showToastMessage(Cart_Activity.this, "No Internet Connection", getResources().getDrawable(R.drawable.ic_nointernet));
        }
    }

    private void listeners() {
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!size.equals("0")) {
                    Intent intent = new Intent(Cart_Activity.this, StartPaymentActivity.class);
                    startActivity(intent);

                } else {
                    Utils.showToastMessage(Cart_Activity.this, "Add Items in cart to continue ", getResources().getDrawable(R.drawable.ic_error_black_24dp));
                }

            }
        });

        cartBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @SuppressLint("WrongConstant")
    private void setAdapter(ArrayList<CartItemsResponse.Data.Cart> cartitems) {

        //Cart Addet items
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        additemRecyclerviw.setHasFixedSize(true);
        additemRecyclerviw.setLayoutManager(linearLayout);
        adapter = new CartAddedItemAdapter(this, cartitems);
        additemRecyclerviw.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.CartAddedItemAdapter(new AddCartQuantity() {
            @Override
            public void onSuccess(String cart_id, String quantity) {
                if (Utils.isOnline() != false) {
                    Dialog.show();
                    controller.AddCartItemQuantity("Bearer " + getStringVal(Constants.TOKEN), cart_id, quantity);
                } else {
                    Dialog.dismiss();
                    Utils.showToastMessage(Cart_Activity.this, "No Internet Connection", getResources().getDrawable(R.drawable.ic_nointernet));
                }

            }
        });

        adapter.CartAddedItemAdapter(new RemoveCartItem() {
            @Override
            public void onSuccess(String cart_id) {
                if (Utils.isOnline() != false) {
                    Dialog.show();
                    controller.RemoveFromCart("Bearer " + getStringVal(Constants.TOKEN), cart_id);
                } else {
                    Dialog.dismiss();
                    Utils.showToastMessage(Cart_Activity.this, "No Internet Connection", getResources().getDrawable(R.drawable.ic_nointernet));
                }
            }
        });

        //Releted Items
        LinearLayoutManager linearLayout1 = new LinearLayoutManager(this);
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        relatedItemRecyclerview.setHasFixedSize(true);
        relatedItemRecyclerview.setLayoutManager(linearLayout1);
        reletedProductAdpater = new ReletedProductAdpater(this, relatedItems,size);
        relatedItemRecyclerview.setAdapter(reletedProductAdpater);
        reletedProductAdpater.notifyDataSetChanged();
    }

    @Override
    public void onSuccessCartItems(Response<CartItemsResponse> cartItemsResponseResponse) {
        Dialog.dismiss();
        cartitems.clear();
        relatedItems.clear();
        if (cartItemsResponseResponse.body().getStatus() == 200) {
            for (int i = 0; i < cartItemsResponseResponse.body().getData().getCart().size(); i++) {
                CartItemsResponse.Data.Cart datum = cartItemsResponseResponse.body().getData().getCart().get(i);
                subTotalTv1.setText("₹" + cartItemsResponseResponse.body().getData().getPrice().getSubTotal().toString());
                totalTv1.setText("₹" + cartItemsResponseResponse.body().getData().getPrice().getTotal().toString());
                taxTv1.setText(cartItemsResponseResponse.body().getData().getPrice().getTaxAmount().toString());
                devliverychargeTv1.setText("₹" + cartItemsResponseResponse.body().getData().getPrice().getDelivery());
                setStringVal(Constants.TOTALAMOUNT, cartItemsResponseResponse.body().getData().getPrice().getTotal().toString());
                setStringVal(Constants.SUBTOTAL, cartItemsResponseResponse.body().getData().getPrice().getSubTotal().toString());
                size = String.valueOf(cartItemsResponseResponse.body().getData().getCart().size());

                cartitems.add(datum);
                setAdapter(cartitems);
                if (cartItemsResponseResponse.body().getData().getRelated().size() == 0) {
                    cardview.setVisibility(View.GONE);
                }

                //Get related Items
                CartItemsResponse.Data.Related related = cartItemsResponseResponse.body().getData().getRelated().get(i);
                relatedItems.add(related);
            }
        } else {
            size = "0";
            additemRecyclerviw.setVisibility(View.GONE);
            subTotalTv1.setText("0");
            totalTv1.setText("0");
            taxTv1.setText("0");
            cartItemscount.setText("0 items available");
            Utils.showToastMessage(this, cartItemsResponseResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_black_24dp));
        }
    }

    @Override
    public void onSucessAddCartQuantity(Response<AddCartQuantityResponse> addCartItemQuantityResponse) {
        Dialog.dismiss();
        if (addCartItemQuantityResponse.body().getStatus() == 200) {
            if (Utils.isOnline() != false) {
                Dialog.show();
                controller.CartItems("Bearer " + getStringVal(Constants.TOKEN));
            } else {
                Dialog.dismiss();
                Utils.showToastMessage(Cart_Activity.this, "No Internet Connection", getResources().getDrawable(R.drawable.ic_nointernet));
            }
        }
    }

    @Override
    public void onSuccessRemoveCart(Response<RemoveCartItemResponse> removeFromCartResponse) {
        Dialog.dismiss();
        if (removeFromCartResponse.body().getStatus() == 200) {
            controller.CartItems("Bearer " + getStringVal(Constants.TOKEN));
            Utils.showToastMessage(Cart_Activity.this, removeFromCartResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_check_circle));
        }
    }

    @Override
    public void onError(String error) {
        Dialog.dismiss();
        Utils.showToastMessage(this, error, getResources().getDrawable(R.drawable.ic_error_black_24dp));
    }
}
