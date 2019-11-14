package com.estoreid.estoreid.views.utils;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.estoreid.estoreid.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Utils {

    private static final int MULTIPLE_PERMISSIONS = 10;
    Context context;

    //permissions
public static String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS};


    /*###################Code to Close the keyboard when your touch anywhere############*/
    public static void abc(final EditText relativeLayout, final Context activity) {
        relativeLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });


    }

    //check internet is online or not
    public static boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {


            return false;
        }

        return true;
    }


    public static void showToastMessage(Context context, String message, Drawable drawable){

        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate( R.layout.custom_toast, null );
        // set a message
        TextView text = (TextView) layout.findViewById(R.id.txtvw);
        text.setText(message);

        ImageView imageView = layout.findViewById(R.id.image);
        imageView.setImageDrawable(drawable);

        // Toast...
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM, 0, 20);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public static Dialog showDialog(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.custom_progress);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        return dialog;
    }

    public static boolean checkPermissions(Context context) {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(context, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions((Activity) context, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MULTIPLE_PERMISSIONS);
            return false;
        } else {
        }
        return true;
    }

   /* public static Dialog dialog(final Context context) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.nointernetdialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button done = dialog.findViewById(R.id.done_bt_on_no_net_dilog);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
//                System.exit(0);
//                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
//                homeIntent.addCategory(Intent.CATEGORY_HOME);
//                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                context.startActivity(homeIntent);

                dialog.dismiss();
            }
        });

        dialog.show();

        return dialog;
    }*/

    public static long calender_time_to_timestamp(String str_date) {
        long time_stamp = 0;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
            //SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            Date date = (Date) formatter.parse(str_date);
            time_stamp = date.getTime();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        time_stamp = time_stamp / 1000;
        return time_stamp;
    }

    //date convert into time stamp
    public static long calender_date_to_timestamp(String str_date) {

        long time_stamp = 0;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            //SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            Date date = (Date) formatter.parse(str_date);
            time_stamp = date.getTime();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        time_stamp = time_stamp / 1000;
        return time_stamp;
    }

    public static String convertTimeStampToTime(long timestamp) {
//        Calendar calendar = Calendar.getInstance();
//        TimeZone tz = TimeZone.getDefault();
//        calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
////        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
//        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
//        sdf.setTimeZone(tz);
//        Date currenTimeZone = new Date(timestamp * 1000);
//        return sdf.format(currenTimeZone);

        // Create a DateFormatter object for displaying date in specified format.
        DateFormat formatter = new SimpleDateFormat("hh:mm a");

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return formatter.format(calendar.getTime());
    }

    public static String convertTimeStampDate(long timestamp) {

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return formatter.format(calendar.getTime());
    }

    public static String convertTimeStampDateTime(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        TimeZone tz = TimeZone.getDefault();
        calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        sdf.setTimeZone(tz);
        Date currenTimeZone = new Date(timestamp * 1000);
        return sdf.format(currenTimeZone);
    }

    public MultipartBody.Part sendImageFileToserver(Bitmap bitMap) throws IOException {

        File filesDir = context.getFilesDir();
        File file = new File(filesDir, "avatar" + ".png");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitMap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
        byte[] bitmapdata = bos.toByteArray();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bitmapdata);
        fos.flush();
        fos.close();

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("avatar", file.getName(), reqFile);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "avatar");

        return body;
    }

}
