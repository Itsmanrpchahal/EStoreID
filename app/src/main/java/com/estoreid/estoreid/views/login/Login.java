package com.estoreid.estoreid.views.login;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.MainActivity;
import com.estoreid.estoreid.views.apiResponseModel.LoginAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.ResetAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.SetNewPasswordAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.SocailLoginAPIResponse;
import com.estoreid.estoreid.views.baseclass.BaseClass;
import com.estoreid.estoreid.views.controller.Controller;
import com.estoreid.estoreid.views.signup.SignUp;
import com.estoreid.estoreid.views.utils.Constants;
import com.estoreid.estoreid.views.utils.Utils;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;


public class Login extends BaseClass implements Controller.LoginAPI, Controller.ForgotPassword,Controller.SetNewPassword,Controller.setSocailLogin {

    public static int RC_SIGN_IN = 121;
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
    @BindView(R.id.loginfacebook)
    LoginButton loginfacebook;
    @BindView(R.id.facebook)
    LinearLayout facebook;
    @BindView(R.id.logingooglesignIn)
    SignInButton logingooglesignIn;
    @BindView(R.id.login_gmail)
    TextView loginGmail;
    @BindView(R.id.googleimage)
    ImageView googleimage;
    @BindView(R.id.gmail)
    LinearLayout gmail;
    @BindView(R.id.login_layout)
    RelativeLayout loginLayout;
    CallbackManager callbackManager;
    GoogleSignInClient googleSignInClient;
    SignInButton signInButton;
    GoogleApiClient googleApiClient;
    Controller controller;
    String email, password;
    Dialog Dialog, resetemailpopup, confirmdialog;
    @BindView(R.id.forgotpassowrd)
    TextView forgotpassowrd;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        controller = new Controller((Controller.LoginAPI) this, (Controller.ForgotPassword) this,(Controller.SetNewPassword)this,(Controller.setSocailLogin)this);
        Dialog = Utils.showDialog(this);
        Dialog.dismiss();
        Utils.abc(loginEmailEt, Login.this);

