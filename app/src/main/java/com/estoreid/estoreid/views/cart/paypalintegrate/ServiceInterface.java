package com.estoreid.estoreid.views.cart.paypalintegrate;

import com.estoreid.estoreid.views.utils.Constants;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * Created by kamal_bunkar on 26-12-2017.
 */

public interface ServiceInterface {

    // method,, return type ,, secondary url
    @Multipart
    @POST(Constants.SERVER_main_folder)
    Call<String> getHashCall(
            @Part("key") RequestBody key,
            @Part("txnid") RequestBody txnid,
            @Part("amount") RequestBody amount,
            @Part("productinfo") RequestBody producinfo,
            @Part("firstname") RequestBody firstname,
            @Part("email") RequestBody email
    );

}
