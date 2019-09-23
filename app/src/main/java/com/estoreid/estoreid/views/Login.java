package com.estoreid.estoreid.views;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Login extends AppCompatActivity {


    EditText editText;
    @BindView(R.id.login_logo)
    ImageView loginLogo;
    @BindView(R.id.login_email_et)
    EditText loginEmailEt;
    @BindView(R.id.login_view1)
    View loginView1;
    @BindView(R.id.login_password_et)
    EditText loginPasswordEt;
    @BindView(R.id.login_password_layout)
    RelativeLayout loginPasswordLayout;
    @BindView(R.id.login_view2)
    View loginView2;
    @BindView(R.id.login_login_bt)
    Button loginLoginBt;
    @BindView(R.id.login_signup_tv)
    TextView loginSignupTv;
    @BindView(R.id.or_layout)
    RelativeLayout orLayout;
    @BindView(R.id.login_facebook)
    TextView loginFacebook;
    @BindView(R.id.facebookimage)
    ImageView facebookimage;
    @BindView(R.id.sort_layout)
    RelativeLayout sortLayout;
    @BindView(R.id.login_gmail)
    TextView loginGmail;
    @BindView(R.id.googleimage)
    ImageView googleimage;
    @BindView(R.id.login_layout)
    RelativeLayout loginLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Utils.abc(loginEmailEt, Login.this);

        listeners();
    }

    private void listeners() {
        loginSignupTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
    }

}
