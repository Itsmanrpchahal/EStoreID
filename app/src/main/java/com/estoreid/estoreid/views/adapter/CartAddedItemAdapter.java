package com.estoreid.estoreid.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;

public class CartAddedItemAdapter extends RecyclerView.Adapter<CartAddedItemAdapter.ViewHolder> {

    Context context;

    public CartAddedItemAdapter(Context context) {
        this.context = context;
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

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
