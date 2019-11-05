package com.estoreid.estoreid.views.signup;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.VerifyScreen;
import com.estoreid.estoreid.views.apiResponseModel.RegisterAPIReponse;
import com.estoreid.estoreid.views.controller.Controller;
import com.estoreid.estoreid.views.login.Login;
import com.estoreid.estoreid.views.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class SignUp extends AppCompatActivity implements Controller.RegisterAPI {

    android.app.Dialog Dialog;
    Controller controller;
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
    Button signup_bt;
    @BindView(R.id.allready_account_tv)
    TextView allreadyAccountTv;
    String firstname,lastname,email,mobile,password,cpassword,user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        controller = new Controller(SignUp.this);
        Dialog = Utils.showDialog(this);
        Dialog.dismiss();
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




        signup_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(firstnameEt.getText().toString()) && TextUtils.isEmpty(lastnameEt.getText().toString()) && TextUtils.isEmpty(emailEt.getText().toString())
                && TextUtils.isEmpty(mobileNoEt.getText().toString()) && TextUtils.isEmpty(PasswordEt.getText().toString()) && TextUtils.isEmpty(confirmPasswordEt.getText().toString()))
                {
                    firstnameEt.setError("enter firstname");
                    lastnameEt.setError("enter lastname");
                    emailEt.setError("enter email");
                    mobileNoEt.setError("enter mobile number");
                    PasswordEt.setError("enter password");
                    confirmPasswordEt.setError("enter confirm password");
                }else if (TextUtils.isEmpty(firstnameEt.getText().toString()))
                {

                }else if (TextUtils.isEmpty(lastnameEt.getText().toString()))
                {
                    lastnameEt.setError("enter lastname");
                }else if (TextUtils.isEmpty(emailEt.getText().toString()))
                {
                    emailEt.setError("enter email");
                }else if (TextUtils.isEmpty(mobileNoEt.getText().toString()))
                {
                    mobileNoEt.setError("enter mobile number");
                }else if (TextUtils.isEmpty(PasswordEt.getText().toString()))
                {
                    PasswordEt.setError("enter password");
                }else if (TextUtils.isEmpty(confirmPasswordEt.getText().toString()))
                {
                    confirmPasswordEt.setError("enter confirm password");
                }else if (!PasswordEt.getText().toString().matches(confirmPasswordEt.getText().toString()))
                {
                    confirmPasswordEt.setError("Password not matched");
                }else if (!policyRadioBt.isChecked())
                {
                    Utils.showToastMessage(SignUp.this,"Agree T&C",getResources().getDrawable(R.drawable.ic_nointernet));
                }else {

                    firstname = firstnameEt.getText().toString();
                    lastname = lastnameEt.getText().toString();
                    email = emailEt.getText().toString();
                    mobile = mobileNoEt.getText().toString();
                    password = confirmPasswordEt.getText().toString();
                    cpassword = confirmPasswordEt.getText().toString();

                    if (Utils.isOnline()!=false)
                    {
                        Dialog.show();
                        controller.setRegisterAPI(firstname,lastname,email,password,cpassword,mobile);
                    }else {
                        Utils.showToastMessage(SignUp.this,"No Internet connection",getResources().getDrawable(R.drawable.ic_nointernet));
                    }



                }
            }
        });
    }

    @Override
    public void onSucess(Response<RegisterAPIReponse> registerAPIReponseResponse) {
            Dialog.dismiss();
        if (registerAPIReponseResponse.body().getStatus()==200)
        {
            user_id = String.valueOf(registerAPIReponseResponse.body().getData().getUserId());
            Intent intent = new Intent(SignUp.this, VerifyScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.putExtra("user_id",user_id);
            startActivity(intent);
        }else {
            Dialog.dismiss();
            Utils.showToastMessage(SignUp.this,""+registerAPIReponseResponse.body().getMessage(),getResources().getDrawable(R.drawable.ic_error_black_24dp));
        }

    }

    @Override
    public void onError(String error) {
        Dialog.dismiss();
        Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
    }
}
