package com.estoreid.estoreid.views.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.apiResponseModel.CartItemsResponse;
import com.estoreid.estoreid.views.apiResponseModel.count;
import com.estoreid.estoreid.views.utils.Constants;
import com.estoreid.estoreid.views.utils.Utils;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class CartAddedItemAdapter extends RecyclerView.Adapter<CartAddedItemAdapter.ViewHolder> {

    Context context;
    int count1 ;
    AsyncTask<Integer> asyncTask;
    ArrayList<CartItemsResponse.Datum> cartsitems = new ArrayList<>();
    AddCartQuantity addCartQuantity;

    public void CartAddedItemAdapter(AddCartQuantity addCartQuantity) {
        this.addCartQuantity = addCartQuantity;
    }

    public CartAddedItemAdapter(Context context, ArrayList<CartItemsResponse.Datum> cartsitems) {
        this.context = context;
        this.cartsitems = cartsitems;
    }

    @NonNull
    @Override
    public CartAddedItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cart_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAddedItemAdapter.ViewHolder holder, int position) {

        CartItemsResponse.Datum datum = cartsitems.get(position);
        Glide.with(context).load(Constants.IMAGES+datum.getImage()).into(holder.cart_product_image);
        holder.product_name.setText(datum.getProductName());
//        holder.product_category.setText(datum.);
        holder.product_price.setText(datum.getSalePrice().toString());
        holder.product_discount.setText(datum.getActualPrice().toString());
        holder.product_discount.setPaintFlags(holder.product_discount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.count_tv.setText(datum.getQuantity().toString());
        holder.add_item_from_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartsitems.get(position).getQuantity()<=15)
                {
                    count1 =0;
                    count1 = 1+cartsitems.get(position).getQuantity();
                    cartsitems.get(position).setQuantity(count1);
                    addCartQuantity.onSuccess(datum.getCartId().toString(),String.valueOf(count1));
                    holder.count_tv.setText(String.valueOf(count1));
                }else {
                    Utils.showToastMessage(context,"15 is maximum",context.getResources().getDrawable(R.drawable.ic_error_black_24dp));
                }
            }
        });


        holder.remove_item_from_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartsitems.get(position).getQuantity() >= 1) {
                    count1 = 0;
                    count1 = cartsitems.get(position).getQuantity() - 1;
                    cartsitems.get(position).setQuantity(count1);
                    addCartQuantity.onSuccess(datum.getCartId().toString(),String.valueOf(count1));
                    holder.count_tv.setText(String.valueOf(count1));

                } else {
                    Utils.showToastMessage(context,"1 is minimum",context.getResources().getDrawable(R.drawable.ic_error_black_24dp));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartsitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton remove_item_from_cart,add_item_from_cart;
        TextView count_tv,product_name,product_category,product_price,product_discount;
        RoundedImageView cart_product_image;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            remove_item_from_cart = itemView.findViewById(R.id.remove_item_from_cart);
            count_tv = itemView.findViewById(R.id.count_tv);
            add_item_from_cart = itemView.findViewById(R.id.add_item_from_cart);
            cart_product_image = itemView.findViewById(R.id.cart_product_image);
            product_name = itemView.findViewById(R.id.product_name);
            product_category = itemView.findViewById(R.id.product_category);
            product_price = itemView.findViewById(R.id.product_price);
            product_discount = itemView.findViewById(R.id.product_discount);
        }
    }
}
