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
import com.estoreid.estoreid.views.Products_Screen;
import com.estoreid.estoreid.views.apiResponseModel.ProductsAPI;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    Context context;
    String type;
    ArrayList<ProductsAPI.Datum> products = new ArrayList<>();
    View.OnClickListener onClickListener;

    public ProductAdapter(Context context, String type,ArrayList<ProductsAPI.Datum> products) {
        this.context = context;
        this.type = type;
        this.products = products;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = null;
        if (type.equals("list"))
        {   LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
             view = layoutInflater.inflate(R.layout.custom_products_list,parent,false);
        }else if (type.equals("grid"))
        {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
             view = layoutInflater.inflate(R.layout.custom_product_grid,parent,false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        Glide.with(context).load("http://estore.amrdev.site/public/images/"+products.get(position).getImage()).into(holder.product_image);
        holder.product_name.setText(products.get(position).getProductName());
        holder.product_price.setText("$"+products.get(position).getSalePrice());
        holder.product_original_price.setText("$"+products.get(position).getActualPrice());
        holder.product_original_price.setPaintFlags(holder.product_original_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.product_brand.setText(products.get(position).getBrandName());
        holder.product_discountper.setText(products.get(position).getDiscount()+"% off");
        holder.product_rating.setRating(Float.parseFloat(products.get(position).getRating()));

        if (products.get(position).getCart_status().equals("1"))
        {
            Glide.with(context).load(R.drawable.ic_addproductactive).into(holder.product_add_to_cart);
        }

        String productid = products.get(position).getId().toString();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Product_details.class);
                intent.putExtra("type",type);
                intent.putExtra("product_id",productid);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView product_image;
        TextView product_name,product_price,product_original_price,product_discountper,products_review,product_brand;
        AppCompatRatingBar product_rating;
        ImageButton product_add_to_cart;
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
        }
    }
}
