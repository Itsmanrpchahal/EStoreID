package com.estoreid.estoreid.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.estoreid.estoreid.R;
import com.google.android.material.navigation.NavigationView;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        if (id == R.id.cart) {
            startAnimatedActivity(new Intent(getApplicationContext(), Cart_Activity.class));
        }
        else if (id == R.id.qrCode) {
            startAnimatedActivity(new Intent(getApplicationContext(), QRActivity.class));
        }else if (id == R.id.settings)
        {
            startAnimatedActivity(new Intent(getApplicationContext(),SettingScreen.class));
        }else if (id==R.id.myaccount)
        {
            startAnimatedActivity(new Intent(getApplicationContext(),ProfileScreen.class));
        }else if (id==R.id.myorders)
        {
            startAnimatedActivity(new Intent(getApplicationContext(),MyOrderActivity.class));
        }else if (id==R.id.myfavoritestore)
        {
            startAnimatedActivity(new Intent(getApplicationContext(),MyFavoriteStoreActivity.class));
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void startAnimatedActivity(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
