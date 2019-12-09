package com.estoreid.estoreid.views;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.apiResponseModel.GetProfileResponse;
import com.estoreid.estoreid.views.apiResponseModel.UploadProfileResponse;
import com.estoreid.estoreid.views.controller.Controller;
import com.estoreid.estoreid.views.utils.Constants;
import com.estoreid.estoreid.views.utils.Utils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.rilixtech.widget.countrycodepicker.Country;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickClick;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;
import retrofit2.Response;

public class SettingScreen extends BaseActivity implements IPickResult, Controller.UploadProfile, Controller.GetProfile {

    public static int RESULT_LOAD_IMAGE = 101;
    public static int REQUEST_CAMERA = 102;
    public static int MY_PERMISSIONS_REQUEST_CAMERA = 1242;
    String[] dialogOptions = new String[]{"Camera", "Gallery", "Cancel"};
    Uri uri;
    Bitmap bitmap;
    MultipartBody.Part part;
    File filesDir;
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
    Controller controller;
    Dialog Dialog;
    String firstname, lastname, email, phoneno, gender, dob, myFormat;
    DatePickerDialog.OnDateSetListener dateSetListener;
    Calendar calendar;
    Dialog dialog;
    String getGender, frommilles,countrycode="91";
    @BindView(R.id.ccp)
    CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);
        ButterKnife.bind(this);

        controller = new Controller((Controller.UploadProfile) this, (Controller.GetProfile) this);
        Dialog = Utils.showDialog(this);
        Dialog.show();
        calendar = Calendar.getInstance();
        filesDir = getFilesDir();
        listerners();
        if (Utils.isOnline() != false) {
            Dialog.show();
            controller.GetProfile("Bearer " + getStringVal(Constants.TOKEN));
        } else {
            Dialog.dismiss();
            Utils.showToastMessage(SettingScreen.this, "No Internet Connection", getResources().getDrawable(R.drawable.ic_nointernet));
        }

    }

    private void listerners() {

        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                countrycode = selectedCountry.getPhoneCode();
            }
        });

        backSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        settingDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SettingScreen.this, R.style.DialogTheme,
                        dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//                datePickerDialog.getDatePicker().setMinDate();
                datePickerDialog.show();
            }
        });

        settingGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });


        settingUploadpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                selectPhoto();
                PickImageDialog.build(new PickSetup()
                        .setButtonOrientation(LinearLayout.HORIZONTAL)).show(SettingScreen.this).setOnClick(new IPickClick() {
                    @Override
                    public void onGalleryClick() {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, RESULT_LOAD_IMAGE);
                    }

                    @Override
                    public void onCameraClick() {
                        if (ContextCompat.checkSelfPermission(SettingScreen.this,
                                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                            if (ActivityCompat.shouldShowRequestPermissionRationale(SettingScreen.this, Manifest.permission.CAMERA)) {

                                ActivityCompat.requestPermissions(SettingScreen.this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                            } else {
                                ActivityCompat.requestPermissions(SettingScreen.this,
                                        new String[]{Manifest.permission.CAMERA},
                                        MY_PERMISSIONS_REQUEST_CAMERA);
                            }

                        } else {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, REQUEST_CAMERA);
                        }
                    }
                });
            }
        });

        settingSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname = settingFirstname.getText().toString();
                lastname = settingLastname.getText().toString();
                email = settingEmail.getText().toString();
                phoneno = settingPhnno.getText().toString();
                gender = settingGender.getText().toString();
                dob = settingDob.getText().toString();

                Log.d("data+++++", firstname + " " + lastname + " " + email + " " + phoneno + " " + gender + " " + dob + "  " + part);
