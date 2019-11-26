package com.estoreid.estoreid.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.apiResponseModel.FilterDataResponse;
import com.estoreid.estoreid.views.filter.FilterScreen;
import com.estoreid.estoreid.views.utils.Constants;

import java.util.ArrayList;

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ViewHolder> {

    Context context;
    ArrayList<FilterDataResponse.Datum.Color> colors;
    private int selectedPosition = -1;

    public ColorsAdapter(Context context, ArrayList<FilterDataResponse.Datum.Color> colorArrayList) {
        this.context = context;
        this.colors = colorArrayList;
    }

    @NonNull
    @Override
    public ColorsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cusstom_color,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorsAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(Constants.IMAGES+colors.get(position).getImage()).into(holder.color_imageview);
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
        return colors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView color_imageview;
        RelativeLayout layoutMethods;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            color_imageview = itemView.findViewById(R.id.color_imageview);
            layoutMethods = itemView.findViewById(R.id.layout_methods);
        }
    }
}
