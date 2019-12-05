package com.estoreid.estoreid.views;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.DashBoardShopAdapter;
import com.estoreid.estoreid.views.adapter.FollowIF;
import com.estoreid.estoreid.views.apiResponseModel.FollowAPIResponse;
import com.estoreid.estoreid.views.apiResponseModel.VendorAPIResponse;
import com.estoreid.estoreid.views.controller.Controller;
import com.estoreid.estoreid.views.filter.FilterScreen;
import com.estoreid.estoreid.views.signup.SignUp;
import com.estoreid.estoreid.views.utils.Constants;
import com.estoreid.estoreid.views.utils.Utils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements OnMapReadyCallback, Controller.VendorList,Controller.FollowUnfollow {

    public static final int MY_PERMISSIONS_REQUEST_READ_LOCATION = 121;
    DashBoardShopAdapter adapter;
    String lat = "20.5937";
    String lng = "78.9629", citystr;
    GoogleMap Gmap;
    int AUTOCOMPLETE_REQUEST_CODE = 1;
    List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);
    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.toolbar_layout)
    RelativeLayout toolbarLayout;
    @BindView(R.id.mapview)
    MapView mapview;
    @BindView(R.id.select_current_loc)
    TextView selectCurrentLoc;
    @BindView(R.id.your_loc_tv)
    TextView yourLocTv;
    @BindView(R.id.loaction_tv)
    TextView loactionTv;
    @BindView(R.id.change_address)
    TextView changeAddress;
    @BindView(R.id.location_view)
    CardView locationView;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.sortby_tv)
    TextView sortbyTv;
    @BindView(R.id.sortimage)
    ImageView sortimage;
    @BindView(R.id.sort_layout)
    RelativeLayout sortLayout;
    @BindView(R.id.filter_tv)
    TextView filterTv;
    @BindView(R.id.filterimage)
    ImageView filterimage;
    @BindView(R.id.filter_layout)
    RelativeLayout filterLayout;
    @BindView(R.id.view2)
    LinearLayout view2;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.dashboard_recylerview)
    RecyclerView dashboardRecylerview;
    @BindView(R.id.item_layout)
    RelativeLayout itemLayout;
    @BindView(R.id.houseno)
    EditText houseno;
    @BindView(R.id.add_view1)
    View addView1;
    @BindView(R.id.street)
    EditText street;
    @BindView(R.id.add_view2)
    View addView2;
    @BindView(R.id.city)
    EditText city;
    @BindView(R.id.add_view3)
    View addView3;
    @BindView(R.id.pincode)
    EditText pincode;
    @BindView(R.id.add_view4)
    View addView4;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.save_new_address)
    Button saveNewAddress;
    @BindView(R.id.adress_layout)
    RelativeLayout adressLayout;
    MapView mapView;
    Controller controller;
    ArrayList<LatLng> locations = new ArrayList();
    ArrayList<VendorAPIResponse.Datum> vendorlist = new ArrayList<>();
    Dialog Dialog;
    @BindView(R.id.nostore)
    TextView nostore;
    Dialog popup;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        @SuppressLint("InflateParams")
        View contentView = inflater.inflate(R.layout.activity_main, null, false);
        drawer.addView(contentView, 0);
        ButterKnife.bind(this);
        controller = new Controller((Controller.VendorList)this,(Controller.FollowUnfollow)this);
        Dialog = Utils.showDialog(this);
        Dialog.show();
        mapView = findViewById(R.id.mapview);
        Places.initialize(this, getResources().getString(R.string.googleclientId));
        searchEt.setVisibility(View.VISIBLE);
        listerners();
        Utils.checkPermissions(MainActivity.this);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        MapsInitializer.initialize(this);
