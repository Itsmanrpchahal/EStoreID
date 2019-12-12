package com.estoreid.estoreid.views.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.MyOrderDetailsActivity;
import com.estoreid.estoreid.views.apiResponseModel.GetOrderListResponse;
import com.estoreid.estoreid.views.utils.Utils;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    Context context;
    ArrayList<GetOrderListResponse.Datum> arrayList = new ArrayList<>();

    public MyOrderAdapter(Context context,ArrayList<GetOrderListResponse.Datum> arrayList1) {
        this.context = context;
        this.arrayList = arrayList1;
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

        holder.tv_OrderName.setText("Order ID:"+arrayList.get(position).getOrderId());
        holder.tv_OrderPrice.setText("$"+arrayList.get(position).getAmount());
        holder.tv_orderTimming.setText(Utils.convertTimeStampDate(Long.parseLong(arrayList.get(position).getOrderDate())));

//        if (position == 0) {
//            holder.tv_orderStatus.setText("On it's way");
//            holder.tv_orderStatus.setTextColor(Color.parseColor("#FDD835"));
//        } else if (position == 2) {
//            holder.tv_orderStatus.setText("Delivered");
//            holder.tv_orderStatus.setTextColor(Color.parseColor("#63BB56"));
//        } else if (position == 4) {
//            holder.tv_orderStatus.setText("Delivered");
//            holder.tv_orderStatus.setTextColor(Color.parseColor("#63BB56"));
//        } else if (position == 6) {
//            holder.tv_orderStatus.setText("Delivered");
//            holder.tv_orderStatus.setTextColor(Color.parseColor("#63BB56"));
//        } else if (position == 7) {
//            holder.tv_orderStatus.setText("Canceled");
//            holder.tv_orderStatus.setTextColor(Color.parseColor("#D81B60"));
//        } else {
//            holder.tv_orderStatus.setText("On it's way");
//            holder.tv_orderStatus.setTextColor(Color.parseColor("#FDD835"));
//        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MyOrderDetailsActivity.class);
                intent.putExtra("text_string","Delivered");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_orderStatus)
        TextView tv_orderStatus;
        @BindView(R.id.tv_OrderName)
        TextView tv_OrderName;
        @BindView(R.id.tv_OrderPrice)
        TextView tv_OrderPrice;
        @BindView(R.id.tv_orderTimming)
        TextView tv_orderTimming;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
