package com.estoreid.estoreid.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.baseclass.BaseClass;
import com.estoreid.estoreid.views.biometriclock.FingerprintActivity;
import com.estoreid.estoreid.views.cart.Cart_Activity;
import com.estoreid.estoreid.views.login.Login;
import com.estoreid.estoreid.views.utils.Constants;
import com.estoreid.estoreid.views.wishlist.WishList;
import com.google.android.material.navigation.NavigationView;
import com.makeramen.roundedimageview.RoundedImageView;

public class BaseActivity extends BaseClass implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawer;
    NavigationView navigationView;
    EditText search;
    Dialog popup,biometric;
    Button OK, CANCEL,BIOMETRICYES,BIOMETRICNO;
    View header;
    RoundedImageView imageView;
    TextView username,drawer_location;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        context = BaseActivity.this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        search = toolbar.findViewById(R.id.search_et);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(8).setActionView(R.layout.custom_reward);

        TextView textView = navigationView.findViewById(R.id.rewardcoinstext);
//        textView.setText("10");


        View headerLayout =
                navigationView.inflateHeaderView(R.layout.header_layout);
        navigationView.getHeaderView(0);
        imageView = headerLayout.findViewById(R.id.drawer_useriamge);
        username = headerLayout.findViewById(R.id.drawer_username);
        drawer_location = headerLayout.findViewById(R.id.drawer_location);


        setData();

    }

    private void setData() {
        String image = getStringVal(Constants.USER_IMAGE);
        String name = getStringVal(Constants.USER_NAME);
        String loc = getStringVal(Constants.CURRENT_LOCATION);

        Glide.with(context).load(Constants.IMAGES+image).into(imageView);
        username.setText(name);
        drawer_location.setText(loc);

        Log.d("dataimage", getStringVal(Constants.USER_NAME) + "  " + getStringVal(Constants.USER_IMAGE));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.homet) {
            startAnimatedActivity(new Intent(getApplicationContext(), MainActivity.class));
            drawer.closeDrawer(GravityCompat.START);
        }else if (id== R.id.wishlist)
        {
            startAnimatedActivity(new Intent(getApplicationContext(), WishList.class));
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.cart) {
            startAnimatedActivity(new Intent(getApplicationContext(), Cart_Activity.class));
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.settings) {
            startAnimatedActivity(new Intent(getApplicationContext(), SettingScreen.class));
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.myaccount) {
            startAnimatedActivity(new Intent(getApplicationContext(), ProfileScreen.class));
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.myorders) {
            startAnimatedActivity(new Intent(getApplicationContext(), MyOrderActivity.class));
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.myfavoritestore) {
            startAnimatedActivity(new Intent(getApplicationContext(), MyFavoriteStoreActivity.class));
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.logout) {
            dialog();
        } else if (id == R.id.offerzone) {
            startAnimatedActivity(new Intent(getApplicationContext(), OfferZone.class));
            drawer.closeDrawer(GravityCompat.START);
        }

        return true;
    }

    private void biometricdialog() {
        biometric = new Dialog(BaseActivity.this);
        Window window = biometric.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        biometric.setContentView(R.layout.biometricdialog);
        biometric.setCancelable(true);
        biometric.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        biometric.show();

        BIOMETRICYES = biometric.findViewById(R.id.yesbiometric);
        BIOMETRICNO = biometric.findViewById(R.id.nobiometric);

        BIOMETRICYES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaseActivity.this, FingerprintActivity.class);
                startActivity(intent);
            }
        });

        BIOMETRICNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                biometric.dismiss();
            }
        });
    }

    protected void startAnimatedActivity(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    private void dialog() {
        popup = new Dialog(BaseActivity.this);
        Window window = popup.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setContentView(R.layout.logoutdialog);
        popup.setCancelable(true);
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popup.show();

        OK = popup.findViewById(R.id.oklogout);
        CANCEL = popup.findViewById(R.id.cancellogout);

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearStringVal(Constants.TOKEN);
                Intent intent = new Intent(BaseActivity.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        CANCEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.dismiss();
            }
        });
    }
}
