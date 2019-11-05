package com.estoreid.estoreid.views.webApi;

import com.estoreid.estoreid.views.apiResponseModel.LoginAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.RegisterAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.ResetAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.SetNewPasswordAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.SocailLoginAPIResponse;
import com.estoreid.estoreid.views.apiResponseModel.VerifyAPIReponse;

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

    @FormUrlEncoded
    @POST("varify_otp")
    Call<VerifyAPIReponse> verify(
            @Field("user_id") String user_id,
            @Field("otp") String otp
    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginAPIReponse> login(
            @Field("email") String email,
            @Field("password") String password,
            @Field("device_token") String device_token
    );

    @FormUrlEncoded
    @POST("forget_password")
    Call<ResetAPIReponse> forget_password(
            @Field("email") String forget_password
    );

    @FormUrlEncoded
    @POST("set_password")
    Call<SetNewPasswordAPIReponse> setNewPassword(
            @Field("otp") String otp,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("social_login")
    Call<SocailLoginAPIResponse> socialLogin(
            @Field("email") String email,
            @Field("device_token") String device_token,
            @Field("first_name") String first_name,
            @Field("social_id") String social_id,
            @Field("type") String type
    );

}
