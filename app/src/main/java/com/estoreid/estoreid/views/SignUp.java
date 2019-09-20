package com.estoreid.estoreid.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.estoreid.estoreid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUp extends AppCompatActivity {


    @BindView(R.id.signup_logo)
    ImageView signupLogo;
    @BindView(R.id.firstname_et)
    EditText firstnameEt;
    @BindView(R.id.signup_view1)
    View signupView1;
    @BindView(R.id.lastname_et)
    EditText lastnameEt;
    @BindView(R.id.signup_view2)
    View signupView2;
    @BindView(R.id.name_layout)
    LinearLayout nameLayout;
    @BindView(R.id.email_et)
    EditText emailEt;
    @BindView(R.id.signup_view3)
    View signupView3;
    @BindView(R.id.mobile_no_et)
    EditText mobileNoEt;
    @BindView(R.id.signup_view4)
    View signupView4;
    @BindView(R.id.Password_et)
    EditText PasswordEt;
    @BindView(R.id.signup_view5)
    View signupView5;
    @BindView(R.id.confirm_password_et)
    EditText confirmPasswordEt;
    @BindView(R.id.signup_view6)
    View signupView6;
    @BindView(R.id.policy_radio_bt)
    RadioButton policyRadioBt;
    @BindView(R.id.signup_bt)
    Button signupBt;
    @BindView(R.id.allready_account_tv)
    TextView allreadyAccountTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        listeners();
    }

    private void listeners() {
        allreadyAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        signupBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, VerifyScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

            }
        });
    }


}
