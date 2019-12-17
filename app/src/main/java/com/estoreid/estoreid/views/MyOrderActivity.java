package com.estoreid.estoreid.views;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.MyOrderAdapter;
import com.estoreid.estoreid.views.apiResponseModel.GetOrderListResponse;
import com.estoreid.estoreid.views.controller.Controller;
import com.estoreid.estoreid.views.utils.Constants;
import com.estoreid.estoreid.views.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class MyOrderActivity extends BaseActivity implements Controller.GetOrderList {


    @BindView(R.id.recycler_order)
    RecyclerView recyclerOrder;
    @BindView(R.id.back_onfav_store)
    ImageButton backOnfavStore;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    MyOrderAdapter myOrderAdapter;
    Controller controller;
    Dialog dialog;
    ArrayList<GetOrderListResponse.Datum> responseArrayList = new ArrayList<GetOrderListResponse.Datum>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        dialog = Utils.showDialog(this);
        controller = new Controller(this);
        controller.GetOrderList("Bearer " + getStringVal(Constants.TOKEN));

        backOnfavStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onSuccessGetOrders(Response<GetOrderListResponse> getOrderListResponse) {
        dialog.dismiss();
        responseArrayList.clear();
        if (getOrderListResponse.body().getStatus() == 200) {
            for (int i = 0; i < getOrderListResponse.body().getData().size(); i++) {
                GetOrderListResponse.Datum datum = getOrderListResponse.body().getData().get(i);
                responseArrayList.add(datum);
                Collections.reverse(responseArrayList);
                setAdapter(responseArrayList);

            }

        } else {
            Utils.showToastMessage(MyOrderActivity.this, getOrderListResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_black_24dp));
        }
    }

    @SuppressLint("WrongConstant")
    private void setAdapter(ArrayList<GetOrderListResponse.Datum> responseArrayList) {
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        recyclerOrder.setHasFixedSize(true);
        recyclerOrder.setLayoutManager(linearLayout);
        myOrderAdapter = new MyOrderAdapter(this, responseArrayList);
        recyclerOrder.setAdapter(myOrderAdapter);
        myOrderAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String error) {
        dialog.dismiss();
        Utils.showToastMessage(MyOrderActivity.this, error, getResources().getDrawable(R.drawable.ic_error_black_24dp));
    }
}
