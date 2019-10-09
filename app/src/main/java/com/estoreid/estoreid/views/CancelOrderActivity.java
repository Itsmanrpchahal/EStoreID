package com.estoreid.estoreid.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.estoreid.estoreid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CancelOrderActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.backontoolbar)
    ImageButton backontoolbar;
    @BindView(R.id.cart_toolbar)
    ImageButton cartToolbar;
    @BindView(R.id.serach_toolbar)
    ImageButton serachToolbar;
    @BindView(R.id.toolbartitle)
    TextView toolbartitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_question)
    ImageView imgQuestion;
    @BindView(R.id.tv_cancel_Order)
    TextView tvCancelOrder;
    @BindView(R.id.tv_alertMsg)
    TextView tvAlertMsg;
    @BindView(R.id.btn_yesCancel)
    TextView btn_yesCancel;
    @BindView(R.id.btn_noCancel)
    TextView btn_noCancel;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_order);
        ButterKnife.bind(this);

        init();
        eventListner();
    }

    private void init() {


        context = CancelOrderActivity.this;
        toolbar.setVisibility(View.VISIBLE);
        backontoolbar.setVisibility(View.VISIBLE);


    }

    private void eventListner() {
        btn_yesCancel.setOnClickListener(this);
        btn_noCancel.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_yesCancel:
                Intent intent = new Intent(CancelOrderActivity.this, SendFeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_noCancel:


                break;
            case R.id.backontoolbar:

                finish();
                break;
        }
    }
}
