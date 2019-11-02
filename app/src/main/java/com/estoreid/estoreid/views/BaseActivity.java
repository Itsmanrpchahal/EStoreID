package com.estoreid.estoreid.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.estoreid.estoreid.R;
import com.google.android.material.navigation.NavigationView;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    EditText search;
    Dialog popup;
    Button OK,CANCEL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

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

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.homet) {

            startAnimatedActivity(new Intent(getApplicationContext(), MainActivity.class));
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (id == R.id.cart) {
            startAnimatedActivity(new Intent(getApplicationContext(), Cart_Activity.class));
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.settings)
        {
            startAnimatedActivity(new Intent(getApplicationContext(),SettingScreen.class));
            drawer.closeDrawer(GravityCompat.START);
        }else if (id==R.id.myaccount)
        {
            startAnimatedActivity(new Intent(getApplicationContext(),ProfileScreen.class));
            drawer.closeDrawer(GravityCompat.START);
        }else if (id==R.id.myorders)
        {
            startAnimatedActivity(new Intent(getApplicationContext(),MyOrderActivity.class));
            drawer.closeDrawer(GravityCompat.START);
        }else if (id==R.id.myfavoritestore)
        {
            startAnimatedActivity(new Intent(getApplicationContext(),MyFavoriteStoreActivity.class));
            drawer.closeDrawer(GravityCompat.START);
        }else if (id == R.id.logout)
        {
            dialog();

        }else if (id == R.id.offerzone)
        {
            startAnimatedActivity(new Intent(getApplicationContext(),OfferZone.class));
            drawer.closeDrawer(GravityCompat.START);
        }


        return true;
    }

    protected void startAnimatedActivity(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    private void dialog() {
        popup = new Dialog (BaseActivity.this);
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
