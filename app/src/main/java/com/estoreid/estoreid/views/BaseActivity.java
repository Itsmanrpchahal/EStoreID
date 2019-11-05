package com.estoreid.estoreid.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
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
import com.estoreid.estoreid.views.login.Login;
import com.estoreid.estoreid.views.utils.Constants;
import com.google.android.material.navigation.NavigationView;
import com.makeramen.roundedimageview.RoundedImageView;

public class BaseActivity extends BaseClass implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    EditText search;
    Dialog popup;
    Button OK, CANCEL;
    View header;
    RoundedImageView imageView;
    TextView username;
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

        View headerLayout =
                navigationView.inflateHeaderView(R.layout.header_layout);
        navigationView.getHeaderView(0);
        imageView = headerLayout.findViewById(R.id.drawer_useriamge);
        username = headerLayout.findViewById(R.id.drawer_username);

        setData();

    }

    private void setData() {
        String image = getStringVal(Constants.USER_IMAGE);
        String name = getStringVal(Constants.USER_NAME);

        Glide.with(context).load(image).into(imageView);
        username.setText(name);

        Log.d("dataimage", getStringVal(Constants.USER_NAME) + "  " + getStringVal(Constants.USER_IMAGE));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.homet) {

            startAnimatedActivity(new Intent(getApplicationContext(), MainActivity.class));
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
