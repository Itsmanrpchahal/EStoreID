package com.estoreid.estoreid.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.apiResponseModel.GetBookOrderDetail;

import java.util.ArrayList;

public class OrderDiscountAdapter extends RecyclerView.Adapter<OrderDiscountAdapter.ViewHolder> {

    Context context;
    ArrayList<GetBookOrderDetail.Datum> data = new ArrayList<>();


    public OrderDiscountAdapter(Context context, ArrayList<GetBookOrderDetail.Datum> datum) {
        this.context = context;
        this.data = datum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_order_discount, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        GetBookOrderDetail.Datum  datum = data.get(position);
        holder.tv_OrderName.setText(datum.getProductName());
        holder.tv_orderStatus.setText(datum.getOrderQuantity()+" item * ₹"+datum.getProductPrice());
        holder.tv_OrderPrice.setText("₹ "+ Integer.valueOf(datum.getOrderQuantity())*  Integer.valueOf(datum.getProductPrice()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_OrderName,tv_orderStatus,tv_OrderPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_OrderName = itemView.findViewById(R.id.tv_OrderName);
            tv_orderStatus = itemView.findViewById(R.id.tv_orderStatus);
            tv_OrderPrice = itemView.findViewById(R.id.tv_OrderPrice);
        }
    }
}