//                Dialog.show();

                if (TextUtils.isEmpty(firstname) && TextUtils.isEmpty(lastname) && TextUtils.isEmpty(email) &&
                        TextUtils.isEmpty(email) && TextUtils.isEmpty(phoneno) &&
                        TextUtils.isEmpty(gender) && TextUtils.isEmpty(dob)) {
                    settingFirstname.setError("enter firstname");
                    settingLastname.setError("enter lastname");
                    settingEmail.setError("enter email");
                    settingDob.setError("enter dob");
                    settingPhnno.setError("enter phoneno.");
                    settingGender.setError("enter gender");
                } else if (TextUtils.isEmpty(firstname)) {
                    settingFirstname.setError("enter firstname");
                } else if (TextUtils.isEmpty(lastname)) {
                    settingLastname.setError("enter lastname");
                } else if (TextUtils.isEmpty(email)) {
                    settingEmail.setError("enter email");
                } else if (TextUtils.isEmpty(phoneno)) {
                    settingPhnno.setError("enter phoneno.");
                } else if (TextUtils.isEmpty(gender)) {
                    settingGender.setError("enter gender");
                } else if (TextUtils.isEmpty(dob)) {
                    settingDob.setError("enter dob");
                } else {
                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = null;
                    try {
                        date = (Date) formatter.parse(dob);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    frommilles = String.valueOf(date.getTime());
                    if (Utils.isOnline() != false) {
                        Dialog.show();
                        controller.UploadProfile("Bearer " + getStringVal(Constants.TOKEN), firstname, lastname, email, phoneno, gender, frommilles, part);
                    } else {
                        Dialog.dismiss();
                        Utils.showToastMessage(SettingScreen.this, "No Internet Connection", getResources().getDrawable(R.drawable.ic_nointernet));
                    }

                }

            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                myFormat = "dd/MM/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat);
                settingDob.setText(simpleDateFormat.format(calendar.getTime()));


            }
        };
    }

    private void selectPhoto() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingScreen.this);
        builder.setTitle("Select Image");
        builder.setCancelable(false);


        builder.setItems(dialogOptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if ("Camera".equals(dialogOptions[which])) {
                    if (ContextCompat.checkSelfPermission(SettingScreen.this,
                            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                        if (ActivityCompat.shouldShowRequestPermissionRationale(SettingScreen.this, Manifest.permission.CAMERA)) {

                            ActivityCompat.requestPermissions(SettingScreen.this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                        } else {
                            ActivityCompat.requestPermissions(SettingScreen.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    MY_PERMISSIONS_REQUEST_CAMERA);
                        }

                    } else {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, REQUEST_CAMERA);
                    }

                } else if ("Gallery".equals(dialogOptions[which])) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, RESULT_LOAD_IMAGE);

                } else if ("Cancel".equals(dialogOptions[which])) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            Log.d("++++++++++", "++++ data log +++" + imageBitmap);

        }

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {


            uri = data.getData();

            try {
                CropImage.activity(uri).setGuidelines(CropImageView.Guidelines.ON).start(SettingScreen.this);
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                setStringVal(Constants.BitmapImage, String.valueOf(bitmap));

                settingUserImage.setImageBitmap(bitmap);
                part = Utils.sendImageFileToserver(bitmap, "user_image", SettingScreen.this);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri imageURI = result.getUri();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageURI);
                    Glide.with(SettingScreen.this).load(imageURI).into(settingUserImage);
                    Utils.encodeTobase64(bitmap);
                    part = Utils.sendImageFileToserver(bitmap, "user_image", SettingScreen.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            ActivityCompat.requestPermissions(SettingScreen.this, new String[]{Manifest.permission.CAMERA}, requestCode);
            Bundle bundle = data.getExtras();
            bitmap = (Bitmap) bundle.get("data");

            settingUserImage.setImageBitmap(bitmap);
            Utils.encodeTobase64(bitmap);

            try {
                part = Utils.sendImageFileToserver(bitmap, "user_image", SettingScreen.this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            try {
                part = Utils.sendImageFileToserver(bitmap, "user_image", SettingScreen.this);


            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            //Handle possible errors
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSuccessUploadProfile(Response<UploadProfileResponse> uploadProfileResponseResponse) {
        Dialog.dismiss();
        if (uploadProfileResponseResponse.body().getStatus() == 200) {
            if (uploadProfileResponseResponse.body().getData().getImage() != null) {
                settingFirstname.setText(uploadProfileResponseResponse.body().getData().getFirstName());
                settingLastname.setText(uploadProfileResponseResponse.body().getData().getLastName());
                settingEmail.setText(uploadProfileResponseResponse.body().getData().getEmail());
                settingGender.setText(uploadProfileResponseResponse.body().getData().getGender());
                settingDob.setText(Utils.convertTimeStampDate(Long.parseLong(uploadProfileResponseResponse.body().getData().getDob())));
            }
        }
    }

    @Override
    public void onSuccessGetProfile(Response<GetProfileResponse> getProfileResponseResponse) {
        Dialog.dismiss();
        if (getProfileResponseResponse.body().getStatus() == 200) {
            settingFirstname.setText(getProfileResponseResponse.body().getData().get(0).getFirstName());
            settingLastname.setText(getProfileResponseResponse.body().getData().get(0).getLastName());
            settingEmail.setText(getProfileResponseResponse.body().getData().get(0).getEmail());
            settingPhnno.setText(getProfileResponseResponse.body().getData().get(0).getMobileNumber().toString());
            if (getProfileResponseResponse.body().getData().get(0).getGender() != null) {
                settingGender.setText(getProfileResponseResponse.body().getData().get(0).getGender());
            }

            if (getProfileResponseResponse.body().getData().get(0).getDob() != null) {
                settingDob.setText(Utils.convertTimeStampDate(Long.parseLong(getProfileResponseResponse.body().getData().get(0).getDob())));
            }

            if (getProfileResponseResponse.body().getData().get(0).getImage() != null) {
                setStringVal(Constants.USER_NAME, getProfileResponseResponse.body().getData().get(0).getFirstName() + " " + getProfileResponseResponse.body().getData().get(0).getLastName());
                setStringVal(Constants.USER_IMAGE, Constants.IMAGES + getProfileResponseResponse.body().getData().get(0).getImage());
                Glide.with(SettingScreen.this).load(Constants.IMAGES + getProfileResponseResponse.body().getData().get(0).getImage()).into(settingUserImage);
                new getImagefromURL(settingUserImage).execute(Constants.IMAGES + getProfileResponseResponse.body().getData().get(0).getImage());
            }
        }
    }

    @Override
    public void onError(String error) {
        Dialog.dismiss();
        Utils.showToastMessage(this, error, getResources().getDrawable(R.drawable.ic_error_black_24dp));
    }


    //Get Image from Url
    public class getImagefromURL extends AsyncTask<String, Void, Bitmap> {

        ImageView imageView;

        public getImagefromURL(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... url) {

            String urlimage = url[0];

            bitmap = null;


            try {
                InputStream stream = new URL(urlimage).openStream();
                bitmap = BitmapFactory.decodeStream(stream);
                part = Utils.sendImageFileToserver(bitmap, "user_image", SettingScreen.this);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }


    private void dialog() {
        dialog = new Dialog(SettingScreen.this);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(R.layout.selectgender);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        TextView male, female;
        male = dialog.findViewById(R.id.gendermale);
        female = dialog.findViewById(R.id.genderfemale);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getGender = "Male";
                dialog.dismiss();
                settingGender.setText(getGender);
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getGender = "Female";
                dialog.dismiss();
                settingGender.setText(getGender);
            }
        });
    }
}
