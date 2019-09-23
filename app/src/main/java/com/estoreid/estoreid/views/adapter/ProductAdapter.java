package com.estoreid.estoreid.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.Product_details;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    Context context;
    String type;
    View.OnClickListener onClickListener;

    public ProductAdapter(Context context, String type) {
        this.context = context;
        this.type = type;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Product_details.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
