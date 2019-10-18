package com.estoreid.estoreid.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.estoreid.estoreid.R;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QRActivity extends BaseActivity {

    @BindView(R.id.qr_back)
    ImageButton qrBack;
    @BindView(R.id.qr_userimage)
    RoundedImageView qrUserimage;
    @BindView(R.id.qr_text)
    TextView qrText;
    @BindView(R.id.qr_text1)
    TextView qrText1;
    @BindView(R.id.qr_image)
    ImageView qrImage;
    @BindView(R.id.qr_share)
    Button qrShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        ButterKnife.bind(this);

        listeners();
    }

    private void listeners() {
        qrBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
