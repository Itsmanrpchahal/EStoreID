package com.estoreid.estoreid.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {

    Context context;

    public BrandAdapter(Context context) {
        this.context = context;
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
        if (position == 0) {
            holder.layoutMethods.setBackgroundResource(R.drawable.custome_theme_selected_bg);
        }

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.layoutMethods.setBackgroundResource(R.drawable.custome_theme_selected_bg);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_card)
        ImageView imgCard;
        @BindView(R.id.layout_methods)
        RelativeLayout layoutMethods;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
