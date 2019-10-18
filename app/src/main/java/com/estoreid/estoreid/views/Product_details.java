package com.estoreid.estoreid.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.Product_Detail_images_Adapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Product_details extends BaseActivity {

    private TextView[] dots;
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
    @BindView(R.id.product_original_price)
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
    @BindView(R.id.size_s)
    Button sizeS;
    @BindView(R.id.size_M)
    Button sizeM;
    @BindView(R.id.size_L)
    Button sizeL;
    @BindView(R.id.size_XXL)
    Button sizeXXL;
    @BindView(R.id.product_detial_back)
    ImageButton productDetialBack;
    String type;
    Intent intent;
    ArrayList<String> images = new ArrayList<>();
    Product_Detail_images_Adapter adapter;
    int CurrentPage =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        setContentView(R.layout.activity_product_details);
        type=getIntent().getStringExtra("type");
        ButterKnife.bind(this);
        listeners();

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

                    int pagecount = 5;

                    if (CurrentPage == pagecount) {
                        productDetialViewpager.setCurrentItem(pagecount, true);
                    } else
                        CurrentPage++;
                }

            }
        });
    }

    private void listeners() {

        productTotalReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Product_details.this, Product_Reviews.class);
                intent.putExtra("type",type);
                startActivity(intent);
                finish();
            }
        });

        productDetialBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Product_details.this,Products_Screen.class);
                intent.putExtra("type",type);
                startActivity(intent);
                finish();
            }
        });
    }


    //add dots at bottom
    private void addBottomDots(int currentPage) {

        dots = new TextView[5];
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
}
