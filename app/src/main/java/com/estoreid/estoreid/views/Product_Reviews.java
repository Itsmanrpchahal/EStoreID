package com.estoreid.estoreid.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.ProductAdapter;
import com.estoreid.estoreid.views.adapter.Product_ReviewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Product_Reviews extends AppCompatActivity {

    @BindView(R.id.reviews_back)
    ImageButton reviewsBack;
    @BindView(R.id.reviews_toolbar)
    Toolbar reviewsToolbar;
    @BindView(R.id.overallrating_text)
    TextView overallratingText;
    @BindView(R.id.product_rating)
    RatingBar productRating;
    @BindView(R.id.total_reviews)
    TextView totalReviews;
    @BindView(R.id.rating_layout)
    RelativeLayout ratingLayout;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.five_starrating)
    RatingBar fiveStarrating;
    @BindView(R.id.five_progress)
    ProgressBar fiveProgress;
    @BindView(R.id.five_reviews)
    TextView fiveReviews;
    @BindView(R.id.rating_layout_one)
    LinearLayout ratingLayoutOne;
    @BindView(R.id.four_starrating)
    RatingBar fourStarrating;
    @BindView(R.id.four_progress)
    ProgressBar fourProgress;
    @BindView(R.id.four_reviews)
    TextView fourReviews;
    @BindView(R.id.rating_layout_two)
    LinearLayout ratingLayoutTwo;
    @BindView(R.id.three_starrating)
    RatingBar threeStarrating;
    @BindView(R.id.three_progress)
    ProgressBar threeProgress;
    @BindView(R.id.three_reviews)
    TextView threeReviews;
    @BindView(R.id.rating_layout_three)
    LinearLayout ratingLayoutThree;
    @BindView(R.id.two_starrating)
    RatingBar twoStarrating;
    @BindView(R.id.two_progress)
    ProgressBar twoProgress;
    @BindView(R.id.two_reviews)
    TextView twoReviews;
    @BindView(R.id.rating_layout_four)
    LinearLayout ratingLayoutFour;
    @BindView(R.id.one_starrating)
    RatingBar oneStarrating;
    @BindView(R.id.one_progress)
    ProgressBar oneProgress;
    @BindView(R.id.one_reviews)
    TextView oneReviews;
    @BindView(R.id.rating_layout_five)
    LinearLayout ratingLayoutFive;
    @BindView(R.id.reviews_recyler)
    RecyclerView reviewsRecyler;

    Product_ReviewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__reviews);

        ButterKnife.bind(this);

        listeners();
        setAdapter();
    }

    private void setAdapter() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        reviewsRecyler.setHasFixedSize(true);
        reviewsRecyler.setLayoutManager(linearLayout);
        adapter = new Product_ReviewAdapter(this);
        reviewsRecyler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void listeners() {

        reviewsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Product_Reviews.this, Product_details.class);
                startActivity(intent);
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
            }
        });
    }
}
