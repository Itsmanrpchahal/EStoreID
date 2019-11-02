package com.estoreid.estoreid.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.estoreid.estoreid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerifyScreen extends AppCompatActivity {

    EditText opt_1, opt_2;
    Button verify_confirm_bt;
    @BindView(R.id.verify_logo)
    ImageView verifyLogo;
    @BindView(R.id.verify_text)
    TextView verifyText;
    @BindView(R.id.opt_1)
    EditText opt1;
    @BindView(R.id.opt_2)
    EditText opt2;
    @BindView(R.id.opt_3)
    EditText opt3;
    @BindView(R.id.opt_4)
    EditText opt4;
    @BindView(R.id.opt_5)
    EditText opt5;
    @BindView(R.id.opt_6)
    EditText opt6;
    @BindView(R.id.otp_layout)
    LinearLayout otpLayout;
    @BindView(R.id.verify_tv)
    TextView verifyTv;
    @BindView(R.id.verify_confirm_bt)
    Button verifyConfirmBt;
    @BindView(R.id.verify_cancel)
    TextView verifyCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_screen);
        ButterKnife.bind(this);

        listeners();
    }

    private void listeners() {
        verifyConfirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerifyScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });


        opt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if (count==1)
               {
                   opt2.requestFocus();
               }
            }

            @Override
            public void afterTextChanged(Editable s) {

                Toast.makeText(VerifyScreen.this, ""+s.length(), Toast.LENGTH_SHORT).show();

            }
        });

        opt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count==1)
                {
                    opt3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        opt3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count==1)
                {
                    opt4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        opt4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count==1)
                {
                    opt5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        opt5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count==1)
                {
                    opt6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}
