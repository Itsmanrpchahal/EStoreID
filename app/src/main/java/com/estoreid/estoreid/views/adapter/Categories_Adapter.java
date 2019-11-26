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
import com.estoreid.estoreid.views.apiResponseModel.FilterDataResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Categories_Adapter extends RecyclerView.Adapter<Categories_Adapter.ViewHolder> {
    Context context;
    private int selectedPosition = -1;
    ArrayList<FilterDataResponse.Datum.Category> categories = new ArrayList<>();


    public Categories_Adapter(Context context, ArrayList<FilterDataResponse.Datum.Category> categories) {
        this.context = context;
        this.categories = categories;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_categories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imgCard.setText(categories.get(position).getName());
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
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_card)
        TextView imgCard;
        @BindView(R.id.layout_methods)
        RelativeLayout layoutMethods;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
