package com.estoreid.estoreid.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.estoreid.estoreid.R;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingScreen extends AppCompatActivity {

    @BindView(R.id.backontoolbar)
    ImageButton backontoolbar;
    @BindView(R.id.toolbartitle)
    TextView toolbartitle;
    @BindView(R.id.cart_toolbar)
    ImageButton cartToolbar;
    @BindView(R.id.serach_toolbar)
    ImageButton serachToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.setting_user_image)
    RoundedImageView settingUserImage;
    @BindView(R.id.userimagelayout)
    RelativeLayout userimagelayout;
    @BindView(R.id.setting_username)
    EditText settingUsername;
    @BindView(R.id.setting_view1)
    View settingView1;
    @BindView(R.id.setting_email)
    EditText settingEmail;
    @BindView(R.id.setting_view2)
    View settingView2;
    @BindView(R.id.setting_phnno)
    EditText settingPhnno;
    @BindView(R.id.setting_view3)
    View settingView3;
    @BindView(R.id.setting_gender)
    EditText settingGender;
    @BindView(R.id.setting_view4)
    View settingView4;
    @BindView(R.id.setting_dob)
    EditText settingDob;
    @BindView(R.id.setting_view5)
    View settingView5;
    @BindView(R.id.setting_changepassword)
    EditText settingChangepassword;
    @BindView(R.id.setting_view6)
    View settingView6;
    @BindView(R.id.setting_confirmpassword)
    EditText settingConfirmpassword;
    @BindView(R.id.setting_view7)
    View settingView7;
    @BindView(R.id.setting_save)
    Button settingSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);
        ButterKnife.bind(this);


        listerners();


    }

    private void listerners() {
        backontoolbar.setVisibility(View.VISIBLE);
        serachToolbar.setVisibility(View.GONE);
        cartToolbar.setVisibility(View.GONE);
        toolbartitle.setVisibility(View.VISIBLE);
        toolbartitle.setText("Setting");
        backontoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
