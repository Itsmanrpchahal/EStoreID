package com.estoreid.estoreid.views.webApi;

import com.estoreid.estoreid.views.apiResponseModel.RegisterAPIReponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("register")
    Call<RegisterAPIReponse> register(
            @Field("first_name") String firstname,
            @Field("last_name") String last_name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("c_password") String c_password,
            @Field("mobile_number") String mobile_number
    );
}
