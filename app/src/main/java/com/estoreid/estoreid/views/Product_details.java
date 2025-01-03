package com.estoreid.estoreid.views;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.GetProductColorIF;
import com.estoreid.estoreid.views.adapter.GetProductSizeIF;
import com.estoreid.estoreid.views.adapter.ProductColorAdapter;
import com.estoreid.estoreid.views.adapter.ProductSizeAdapter;
import com.estoreid.estoreid.views.adapter.Product_Detail_images_Adapter;
import com.estoreid.estoreid.views.apiResponseModel.AddToCartResponse;
import com.estoreid.estoreid.views.apiResponseModel.AddToWishlistResponse;
import com.estoreid.estoreid.views.apiResponseModel.ProductDetailResponse;
import com.estoreid.estoreid.views.controller.Controller;
import com.estoreid.estoreid.views.utils.Constants;
import com.estoreid.estoreid.views.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class Product_details extends BaseActivity implements Controller.ProductDetail, Controller.AddToCart, Controller.AddToWishlist {

    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.backontoolbar)
    ImageButton backontoolbar;
    @BindView(R.id.backonproductdetails)
    ImageButton backonproductdetails;
    @BindView(R.id.cart_toolbar)
    ImageButton cartToolbar;
    @BindView(R.id.serach_toolbar)
    ImageButton serachToolbar;
    @BindView(R.id.toolbartitle)
    TextView toolbartitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.product_serach)
    ImageButton productSerach;
    @BindView(R.id.product_cart)
    ImageButton productCart;
    @BindView(R.id.toolbar_layout)
    RelativeLayout toolbarLayout;
    @BindView(R.id.product_detial_viewpager)
    ViewPager productDetialViewpager;
    @BindView(R.id.product_detail_dotlayout)
    LinearLayout productDetailDotlayout;
    @BindView(R.id.producatdetail_viewpager_layout)
    RelativeLayout producatdetailViewpagerLayout;
    @BindView(R.id.product_detial_product_name)
    TextView productDetialProductName;
    @BindView(R.id.producat_detail_price)
    TextView producatDetailPrice;
    @BindView(R.id.product_detail_original_price)
    TextView productOriginalPrice;
    @BindView(R.id.price_layout)
    RelativeLayout priceLayout;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.product_rating)
    Button productRating;
    @BindView(R.id.product_overallreview)
    TextView productOverallreview;
    @BindView(R.id.product_total_reviews)
    TextView productTotalReviews;
    @BindView(R.id.rating_layout)
    RelativeLayout ratingLayout;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.desc_tv)
    TextView descTv;
    @BindView(R.id.decripition_tv)
    ReadMoreTextView decripitionTv;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.select_size)
    TextView selectSize;
    @BindView(R.id.select_colour)
    TextView selectColour;
    @BindView(R.id.size_layout)
    LinearLayout sizeLayout;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.color_recyler)
    RecyclerView colorRecyler;
    @BindView(R.id.size_layout1)
    LinearLayout sizeLayout1;
    @BindView(R.id.colorlayout)
    LinearLayout colorlayout;
    @BindView(R.id.size_recyler)
    RecyclerView sizeRecyler;
    @BindView(R.id.product_add_to_cart)
    Button productAddToCart;
    @BindView(R.id.product_add_to_wishlist)
    Button productAddToWishlist;
    @BindView(R.id.cartcount)
    TextView cartcount;
    private TextView[] dots;
    String type, product_id;
    Intent intent;
    ArrayList<String> images = new ArrayList<>();
    Product_Detail_images_Adapter adapter;
    ProductColorAdapter productColorAdapter;
    ProductSizeAdapter productSizeAdapter;
    int CurrentPage = 0;
    Controller controller;
    String token, productSizeID, productColorID;
    Dialog Dialog;
    ArrayList<ProductDetailResponse.Datum.Color> productImages = new ArrayList<>();
    ArrayList<ProductDetailResponse.Datum.Size> productSize = new ArrayList<>();
    String cart_count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        controller = new Controller((Controller.ProductDetail) this, (Controller.AddToCart) this, (Controller.AddToWishlist) this);
        Dialog = Utils.showDialog(this);
        Dialog.show();
        type = getIntent().getStringExtra("type");
        token = getStringVal(Constants.TOKEN);
        intent = getIntent();
        if (intent != null) {
            product_id = intent.getStringExtra("product_id");
            cart_count = intent.getStringExtra("cartcount");
            cartcount.setText(cart_count);
            if (Utils.isOnline() != false) {
                Dialog.show();
                controller.ProductDetails("Bearer " + token, product_id);
            } else {
                Dialog.dismiss();
                Utils.showToastMessage(Product_details.this, "No Internet Connection", getResources().getDrawable(R.drawable.ic_nointernet));
            }

        }

        listeners();
    }

    private void listeners() {

        selectColour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeLayout1.setVisibility(View.GONE);
                colorlayout.setVisibility(View.VISIBLE);
                selectColour.setTextColor(getResources().getColor(R.color.colorBlack));
                selectSize.setTextColor(getResources().getColor(R.color.colorgrey));
            }
        });

        selectSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorlayout.setVisibility(View.GONE);
                sizeLayout1.setVisibility(View.VISIBLE);
                selectSize.setTextColor(getResources().getColor(R.color.colorBlack));
                selectColour.setTextColor(getResources().getColor(R.color.colorgrey));
            }
        });

        productTotalReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Product_details.this, Product_Reviews.class);
                intent.putExtra("product_id", product_id);
                intent.putExtra("type", type);
                startActivity(intent);
                finish();
            }
        });


        backonproductdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        productAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isOnline() != false) {
                    Dialog.show();
                    controller.Addtocart("Bearer " + getStringVal(Constants.TOKEN), product_id, productColorID, productSizeID);
                } else {
                    Dialog.dismiss();
                    Utils.showToastMessage(Product_details.this, "No Internet Connection", getResources().getDrawable(R.drawable.ic_nointernet));
                }
            }
        });

        productAddToWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isOnline() != false) {
                    Dialog.show();
                    controller.AddToWishlist("Bearer " + getStringVal(Constants.TOKEN), product_id);
                } else {
                    Dialog.dismiss();
                    Utils.showToastMessage(Product_details.this, "No Internet Connection", getResources().getDrawable(R.drawable.ic_nointernet));
                }
            }
        });

    }


    //add dots at bottom
    private void addBottomDots(int currentPage) {

        dots = new TextView[images.size()];
        productDetailDotlayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {

            dots[i] = new TextView(Product_details.this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(20);
            dots[i].setTextColor(getResources().getColor(R.color.colorWhite));
            productDetailDotlayout.addView(dots[i]);

        }

        if (dots.length > 0) {
            dots[currentPage].setTextColor(getResources().getColor(R.color.colorTheme));
        }
    }

    @Override
    public void onSuccessProductDetail(Response<ProductDetailResponse> productDetailResponseResponse) {
        productImages.clear();
        Dialog.dismiss();
        if (productDetailResponseResponse.body().getStatus() == 200) {

            decripitionTv.setText(productDetailResponseResponse.body().getData().get(0).getDescription());
            productDetialProductName.setText(productDetailResponseResponse.body().getData().get(0).getProductName());
            producatDetailPrice.setText("₹"+productDetailResponseResponse.body().getData().get(0).getSalePrice());
            productOriginalPrice.setText("₹"+productDetailResponseResponse.body().getData().get(0).getActualPrice());
            productOriginalPrice.setPaintFlags(productOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            for (int i = 0; i < productDetailResponseResponse.body().getData().get(0).getImages().size(); i++) {
                images.add(productDetailResponseResponse.body().getData().get(0).getImages().get(i).getImage());
            }

            for (int i = 0; i < productDetailResponseResponse.body().getData().get(0).getColor().size(); i++) {
                ProductDetailResponse.Datum.Color color = productDetailResponseResponse.body().getData().get(0).getColor().get(i);
                productImages.add(color);
                setColors(productImages);
            }

            for (int i = 0; i < productDetailResponseResponse.body().getData().get(0).getSize().size(); i++) {
                ProductDetailResponse.Datum.Size size = productDetailResponseResponse.body().getData().get(0).getSize().get(i);
                productSize.add(size);
                setSize(productSize);
            }

            productDetialViewpager.setOffscreenPageLimit(1);
            adapter = new Product_Detail_images_Adapter(Product_details.this, images);
            productDetialViewpager.setAdapter(adapter);
            addBottomDots(0);
            //indicator.setViewPager(viewPager);
            productDetialViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


                @Override
                public void onPageScrolled(int i, float v, int i1) {
                    CurrentPage = i;
                }

                @Override
                public void onPageSelected(int i) {
                    addBottomDots(i);
                }

                @Override
                public void onPageScrollStateChanged(int i) {

                    if (i == ViewPager.SCROLL_STATE_IDLE) {

                        int pagecount = images.size();

                        if (CurrentPage == pagecount) {
                            productDetialViewpager.setCurrentItem(pagecount, true);
                        } else
                            CurrentPage++;
                    }

                }
            });
        } else {
            Dialog.dismiss();
            Utils.showToastMessage(Product_details.this, productDetailResponseResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_black_24dp));
        }

    }

    @SuppressLint("WrongConstant")
    private void setSize(ArrayList<ProductDetailResponse.Datum.Size> productSize) {
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        sizeRecyler.setHasFixedSize(true);
        sizeRecyler.setLayoutManager(linearLayout);
        productSizeAdapter = new ProductSizeAdapter(this, productSize);
        sizeRecyler.setAdapter(productSizeAdapter);
        productSizeAdapter.notifyDataSetChanged();
        productSizeAdapter.ProductSizeAdapter(new GetProductSizeIF() {
            @Override
            public void getID(String id) {
                productSizeID = id;
            }
        });
    }

    @SuppressLint("WrongConstant")
    private void setColors(ArrayList<ProductDetailResponse.Datum.Color> productImages) {

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        colorRecyler.setHasFixedSize(true);
        colorRecyler.setLayoutManager(linearLayout);
        productColorAdapter = new ProductColorAdapter(this, productImages);
        colorRecyler.setAdapter(productColorAdapter);
        productColorAdapter.notifyDataSetChanged();
        productColorAdapter.ProductColorAdapter(new GetProductColorIF() {
            @Override
            public void getColorID(String id) {
                productColorID = id;
            }
        });
    }


    @Override
    public void onSuccessAddToCart(Response<AddToCartResponse> addToCartResponseResponse) {
        Dialog.dismiss();
        if (addToCartResponseResponse.body().getStatus() == 200) {
            Utils.showToastMessage(Product_details.this, addToCartResponseResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_cart_active));
        } else {
            Utils.showToastMessage(Product_details.this, addToCartResponseResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_black_24dp));
        }
    }

    @Override
    public void onSuccessAddToWistlist(Response<AddToWishlistResponse> addToWishlistResponseResponse) {
        Dialog.dismiss();
        if (addToWishlistResponseResponse.body().getStatus() == 200) {
            Utils.showToastMessage(Product_details.this, addToWishlistResponseResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_cart_active));
        } else {
            Utils.showToastMessage(Product_details.this, addToWishlistResponseResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_black_24dp));
        }
    }

    @Override
    public void onError(String error) {
        Dialog.dismiss();
        Utils.showToastMessage(Product_details.this, error, getResources().getDrawable(R.drawable.ic_error_black_24dp));
    }
}
