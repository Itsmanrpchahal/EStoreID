package com.estoreid.estoreid.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;

public class ReletedProductAdpater extends RecyclerView.Adapter<ReletedProductAdpater.ViewHolder> {

    Context context;

    public ReletedProductAdpater(Context context) {
        this.context = context;
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

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
