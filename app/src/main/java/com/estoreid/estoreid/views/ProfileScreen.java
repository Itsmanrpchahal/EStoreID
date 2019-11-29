package com.estoreid.estoreid.views;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.apiResponseModel.GetProfileResponse;
import com.estoreid.estoreid.views.baseclass.BaseClass;
import com.estoreid.estoreid.views.controller.Controller;
import com.estoreid.estoreid.views.utils.Constants;
import com.estoreid.estoreid.views.utils.Utils;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class ProfileScreen extends BaseClass implements Controller.GetProfile {


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
    Dialog Dialog;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        ButterKnife.bind(this);
        listerners();
        Dialog = Utils.showDialog(this);
        Dialog.show();
        controller = new Controller(this);
        controller.GetProfile("Bearer "+getStringVal(Constants.TOKEN));

        setData();

    }

    private void setData() {
        String image = getStringVal(Constants.USER_IMAGE);
        String name = getStringVal(Constants.USER_NAME);
        Glide.with(ProfileScreen.this).load(image).into(profileUserimage);
        profileUsername.setText(name);
    }

    private void listerners() {
        profileBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onSuccessGetProfile(Response<GetProfileResponse> getProfileResponseResponse) {
        Dialog.dismiss();
        if (getProfileResponseResponse.body().getStatus()==200)
        {
            profileUsername.setText(getProfileResponseResponse.body().getData().get(0).getFirstName()+" "+getProfileResponseResponse.body().getData().get(0).getLastName());
            profileUsernameet.setText(getProfileResponseResponse.body().getData().get(0).getFirstName()+" "+getProfileResponseResponse.body().getData().get(0).getLastName());
            profileEmail.setText(getProfileResponseResponse.body().getData().get(0).getEmail());
            profilePhnno.setText(getProfileResponseResponse.body().getData().get(0).getMobileNumber());
        }
    }

    @Override
    public void onError(String error) {
        Dialog.dismiss();Utils.showToastMessage(ProfileScreen.this, error, getResources().getDrawable(R.drawable.ic_error_black_24dp));Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}
