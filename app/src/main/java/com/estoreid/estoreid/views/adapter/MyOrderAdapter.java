package com.estoreid.estoreid.views.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.MyOrderDetailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    Context context;

    public MyOrderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_myorder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (position == 0) {
            holder.tv_orderStatus.setText("On it's way");
            holder.tv_orderStatus.setTextColor(Color.parseColor("#FDD835"));
        } else if (position == 2) {
            holder.tv_orderStatus.setText("Delivered");
            holder.tv_orderStatus.setTextColor(Color.parseColor("#63BB56"));
        } else if (position == 4) {
            holder.tv_orderStatus.setText("Delivered");
            holder.tv_orderStatus.setTextColor(Color.parseColor("#63BB56"));
        } else if (position == 6) {
            holder.tv_orderStatus.setText("Delivered");
            holder.tv_orderStatus.setTextColor(Color.parseColor("#63BB56"));
        } else if (position == 7) {
            holder.tv_orderStatus.setText("Canceled");
            holder.tv_orderStatus.setTextColor(Color.parseColor("#D81B60"));
        } else {
            holder.tv_orderStatus.setText("On it's way");
            holder.tv_orderStatus.setTextColor(Color.parseColor("#FDD835"));
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MyOrderDetailsActivity.class);
                intent.putExtra("text_string",holder.tv_orderStatus.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_orderStatus)
        TextView tv_orderStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
