package com.estoreid.estoreid.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.apiResponseModel.ProductDetailResponse;
import com.estoreid.estoreid.views.utils.Constants;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class ProductColorAdapter extends RecyclerView.Adapter<ProductColorAdapter.ViewHolder> {

    Context context;
    ArrayList<ProductDetailResponse.Datum.Color> colorImages = new ArrayList<>();

    public ProductColorAdapter(Context context, ArrayList<ProductDetailResponse.Datum.Color> colorImages) {
        this.context = context;
        this.colorImages = colorImages;
    }

    @NonNull
    @Override
    public ProductColorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cusstom_color,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductColorAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(Constants.IMAGES+colorImages.get(position).getImage()).into(holder.color_imageview);


    }

    @Override
    public int getItemCount() {
        return colorImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView color_imageview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            color_imageview = itemView.findViewById(R.id.color_imageview);
        }
    }
}
