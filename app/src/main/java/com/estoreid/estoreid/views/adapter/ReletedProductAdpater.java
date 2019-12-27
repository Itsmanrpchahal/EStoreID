package com.estoreid.estoreid.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.Product_details;
import com.estoreid.estoreid.views.apiResponseModel.CartItemsResponse;
import com.estoreid.estoreid.views.utils.Constants;

import java.util.ArrayList;

public class ReletedProductAdpater extends RecyclerView.Adapter<ReletedProductAdpater.ViewHolder> {

    Context context;
    ArrayList<CartItemsResponse.Data.Related> relatedItems = new ArrayList<>();
   String size;

    public ReletedProductAdpater(Context context, ArrayList<CartItemsResponse.Data.Related> relatedItems, String s) {
        this.context = context;
        this.relatedItems = relatedItems;
        this.size = s;
    }

    @NonNull
    @Override
    public ReletedProductAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.related_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReletedProductAdpater.ViewHolder holder, int position) {
        CartItemsResponse.Data.Related related = relatedItems.get(position);
        Glide.with(context).load(Constants.IMAGES+related.getImage().toString()).into(holder.releted_image);
        holder.releted_product_name.setText(related.getBrandName());
        holder.related_product_price.setText("₹"+related.getSalePrice().toString());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Product_details.class);
                intent.putExtra("product_id",related.getId().toString());
                intent.putExtra("cartcount",size);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return relatedItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView releted_image;
        TextView releted_product_name,related_product_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            releted_image = itemView.findViewById(R.id.releted_image);
            releted_product_name = itemView.findViewById(R.id.releted_product_name);
            related_product_price = itemView.findViewById(R.id.related_product_price);
        }
    }
}
