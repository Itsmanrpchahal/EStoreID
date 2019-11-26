package com.estoreid.estoreid.views.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.estoreid.estoreid.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class Product_Detail_images_Adapter extends PagerAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<String> images = new ArrayList<>();

    public Product_Detail_images_Adapter(Context context, ArrayList<String> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final RoundedImageView trailimg;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemview = inflater.inflate(R.layout.productimages, container, false);
        trailimg = itemview.findViewById(R.id.trailImage);

        Log.d("images",""+images.toString());


        Glide.with(context).load(R.drawable.shape).into(trailimg);

        ((ViewPager) container).addView(itemview);

        return itemview;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
