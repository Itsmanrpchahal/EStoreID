package com.estoreid.estoreid.views.controller;

import com.estoreid.estoreid.views.apiResponseModel.LoginAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.RegisterAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.ResetAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.SetNewPasswordAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.SocailLoginAPIResponse;
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
    public ForgotPassword forgotPassword;
    public SetNewPassword setNewPassword;
    public setSocailLogin setSocailLogin;

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

    //loginAPI ----- forgotPasswordApi ------ SocialLogin
    public Controller(LoginAPI loginAPI1,ForgotPassword forgotPassword1,SetNewPassword setNewPassword1,setSocailLogin setSocailLogin1)
    {
        loginAPI = loginAPI1;
        forgotPassword = forgotPassword1;
        setNewPassword = setNewPassword1;
        setSocailLogin = setSocailLogin1;
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

    public void setLoginAPI(String email,String password,String device_token)
    {
        webAPI.getApi().login(email, password,device_token).enqueue(new Callback<LoginAPIReponse>() {
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

    public void setForgotPassword(String email)
    {
        webAPI.getApi().forget_password(email).enqueue(new Callback<ResetAPIReponse>() {
            @Override
            public void onResponse(Call<ResetAPIReponse> call, Response<ResetAPIReponse> response) {
                if (response !=null)
                {
                    Response<ResetAPIReponse> resetAPIReponseResponse = response;
                    forgotPassword.onSucessForgot(resetAPIReponseResponse);
                }
            }

            @Override
            public void onFailure(Call<ResetAPIReponse> call, Throwable t) {
                    forgotPassword.onError(t.getMessage());
            }
        });
    }

    public void setSetNewPassword(String email,String otp,String password)
    {
        webAPI.getApi().setNewPassword(otp,email,password).enqueue(new Callback<SetNewPasswordAPIReponse>() {
            @Override
            public void onResponse(Call<SetNewPasswordAPIReponse> call, Response<SetNewPasswordAPIReponse> response) {
                if (response!=null)
                {
                    Response<SetNewPasswordAPIReponse> newPasswordAPIReponseResponse = response;
                    setNewPassword.onSuccessSetNewPassowrd(newPasswordAPIReponseResponse);
                }
            }

            @Override
            public void onFailure(Call<SetNewPasswordAPIReponse> call, Throwable t) {
                    setNewPassword.onError(t.getMessage());
            }
        });
    }

    public void setSetSocailLogin(String email,String device_token, String firstname,String socialId,String type)
    {
        webAPI.getApi().socialLogin(email,device_token,firstname,socialId,type).enqueue(new Callback<SocailLoginAPIResponse>() {
            @Override
            public void onResponse(Call<SocailLoginAPIResponse> call, Response<SocailLoginAPIResponse> response) {
                if (response!=null)
                {
                    Response<SocailLoginAPIResponse> socailLoginAPIResponseResponse = response;
                    setSocailLogin.onSuccessSocialLogin(socailLoginAPIResponseResponse);
                }
            }

            @Override
            public void onFailure(Call<SocailLoginAPIResponse> call, Throwable t) {
                setSocailLogin.onError(t.getMessage());
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

    public interface ForgotPassword{
        void onSucessForgot(Response<ResetAPIReponse> forgotPasswordResponse);
        void onError(String error);
    }

    public interface SetNewPassword{
        void onSuccessSetNewPassowrd(Response<SetNewPasswordAPIReponse> newPasswordAPIReponseResponse);
        void onError(String error);
    }

    public interface setSocailLogin{
        void onSuccessSocialLogin(Response<SocailLoginAPIResponse> socailLoginAPIResponseResponse);
        void onError(String error);
    }
}
