package com.estoreid.estoreid.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.Product_details;
import com.estoreid.estoreid.views.apiResponseModel.GetWishlistProducts;
import com.estoreid.estoreid.views.utils.Constants;

import java.util.ArrayList;

public class WishlistProducts extends RecyclerView.Adapter<WishlistProducts.ViewHolder> {

    Context context;
    ArrayList<GetWishlistProducts.Datum> wishlist = new ArrayList<>();

    public WishlistProducts(Context context, ArrayList<GetWishlistProducts.Datum> wishlist) {
        this.context = context;
        this.wishlist = wishlist;
    }

    @NonNull
    @Override
    public WishlistProducts.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_products_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistProducts.ViewHolder holder, int position) {

        GetWishlistProducts.Datum datum = wishlist.get(position);

        holder.product_add_to_fav.setVisibility(View.GONE);
        holder.product_add_to_cart.setVisibility(View.GONE);
        Glide.with(context).load(Constants.IMAGES +wishlist.get(position).getImage().toString()).into(holder.product_image);
        holder.product_name.setText(wishlist.get(position).getProductName());
        holder.product_price.setText("₹"+wishlist.get(position).getSalePrice());
        holder.product_original_price.setText("₹"+wishlist.get(position).getActualPrice());
        holder.product_original_price.setPaintFlags(holder.product_original_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
          holder.product_discountper.setText(wishlist.get(position).getDiscount()+"% off");
        holder.product_rating.setRating(Float.parseFloat(String.valueOf(wishlist.get(position).getRating())));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Product_details.class);
                intent.putExtra("product_id",wishlist.get(position).getId().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wishlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView product_image;
        TextView product_name,product_price,product_original_price,product_discountper,products_review,product_brand;
        AppCompatRatingBar product_rating;
        ImageButton product_add_to_cart,product_add_to_fav;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product_image = itemView.findViewById(R.id.product_image);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);
            product_original_price = itemView.findViewById(R.id.product_original_price);
            product_discountper = itemView.findViewById(R.id.product_discountper);
            product_rating = itemView.findViewById(R.id.product_rating);
            products_review = itemView.findViewById(R.id.products_review);
            product_brand = itemView.findViewById(R.id.product_brand);
            product_add_to_cart = itemView.findViewById(R.id.product_add_to_cart);
            product_add_to_fav = itemView.findViewById(R.id.product_add_to_fav);
        }
    }
}
