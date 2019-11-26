package com.estoreid.estoreid.views.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.apiResponseModel.FilterDataResponse;
import com.estoreid.estoreid.views.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {

    Context context;
    private int selectedPosition = -1;
    ArrayList<FilterDataResponse.Datum> products = new ArrayList<>();
    ArrayList<FilterDataResponse.Datum.Brand> brands = new ArrayList<FilterDataResponse.Datum.Brand>();


    public BrandAdapter(Context context,ArrayList<FilterDataResponse.Datum.Brand> brands) {
        this.context = context;
        this.brands = brands;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_brand, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(Constants.IMAGES+brands.get(position).getImage()).into(holder.imgbrand);



        if (selectedPosition == position) {
            holder.itemView.setSelected(true); //using selector drawable
            holder.layoutMethods.setBackgroundResource(R.drawable.custome_theme_selected_bg);
        } else {
            holder.itemView.setSelected(false);
            holder.layoutMethods.setBackgroundResource(R.drawable.grey_border);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedPosition >= 0)
                    notifyItemChanged(selectedPosition);
                selectedPosition = holder.getAdapterPosition();
                notifyItemChanged(selectedPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return brands.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_brand)
        ImageView imgbrand;
        @BindView(R.id.layout_methods)
        RelativeLayout layoutMethods;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