        listeners();
        getHashKey();
        Utils.checkPermissions(Login.this);
        loginfacebook.setReadPermissions(Arrays.asList("email", "public_profile"));
        facebookLogin();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void listeners() {
        loginLoginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = loginEmailEt.getText().toString();
                password = loginPasswordEt.getText().toString();

                if (Utils.isOnline() != false) {
                    if (TextUtils.isEmpty(loginEmailEt.getText().toString()) && TextUtils.isEmpty(loginPasswordEt.getText().toString())) {
                        loginEmailEt.setError("Enter email");
                        loginPasswordEt.setError("Enter Password");
                        forgotpassowrd.setVisibility(View.GONE);

                    } else if (TextUtils.isEmpty(email)) {
                        loginEmailEt.setError("Enter email");
                    } else if (TextUtils.isEmpty(password)) {
                        loginPasswordEt.setError("Enter Password");
                        forgotpassowrd.setVisibility(View.GONE);
                    } else {
                        Dialog.show();
                        controller.setLoginAPI(email, password,"12f1s2f1f2sfsd2f1f2d1sfff1vdv");
                    }

                } else {
                    Utils.showToastMessage(Login.this, "No Internet Connection", getResources().getDrawable(R.drawable.ic_nointernet));
                }
            }
        });

        loginSignupTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Utils.isOnline() == false) {
                    Utils.showToastMessage(Login.this, "No Internet Connection", getResources().getDrawable(R.drawable.ic_nointernet));
                } else {
                    loginfacebook.performClick();
                }
            }
        });

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isOnline() == false) {
                    Utils.showToastMessage(Login.this, "No Internet Connection", getResources().getDrawable(R.drawable.ic_nointernet));
                } else {
                    logingooglesignIn.performClick();
                    googleSignIn();
                }
            }
        });

        forgotpassowrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isOnline() != false) {
                    resetemaildialog();
                } else {
                    Utils.showToastMessage(Login.this, "No Internet Connection", getResources().getDrawable(R.drawable.ic_nointernet));
                }

            }
        });
    }

    private void resetemaildialog() {
        resetemailpopup = new Dialog(this);
        Window window = resetemailpopup.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        resetemailpopup.setContentView(R.layout.forgotemailpopup);
        resetemailpopup.setCancelable(false);
        resetemailpopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        resetemailpopup.show();

        TextView emailtext;
        EditText forgotdialogemail;
        Button forgotemailnext;
        ImageButton closedilog;

        forgotdialogemail = resetemailpopup.findViewById(R.id.forgotdialogemail);
        forgotemailnext = resetemailpopup.findViewById(R.id.forgotemailnext);
        closedilog = resetemailpopup.findViewById(R.id.closedilog);


        forgotemailnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = forgotdialogemail.getText().toString();
                Dialog.show();
                if (Utils.isOnline() != false) {
                    if (!TextUtils.isEmpty(forgotdialogemail.getText().toString())) {
                        resetemailpopup.show();
                        controller.setForgotPassword(email);
                    } else {
                        Dialog.dismiss();
                        forgotdialogemail.setError("Enter Email");
                    }

                } else {
                    Dialog.dismiss();
                    Utils.showToastMessage(Login.this, "No Internet Connection", getResources().getDrawable(R.drawable.ic_nointernet));
                }

            }
        });

        closedilog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetemailpopup.dismiss();
            }
        });
    }

    private void opennewpassworddialog(String email) {
        confirmdialog = new Dialog(this);
        Window window = confirmdialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        confirmdialog.setContentView(R.layout.setpassoworddialog);
        confirmdialog.setCancelable(false);
        confirmdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        confirmdialog.show();

        EditText opt, password, confirmpassword;
        ImageButton closedilog;
        Button forgotpassworddone;

        opt = confirmdialog.findViewById(R.id.enterotp);
        password = confirmdialog.findViewById(R.id.enternewpassword);
        confirmpassword = confirmdialog.findViewById(R.id.enterconfirmpassword);
        closedilog = confirmdialog.findViewById(R.id.closedilog);
        forgotpassworddone = confirmdialog.findViewById(R.id.forgotpassworddone);
        closedilog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmdialog.dismiss();
            }
        });

        forgotpassworddone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = opt.getText().toString();
                String passwrd = password.getText().toString();

                if (TextUtils.isEmpty(opt.getText().toString()) && TextUtils.isEmpty(password.getText().toString()) && TextUtils.isEmpty(confirmpassword.getText().toString()))
                {
                    opt.setError("Enter OTP");
                    password.setError("Enter new password");
                    confirmpassword.setError("Enter confirm password");
                }else if (TextUtils.isEmpty(opt.getText().toString()))
                {
                    opt.setError("Enter OTP");
                }else if (TextUtils.isEmpty(password.getText().toString()))
                {
                    password.setError("Enter new password");
                }else if (TextUtils.isEmpty(confirmpassword.getText().toString()))
                {
                    confirmpassword.setError("Enter confirm password");
                }else if (!password.getText().toString().matches(confirmpassword.getText().toString()))
                {
                    confirmpassword.setError("Password not matched");
                }
                else {
                    if (Utils.isOnline()!=false)
                    {
                        Dialog.show();
                        controller.setSetNewPassword(email,otp,passwrd);
                    }else {
                        Utils.showToastMessage(Login.this,"No Internet Connection",getResources().getDrawable(R.drawable.ic_nointernet));
                    }

                }
            }
        });
    }

    private void facebookLogin() {

        loginfacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
                Log.d("API123", loggedIn + " ??");

                //getFaceBookUserProfile(AccessToken.getCurrentAccessToken());

                //==============================get Facebook Information============================

                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                if (AccessToken.getCurrentAccessToken() != null) {
                                    try {
                                        String first_name = object.getString("first_name");
                                        String last_name = object.getString("last_name");
                                        String email = object.getString("email");
                                        String id = object.getString("id");

                                        //fbUsername = first_name + " " + last_name;
                                        Log.d("fbUsername", first_name + "  " + last_name);

                                        final String fbToken1 = AccessToken.getCurrentAccessToken().getToken();
                                        //fbEmail = email;
                                        String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                                        //imageurl = image_url;
                                        Log.d("first_name", first_name + "  " + last_name + " " + fbToken1 + " " + email + " " + image_url);

                                        //=============================register and login facebook here========================\
//                                        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
//                                            @Override
//                                            public void onSuccess(InstanceIdResult instanceIdResult) {
//                                                String device_token = instanceIdResult.getToken();
//                                                Log.e("token",device_token);
//                                                NormalLogin("normal",fbToken,fbEmail,fbUsername,"facebook",device_token);
//
//                                            }
//                                        });
                                        setStringVal(Constants.USER_IMAGE,image_url);
                                        setStringVal(Constants.USER_NAME,first_name+" "+last_name);
                                        controller.setSetSocailLogin(email,"12f1s2f1f2sfsd2f1f2d1sfff1vdv",first_name+" "+last_name,id,"google");

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "first_name,last_name,email,id");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void googleSignIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);

        } catch (ApiException e) {
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account) {

        if (account != null) {
            final String email = account.getEmail();
            final String username = account.getDisplayName();

            if (account.getPhotoUrl() != null) {
                final String image = account.getPhotoUrl().toString();
                final String token = account.getId();
                setStringVal(Constants.USER_IMAGE,image);
                setStringVal(Constants.USER_NAME,username);
                controller.setSetSocailLogin(email,"12f1s2f1f2sfsd2f1f2d1sfff1vdv",username,token,"google");

            } else if (account.getPhotoUrl() == null) {
                final String token = account.getId();
                final String image = "data:image/png;base64,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";
                setStringVal(Constants.USER_IMAGE,image);
                setStringVal(Constants.USER_NAME,username);
                controller.setSetSocailLogin(email,"12f1s2f1f2sfsd2f1f2d1sfff1vdv",username,token,"google");
            }
        }
    }

    //Genrate HashKey here...
    public void getHashKey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.estoreid.estoreid",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }


    @Override
    public void onSucess(Response<LoginAPIReponse> loginAPIReponseResponse) {
        if (loginAPIReponseResponse.body().getStatus() == 200) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            setStringVal(Constants.TOKEN, loginAPIReponseResponse.body().getData().getToken());
            setStringVal(Constants.USER_NUMBER,loginAPIReponseResponse.body().getData().getMobileNumber());
            setStringVal(Constants.USER_NAME,loginAPIReponseResponse.body().getData().getFirstName()+" "+loginAPIReponseResponse.body().getData().getLastName());
            setStringVal(Constants.USER_IMAGE,loginAPIReponseResponse.body().getData().getImage());
            startActivity(intent);
        } else {
            Dialog.dismiss();
            Utils.showToastMessage(Login.this, "" + loginAPIReponseResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_black_24dp));
        }
    }

    @Override
    public void onSucessForgot(Response<ResetAPIReponse> forgotPasswordResponse) {

        if (forgotPasswordResponse.body().getStatus() == 200) {
            Dialog.dismiss();
            resetemailpopup.dismiss();
            opennewpassworddialog(email);
        } else {
            Dialog.dismiss();
            Utils.showToastMessage(Login.this, "" + forgotPasswordResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_black_24dp));
        }
    }

    @Override
    public void onSuccessSetNewPassowrd(Response<SetNewPasswordAPIReponse> newPasswordAPIReponseResponse) {
        if (newPasswordAPIReponseResponse.body().getStatus()==200)
        {
            Dialog.dismiss();
            confirmdialog.dismiss();
            resetemailpopup.dismiss();
            Toast.makeText(context, ""+newPasswordAPIReponseResponse.body().getMessage(), Toast.LENGTH_SHORT).show();
        }else {
            Dialog.dismiss();
            Utils.showToastMessage(Login.this,""+newPasswordAPIReponseResponse.body().getMessage(),getResources().getDrawable(R.drawable.ic_error_black_24dp));
        }
    }

    @Override
    public void onSuccessSocialLogin(Response<SocailLoginAPIResponse> socailLoginAPIResponseResponse) {
        if (socailLoginAPIResponseResponse.body().getStatus()==200)
        {
            Dialog.dismiss();
            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            setStringVal(Constants.TOKEN, socailLoginAPIResponseResponse.body().getData().getToken());
            startActivity(intent);
        }
    }

    @Override
    public void onError(String error) {
        Dialog.dismiss();
        Utils.showToastMessage(Login.this, "" + error, getResources().getDrawable(R.drawable.ic_error_black_24dp));
    }
}