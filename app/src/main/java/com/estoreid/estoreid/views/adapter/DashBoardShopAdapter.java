package com.estoreid.estoreid.views.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.Products_Screen;
import com.estoreid.estoreid.views.apiResponseModel.VendorAPIResponse;
import com.estoreid.estoreid.views.controller.Controller;
import com.estoreid.estoreid.views.utils.Utils;

import java.util.ArrayList;

public class DashBoardShopAdapter extends RecyclerView.Adapter<DashBoardShopAdapter.ViewHolder> {

    Context context;
    ArrayList<VendorAPIResponse.Datum> vendorlist;
    android.app.Dialog Dialog;
    FollowIF followIF;

    public void SetOnFollow(FollowIF followIF1) {
        followIF = followIF1;
    }

    public DashBoardShopAdapter(Context context, ArrayList<VendorAPIResponse.Datum> vendorlist) {
        this.context = context;
        this.vendorlist = vendorlist;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Dialog = Utils.showDialog(this.context);
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_shop_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashBoardShopAdapter.ViewHolder holder, int position) {

        VendorAPIResponse.Datum datum = vendorlist.get(position);
        holder.vendor_name.setText(datum.getBusinessName());
        holder.vendor_loc.setText(datum.getAddress());
        holder.vendor_time.setText("Timing: " + datum.getTimeFrom() + " - " + datum.getTimeTo());
        String vendor_id = datum.getVendor_id();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Products_Screen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("vendor_id", vendor_id);
                intent.putExtra("type","list");
                context.startActivity(intent);
            }
        });

        if (datum.getFollow_status().equals("1")) {
            holder.followbt.setText("Unfollow");
        } else {
            holder.followbt.setText("Follow");
        }

        holder.followbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isOnline() != false) {
                    followIF.onSuccess(datum.getVendor_id());
                    if (holder.followbt.getText().equals("Follow")) {
                        holder.followbt.setText("Unfollow");
                    } else if (holder.followbt.getText().equals("Unfollow")) {
                        holder.followbt.setText("Follow");
                    }
                } else {
                    Utils.showToastMessage(context, "No Internet", context.getResources().getDrawable(R.drawable.ic_nointernet));
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return vendorlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView vendor_name, vendor_loc, vendor_time;
        Button followbt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vendor_name = itemView.findViewById(R.id.vendor_name);
            vendor_loc = itemView.findViewById(R.id.vendor_loc);
            vendor_time = itemView.findViewById(R.id.vendor_time);
            followbt = itemView.findViewById(R.id.followbt);
        }


    }
}
