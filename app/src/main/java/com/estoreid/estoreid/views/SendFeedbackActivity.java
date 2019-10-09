package com.estoreid.estoreid.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.estoreid.estoreid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SendFeedbackActivity extends AppCompatActivity {

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
    @BindView(R.id.tv_cancel_Order)
    TextView tvCancelOrder;
    @BindView(R.id.tv_alertMsg)
    TextView tvAlertMsg;
    @BindView(R.id.btn_noCancel)
    TextView btnNoCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feedback);
        ButterKnife.bind(this);
        toolbar.setVisibility(View.VISIBLE);
        backontoolbar.setVisibility(View.VISIBLE);
        backontoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
