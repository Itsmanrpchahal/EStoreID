package com.estoreid.estoreid.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.estoreid.estoreid.R;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileScreen extends AppCompatActivity {


    @BindView(R.id.profile_back)
    ImageButton profileBack;
    @BindView(R.id.profile_userimage)
    RoundedImageView profileUserimage;
    @BindView(R.id.profile_username)
    TextView profileUsername;
    @BindView(R.id.profile_location)
    TextView profileLocation;
    @BindView(R.id.profile_following)
    Button profileFollowing;
    @BindView(R.id.profile_rewardspoints)
    Button profileRewardspoints;
    @BindView(R.id.layout_one)
    LinearLayout layoutOne;
    @BindView(R.id.profile_view1)
    View profileView1;
    @BindView(R.id.profile_usernameet)
    EditText profileUsernameet;
    @BindView(R.id.profile_view11)
    View profileView11;
    @BindView(R.id.profile_email)
    EditText profileEmail;
    @BindView(R.id.profile_view2)
    View profileView2;
    @BindView(R.id.profile_phnno)
    EditText profilePhnno;
    @BindView(R.id.profile_view3)
    View profileView3;
    @BindView(R.id.profile_gender)
    EditText profileGender;
    @BindView(R.id.profile_view4)
    View profileView4;
    @BindView(R.id.profile_dob)
    EditText profileDob;
    @BindView(R.id.setting_view6)
    View settingView6;
    @BindView(R.id.profile_logout)
    Button profileLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        ButterKnife.bind(this);
        listerners();

    }

    private void listerners() {
        profileBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
