package com.estoreid.estoreid.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.Product_details;
import com.estoreid.estoreid.views.apiResponseModel.ProductDetailResponse;

import java.util.ArrayList;

public class ProductSizeAdapter extends RecyclerView.Adapter<ProductSizeAdapter.ViewHolder> {

    Context context;
    ArrayList<ProductDetailResponse.Datum.Size> size = new ArrayList<>();

    public ProductSizeAdapter(Context context, ArrayList<ProductDetailResponse.Datum.Size> size) {
        this.context = context;
        this.size = size;
    }

    @NonNull
    @Override
    public ProductSizeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_productsize, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSizeAdapter.ViewHolder holder, int position) {

        holder.productsize.setText(size.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return size.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productsize;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productsize = itemView.findViewById(R.id.productsize);
        }
    }
}
