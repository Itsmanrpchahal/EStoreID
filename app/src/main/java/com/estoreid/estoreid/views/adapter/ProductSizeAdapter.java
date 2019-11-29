package com.estoreid.estoreid.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.Product_details;
import com.estoreid.estoreid.views.apiResponseModel.ProductDetailResponse;

import java.util.ArrayList;

public class ProductSizeAdapter extends RecyclerView.Adapter<ProductSizeAdapter.ViewHolder> {

    Context context;
    private int selectedPosition = -1;
    ArrayList<ProductDetailResponse.Datum.Size> size = new ArrayList<>();
    GetProductSizeIF getProductSizeIF;

    public void ProductSizeAdapter(GetProductSizeIF getProductSizeIF) {
        this.getProductSizeIF = getProductSizeIF;
    }

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
        if (selectedPosition == position) {
            holder.itemView.setSelected(true); //using selector drawable
            holder.layout_methods.setBackgroundResource(R.drawable.custome_theme_selected_bg);
        } else {
            holder.itemView.setSelected(false);
            holder.layout_methods.setBackgroundResource(R.drawable.grey_border);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedPosition >= 0)
                    notifyItemChanged(selectedPosition);
                selectedPosition = holder.getAdapterPosition();
                getProductSizeIF.getID(String.valueOf(size.get(position).getId()));
                notifyItemChanged(selectedPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return size.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productsize;
        RelativeLayout layout_methods;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productsize = itemView.findViewById(R.id.productsize);
            layout_methods = itemView.findViewById(R.id.layout_methods);
        }
    }
}
