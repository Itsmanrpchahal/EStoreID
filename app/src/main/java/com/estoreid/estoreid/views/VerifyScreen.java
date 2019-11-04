package com.estoreid.estoreid.views;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.apiResponseModel.VerifyAPIReponse;
import com.estoreid.estoreid.views.controller.Controller;
import com.estoreid.estoreid.views.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class VerifyScreen extends AppCompatActivity implements Controller.VerifyAPI {


    @BindView(R.id.verify_logo)
    ImageView verifyLogo;
    @BindView(R.id.verify_text)
    TextView verifyText;
    @BindView(R.id.otpedittext)
    PinView otpedittext;
    @BindView(R.id.verify_tv)
    TextView verifyTv;
    @BindView(R.id.verify_confirm_bt)
    Button verifyConfirmBt;
    @BindView(R.id.verify_cancel)
    TextView verifyCancel;
    android.app.Dialog Dialog;
    Intent intent;
    String user_id="",otp;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_screen);
        ButterKnife.bind(this);
        controller = new Controller(this);
        Dialog = Utils.showDialog(this);
        Dialog.dismiss();
        listeners();
    }

    private void listeners() {
        intent = getIntent();
        if (intent!=null)
        {
            user_id = intent.getStringExtra("user_id");
        }

        verifyConfirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp = otpedittext.getText().toString();
                if (Utils.isOnline()!=false)
                {
                    if (!otp.equals(""))
                    {
                        Dialog.show();
                        controller.setVerifyAPI(user_id,otp);
                    }else {
                        Utils.showToastMessage(VerifyScreen.this,"Enter OTP to continue",getResources().getDrawable(R.drawable.ic_error_black_24dp));
                    }

                }else {
                    Utils.showToastMessage(VerifyScreen.this,"No Internet connection",getResources().getDrawable(R.drawable.ic_nointernet));
                }
            }
        });
    }

    @Override
    public void onSucess(Response<VerifyAPIReponse> verifyAPIResponse) {

        if (verifyAPIResponse.body().getStatus()==200)
        {
            Intent intent = new Intent(VerifyScreen.this, Login.class);
            startActivity(intent);
        }else {
            Utils.showToastMessage(this,""+verifyAPIResponse.message(),getResources().getDrawable(
                    R.drawable.ic_error_black_24dp
            ));
        }
    }

    @Override
    public void onError(String error) {
        Dialog.dismiss();
            Utils.showToastMessage(this,""+error,getResources().getDrawable(R.drawable.ic_error_black_24dp));
    }
}
