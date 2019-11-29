package com.estoreid.estoreid.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.estoreid.estoreid.R;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingScreen extends AppCompatActivity {


    @BindView(R.id.back_setting)
    ImageButton backSetting;
    @BindView(R.id.setting_user_image)
    RoundedImageView settingUserImage;
    @BindView(R.id.userimagelayout)
    RelativeLayout userimagelayout;
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
    @BindView(R.id.setting_save)
    Button settingSave;
    @BindView(R.id.setting_firstname)
    EditText settingFirstname;
    @BindView(R.id.setting_view)
    View settingView;
    @BindView(R.id.setting_lastname)
    EditText settingLastname;
    @BindView(R.id.setting_uploadpic)
    ImageButton settingUploadpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);
        ButterKnife.bind(this);


        listerners();


    }

    private void listerners() {

        backSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        settingUploadpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
