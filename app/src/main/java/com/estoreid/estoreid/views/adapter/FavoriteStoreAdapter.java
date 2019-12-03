package com.estoreid.estoreid.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.Products_Screen;
import com.estoreid.estoreid.views.apiResponseModel.FavVendorsResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteStoreAdapter extends RecyclerView.Adapter<FavoriteStoreAdapter.ViewHolder> {

    Context context;
    ArrayList<FavVendorsResponse.Datum> datumArrayList = new ArrayList<>();
    GetVendorIDIF getVendorIDIF;

    public void FavoriteStoreAdapter(GetVendorIDIF getVendorIDIF) {
        this.getVendorIDIF = getVendorIDIF;
    }

    public FavoriteStoreAdapter(Context context, ArrayList<FavVendorsResponse.Datum> datumArrayList1) {
        this.context = context;
        this.datumArrayList = datumArrayList1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_favroite_store, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_rating.setText(datumArrayList.get(position).getRating().toString());
        Glide.with(context).load("http://estore.amrdev.site/public/images/"+datumArrayList.get(position).getImage().toString()).into(holder.img_store);
        holder.tv_venderName.setText(datumArrayList.get(position).getBusinessName());
        holder.tv_venderAddress.setText(datumArrayList.get(position).getAddress());
        holder.tv_venderTimming.setText("Timing: "+datumArrayList.get(position).getTimeFrom().toString()+" - "+datumArrayList.get(position).getTimeTo().toString());

        holder.btn_unfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVendorIDIF.venDorID(datumArrayList.get(position).getVendorId().toString());
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Products_Screen.class);
                intent.putExtra("vendor_id",datumArrayList.get(position).getVendorId());
                intent.putExtra("type","list");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_venderName,tv_venderAddress,tv_venderTimming,tv_rating;
        ImageView img_store;
        Button btn_unfollow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_venderName = itemView.findViewById(R.id.tv_venderName);
            tv_venderAddress =  itemView.findViewById(R.id.tv_venderAddress);
            tv_venderTimming = itemView.findViewById(R.id.tv_venderTimming);
            img_store = itemView.findViewById(R.id.img_store);
            tv_rating = itemView.findViewById(R.id.tv_rating);
            btn_unfollow = itemView.findViewById(R.id.btn_unfollow);
        }
    }
}
