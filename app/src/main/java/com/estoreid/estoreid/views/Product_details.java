package com.estoreid.estoreid.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.estoreid.estoreid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Product_details extends AppCompatActivity {

    @BindView(R.id.cart_toolbar_navigate)
    ImageButton cartToolbarNavigate;
    @BindView(R.id.cart_toolbar)
    ImageButton cartToolbar;
    @BindView(R.id.serach_toolbar)
    ImageButton serachToolbar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);

        listeners();
    }

    private void listeners() {
        productTotalReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Product_details.this,Product_Reviews.class);
                startActivity(intent);
                finish();
//                overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);
            }
        });
    }
}