//        controller.setVendorList();

    }

    @Override
    protected void onResume() {
        super.onResume();
        statusCheck();
    }

    private void listerners() {

        changeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardRecylerview.setVisibility(View.GONE);
                adressLayout.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
                selectCurrentLoc.setVisibility(View.VISIBLE);
                yourLocTv.setVisibility(View.GONE);
                loactionTv.setVisibility(View.GONE);
                changeAddress.setVisibility(View.GONE);
            }
        });

        saveNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String postcode = pincode.getText().toString();
                if (!TextUtils.isEmpty(pincode.getText().toString())) {
                    Dialog.show();
                    controller.setVendorList("Bearer " + getStringVal(Constants.TOKEN), "", "", postcode);
                   layoutvisibilty();
                } else {
                    pincode.setError("Enter Pincode");
                }
            }
        });

        filterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FilterScreen.class);
                startActivity(intent);
            }
        });

        selectCurrentLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentLocation();
                layoutvisibilty();
            }
        });
    }

    private void layoutvisibilty() {
        adressLayout.setVisibility(View.GONE);
        view2.setVisibility(View.VISIBLE);
        dashboardRecylerview.setVisibility(View.VISIBLE);
        selectCurrentLoc.setVisibility(View.GONE);
        yourLocTv.setVisibility(View.VISIBLE);
        loactionTv.setVisibility(View.VISIBLE);
        changeAddress.setVisibility(View.VISIBLE);
    }


    @SuppressLint("WrongConstant")
    private void setAdapter(ArrayList<VendorAPIResponse.Datum> vendorlist) {
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        dashboardRecylerview.setHasFixedSize(true);
        dashboardRecylerview.setLayoutManager(linearLayout);
        adapter = new DashBoardShopAdapter(this, vendorlist);
        dashboardRecylerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.SetOnFollow(new FollowIF() {
            @Override
            public void onSuccess(String id) {
                controller.setFollowUnfollow("Bearer " + getStringVal(Constants.TOKEN),id);
            }
        });
    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
                dialog();
        }
    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        } else {
            CurrentLocation();
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.dismiss();
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    private void CurrentLocation() {
        final FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        } else {
            Task<Location> locationTask = client.getLastLocation();
            if (locationTask != null) {
                locationTask.addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {

                        task.addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // location(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));

                                if (location == null) {
                                    statusCheck();
                                    Toast.makeText(MainActivity.this, "Unable to get Location", Toast.LENGTH_SHORT).show();
                                } else {
                                    lat = String.valueOf(location.getLatitude());
                                    lng = String.valueOf(location.getLongitude());

                                    if (Utils.isOnline()!=false)
                                    {
                                        controller.setVendorList("Bearer " + getStringVal(Constants.TOKEN), lat, lng, "");
                                    }else {
                                        Utils.showToastMessage(MainActivity.this,"No Internet connection",getResources().getDrawable(R.drawable.ic_nointernet));
                                    }

                                }

                                Log.d("Location", "" + lat + "" + lng);
                                Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                                try {
                                    List<Address> address = (List<Address>) geocoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(lng), 1);

                                    if (address != null) {
                                        //citystr = address.get(0).getAdminArea();
                                        //cityname.setText(address.get(0).getLocality());
                                        loactionTv.setText(address.get(0).getLocality());
                                    } else {
                                        //cityname.setText("Address not found");
                                    }

                                    if (mapView != null) {
                                        mapView.onCreate(null);
                                        mapView.onResume();
                                        mapView.getMapAsync(MainActivity.this);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        task.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Location Not Found...,Enter Location Manually...", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Gmap = googleMap;
        Gmap.getUiSettings().setMyLocationButtonEnabled(false);
        Gmap.getUiSettings().setZoomControlsEnabled(false);
        Gmap.getUiSettings().setCompassEnabled(false);
        Gmap.setMyLocationEnabled(true);

        /*Rect rect = new Rect();
        mapview.getLocalVisibleRect(rect);
        Gmap.setPadding(rect.width()-1,rect.centerX()+15,rect.centerY(),rect.height());

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.mapstype));

            if (!success) {
            }
        } catch (Resources.NotFoundException e) {
        }*/


        CameraPosition cameraPosition = CameraPosition.builder().target(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))).zoom(15).bearing(0).tilt(40).build();
        Gmap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        for (int i=0;i<vendorlist.size();i++)
        {
            createMarker(Double.parseDouble(vendorlist.get(i).getLatitude()),Double.parseDouble(vendorlist.get(i).getLongitude()), vendorlist.get(i).getBusinessName());
        }
    }

    protected Marker createMarker(double latitude, double longitude, String title) {


        return Gmap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .icon(BitmapDescriptorFactory.defaultMarker()));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "No permission Granted", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }


    @Override
    public void onSucessVendorList(Response<VendorAPIResponse> vendorAPIResponseResponse) {
        vendorlist.clear();
        Dialog.dismiss();
        if (vendorAPIResponseResponse.body().getStatus() == 200) {

            for (int i = 0; i < vendorAPIResponseResponse.body().getData().size(); i++) {
                VendorAPIResponse.Datum datum = vendorAPIResponseResponse.body().getData().get(i);
                String lat = datum.getLatitude();
                String lng = datum.getLongitude();

                if (vendorAPIResponseResponse.body().getData().size()>0)
                {
                    nostore.setVisibility(View.GONE);
                }
                Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                try {
                    List<Address> address = (List<Address>) geocoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(lng), 1);

                    if (address != null) {
                        loactionTv.setText(address.get(0).getLocality());
                    } else {
                        loactionTv.setText("Address not found");
                    }

                    if (mapView != null) {
                        mapView.onCreate(null);
                        mapView.onResume();
                        mapView.getMapAsync(MainActivity.this);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                vendorlist.add(datum);
                setAdapter(vendorlist);
                Dialog.dismiss();
            }
        } else {
            vendorlist.clear();
            dashboardRecylerview.setVisibility(View.GONE);
            nostore.setVisibility(View.VISIBLE);
            Dialog.dismiss();
            Utils.showToastMessage(MainActivity.this, vendorAPIResponseResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_black_24dp));
        }
    }

    @Override
    public void onSucessFollow(Response<FollowAPIResponse> followAPIResponseResponse) {
        Dialog.dismiss();
        if (followAPIResponseResponse.body().getStatus()==200)
        {
            Utils.showToastMessage(context,""+followAPIResponseResponse.body().getMessage(),getResources().getDrawable(R.drawable.ic_check_black_24dp));
        }else
        {
            Utils.showToastMessage(context,""+followAPIResponseResponse.body().getMessage(),getResources().getDrawable(R.drawable.ic_check_black_24dp));
        }
    }

    @Override
    public void onError(String error) {
        vendorlist.clear();
        Dialog.dismiss();
        Utils.showToastMessage(MainActivity.this, error, getResources().getDrawable(R.drawable.ic_error_black_24dp));
    }


    private void dialog() {
        popup = new Dialog(MainActivity.this);
        Window window = popup.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setContentView(R.layout.exit_dialog);
        popup.setCancelable(true);
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popup.show();

        OK = popup.findViewById(R.id.ok_exit);
        CANCEL = popup.findViewById(R.id.cancel_exit);

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
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