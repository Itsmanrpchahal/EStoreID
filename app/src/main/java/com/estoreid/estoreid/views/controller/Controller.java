package com.estoreid.estoreid.views.controller;

import com.estoreid.estoreid.views.apiResponseModel.RegisterAPIReponse;
import com.estoreid.estoreid.views.webApi.WebAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Controller {

    public WebAPI webAPI;
    public RegisterAPI registerAPI;

    //registerAPI
    public Controller(RegisterAPI registerAPI1) {
        registerAPI = registerAPI1;
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

    public interface RegisterAPI {
        void onSucess(Response<RegisterAPIReponse> registerAPIReponseResponse);

        void onError(String error);
    }
}
