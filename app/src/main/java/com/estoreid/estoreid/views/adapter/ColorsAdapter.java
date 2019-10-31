package com.estoreid.estoreid.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.filter.FilterScreen;

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ViewHolder> {

    Context context;

    public ColorsAdapter(Context context) {
        this.context = context;
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
