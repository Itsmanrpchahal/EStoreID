package com.estoreid.estoreid.views.controller;

import com.estoreid.estoreid.views.apiResponseModel.LoginAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.RegisterAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.VerifyAPIReponse;
import com.estoreid.estoreid.views.webApi.WebAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Controller {

    public WebAPI webAPI;
    public RegisterAPI registerAPI;
    public VerifyAPI verifyAPI;
    public LoginAPI loginAPI;

    //registerAPI
    public Controller(RegisterAPI registerAPI1) {
        registerAPI = registerAPI1;
        webAPI = new WebAPI();
    }

    //verifyAPI
    public Controller(VerifyAPI verifyAPI1)
    {
        verifyAPI = verifyAPI1;
        webAPI = new WebAPI();
    }

    //loginAPI
    public Controller(LoginAPI loginAPI1)
    {
        loginAPI = loginAPI1;
        webAPI = new WebAPI();
    }


    //rest  API's
    public void setRegisterAPI(String firstname, String lastname, String email, String password, String confirmPassword, String mobilenumber) {
        webAPI.getApi().register(firstname,lastname,email,password,confirmPassword,mobilenumber).enqueue(new Callback<RegisterAPIReponse>() {
            @Override
            public void onResponse(Call<RegisterAPIReponse> call, Response<RegisterAPIReponse> response) {
                if (response!=null)
                {
                    Response<RegisterAPIReponse> registerAPIReponseResponse = response;
                    registerAPI.onSucess(registerAPIReponseResponse);
                }
            }

            @Override
            public void onFailure(Call<RegisterAPIReponse> call, Throwable t) {
                registerAPI.onError(t.getMessage());
            }
        });
    }


    public void setVerifyAPI(String user_id,String otp)
    {
        webAPI.getApi().verify(user_id, otp).enqueue(new Callback<VerifyAPIReponse>() {
            @Override
            public void onResponse(Call<VerifyAPIReponse> call, Response<VerifyAPIReponse> response) {
                if (response!=null)
                {
                    Response<VerifyAPIReponse> verifyAPIResponse = response;
                    verifyAPI.onSucess(verifyAPIResponse);
                }
            }

            @Override
            public void onFailure(Call<VerifyAPIReponse> call, Throwable t) {
                verifyAPI.onError(t.getMessage());
            }
        });
    }

    public void setLoginAPI(String email,String password)
    {
        webAPI.getApi().login(email, password).enqueue(new Callback<LoginAPIReponse>() {
            @Override
            public void onResponse(Call<LoginAPIReponse> call, Response<LoginAPIReponse> response) {
                if (response!=null)
                {
                    Response<LoginAPIReponse> loginAPIReponseResponse = response;
                    loginAPI.onSucess(loginAPIReponseResponse);
                }
            }

            @Override
            public void onFailure(Call<LoginAPIReponse> call, Throwable t) {
                loginAPI.onError(t.getMessage());
            }
        });
    }


    public interface RegisterAPI {
        void onSucess(Response<RegisterAPIReponse> registerAPIReponseResponse);

        void onError(String error);
    }

    public interface VerifyAPI{
        void onSucess(Response<VerifyAPIReponse> verifyAPIResponse);
        void onError(String error);
    }

    public interface LoginAPI{
        void onSucess(Response<LoginAPIReponse> loginAPIReponseResponse);
        void onError(String error);
    }
}
