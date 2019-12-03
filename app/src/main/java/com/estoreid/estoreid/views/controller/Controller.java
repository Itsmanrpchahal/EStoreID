package com.estoreid.estoreid.views.controller;

import com.estoreid.estoreid.views.apiResponseModel.AddCartQuantityResponse;
import com.estoreid.estoreid.views.apiResponseModel.AddToCartResponse;
import com.estoreid.estoreid.views.apiResponseModel.CartItemsResponse;
import com.estoreid.estoreid.views.apiResponseModel.FavVendorsResponse;
import com.estoreid.estoreid.views.apiResponseModel.FilterDataResponse;
import com.estoreid.estoreid.views.apiResponseModel.FollowAPIResponse;
import com.estoreid.estoreid.views.apiResponseModel.GetProfileResponse;
import com.estoreid.estoreid.views.apiResponseModel.LoginAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.ProductDetailResponse;
import com.estoreid.estoreid.views.apiResponseModel.ProductsAPI;
import com.estoreid.estoreid.views.apiResponseModel.RegisterAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.ResetAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.SetNewPasswordAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.SocailLoginAPIResponse;
import com.estoreid.estoreid.views.apiResponseModel.UploadProfileResponse;
import com.estoreid.estoreid.views.apiResponseModel.VendorAPIResponse;
import com.estoreid.estoreid.views.apiResponseModel.VerifyAPIReponse;
import com.estoreid.estoreid.views.utils.Utils;
import com.estoreid.estoreid.views.webApi.WebAPI;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class Controller {

    public WebAPI webAPI;
    public RegisterAPI registerAPI;
    public VerifyAPI verifyAPI;
    public LoginAPI loginAPI;
    public ForgotPassword forgotPassword;
    public SetNewPassword setNewPassword;
    public setSocailLogin setSocailLogin;
    public VendorList vendorList;
    public FollowUnfollow followUnfollow;
    public Products products;
    public FilterScreen filterScreen;
    public ProductDetail productDetail;
    public GetProfile getProfile;
    public AddToCart addToCart;
    public UploadProfile uploadProfile;
    public FavStore favStore;
    public CartItems cartItems;
    public AddCartItemQuantity addCartItemQuantity;


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

    //vendor list
    public Controller(VendorList vendorList1,FollowUnfollow followUnfollow1)
    {
        vendorList = vendorList1;
        followUnfollow = followUnfollow1;
        webAPI = new WebAPI();
    }

    //products api
    public Controller(Products products1)
    {
        products = products1;
        webAPI = new WebAPI();
    }

    //filter screen
    public Controller(FilterScreen filterScreen1)
    {
        filterScreen = filterScreen1;
        webAPI = new WebAPI();
    }

    //product details
    public Controller(ProductDetail productDetail1,AddToCart addToCart1)
    {
        productDetail = productDetail1;
        addToCart = addToCart1;
        webAPI = new WebAPI();
    }

    //get profile
    public Controller(GetProfile getProfile1)
    {
        getProfile = getProfile1;
        webAPI = new WebAPI();
    }

    //uploadProfile -- getProfile
    public Controller(UploadProfile uploadProfile1,GetProfile getProfile1)
    {
        uploadProfile = uploadProfile1;
        getProfile = getProfile1;
        webAPI = new WebAPI();
    }

    //fav strore
    public Controller(FavStore favStore1,FollowUnfollow followUnfollow1)
    {
        favStore = favStore1;
        followUnfollow = followUnfollow1;
        webAPI = new WebAPI();
    }

    //cart Items
    public Controller(CartItems cartItems1,AddCartItemQuantity addCartItemQuantity1)
    {
        cartItems = cartItems1;
        addCartItemQuantity = addCartItemQuantity1;
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

    public void setVendorList(String token,String lat,String lng,String pincode)
    {
        webAPI.getApi().vendorlist(token,lat,lng,pincode).enqueue(new Callback<VendorAPIResponse>() {
            @Override
            public void onResponse(Call<VendorAPIResponse> call, Response<VendorAPIResponse> response) {
                if (response!=null)
                {
                    Response<VendorAPIResponse> vendorListResponse = response;
                    vendorList.onSucessVendorList(vendorListResponse);
                }
            }

            @Override
            public void onFailure(Call<VendorAPIResponse> call, Throwable t) {
                vendorList.onError(t.getMessage());
            }
        });
    }

    public void setFollowUnfollow(String token,String vendor_id)
    {
        webAPI.getApi().FollowVendor(token,vendor_id).enqueue(new Callback<FollowAPIResponse>() {
            @Override
            public void onResponse(Call<FollowAPIResponse> call, Response<FollowAPIResponse> response) {
                if (response!=null)
                {
                    Response<FollowAPIResponse> followAPIResponseResponse = response;
                    followUnfollow.onSucessFollow(followAPIResponseResponse);
                }
            }

            @Override
            public void onFailure(Call<FollowAPIResponse> call, Throwable t) {
                followUnfollow.onError(t.getMessage());
            }
        });
    }

    public void Products(String token,String vendor_id)
    {
        webAPI.getApi().products(token,vendor_id).enqueue(new Callback<ProductsAPI>() {
            @Override
            public void onResponse(Call<ProductsAPI> call, Response<ProductsAPI> response) {
                if (response!=null)
                {
                    Response<ProductsAPI> productsAPIResponse = response;
                    products.onSuccessProduct(productsAPIResponse);
                }
            }

            @Override
            public void onFailure(Call<ProductsAPI> call, Throwable t) {
                    products.onError(t.getMessage());
            }
        });
    }

    public void FilterScreen(String token)
    {
        webAPI.getApi().filterscreen(token).enqueue(new Callback<FilterDataResponse>() {
            @Override
            public void onResponse(Call<FilterDataResponse> call, Response<FilterDataResponse> response) {
                if (response!=null)
                {
                    Response<FilterDataResponse> filterDataResponseResponse = response;
                    filterScreen.onSucessFilter(filterDataResponseResponse);
                }
            }

            @Override
            public void onFailure(Call<FilterDataResponse> call, Throwable t) {
                filterScreen.onError(t.getMessage());
            }
        });
    }

    public void ProductDetails(String token,String product_id)
    {
        webAPI.getApi().productdetails(token,product_id).enqueue(new Callback<ProductDetailResponse>() {
            @Override
            public void onResponse(Call<ProductDetailResponse> call, Response<ProductDetailResponse> response) {
                if (response!=null)
                {
                    Response<ProductDetailResponse> productDetailResponse = response;
                    productDetail.onSuccessProductDetail(productDetailResponse);
                }
            }

            @Override
            public void onFailure(Call<ProductDetailResponse> call, Throwable t) {
                productDetail.onError(t.getMessage());
            }
        });
    }

    public void Addtocart(String token,String product_id,String color_id,String size_id)
    {
        webAPI.getApi().addtocart(token,product_id,color_id,size_id).enqueue(new Callback<AddToCartResponse>() {
            @Override
            public void onResponse(Call<AddToCartResponse> call, Response<AddToCartResponse> response) {
                if (response!=null)
                {
                    Response<AddToCartResponse> addToCartResponseResponse = response;
                    addToCart.onSuccessAddToCart(addToCartResponseResponse);
                }
            }

            @Override
            public void onFailure(Call<AddToCartResponse> call, Throwable t) {
                addToCart.onError(t.getMessage());
            }
        });
    }

    public void GetProfile(String token)
    {
        webAPI.getApi().getProfile(token).enqueue(new Callback<GetProfileResponse>() {
            @Override
            public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {
                if (response!=null)
                {
                    Response<GetProfileResponse> getProfileResponseResponse = response;
                    getProfile.onSuccessGetProfile(getProfileResponseResponse);
                }
            }

            @Override
            public void onFailure(Call<GetProfileResponse> call, Throwable t) {
                getProfile.onError(t.getMessage());
            }
        });
    }

    public void UploadProfile(String token, String first_name, String last_name, String email, String phone, String gender, String dob, MultipartBody.Part part)
    {
        webAPI.getApi().uploadProfile(token,first_name,last_name,email,phone,gender,dob,part).enqueue(new Callback<UploadProfileResponse>() {
            @Override
            public void onResponse(Call<UploadProfileResponse> call, Response<UploadProfileResponse> response) {
                if (response!=null)
                {
                    Response<UploadProfileResponse> responseResponse = response;
                    uploadProfile.onSuccessUploadProfile(responseResponse);
                }
            }

            @Override
            public void onFailure(Call<UploadProfileResponse> call, Throwable t) {
                    uploadProfile.onError(t.getMessage());
            }
        });
    }

    public void FavStore(String token)
    {
        webAPI.getApi().favStores(token).enqueue(new Callback<FavVendorsResponse>() {
            @Override
            public void onResponse(Call<FavVendorsResponse> call, Response<FavVendorsResponse> response) {
                if (response!=null)
                {
                    Response<FavVendorsResponse> favVendorsResponseResponse = response;
                    favStore.onSuccessFavStore(favVendorsResponseResponse);
                }
            }

            @Override
            public void onFailure(Call<FavVendorsResponse> call, Throwable t) {
                    favStore.onError(t.getMessage());
            }
        });
    }


    public void CartItems(String token)
    {
        webAPI.getApi().cartItems(token).enqueue(new Callback<CartItemsResponse>() {
            @Override
            public void onResponse(Call<CartItemsResponse> call, Response<CartItemsResponse> response) {
                if (response != null)
                {
                    Response<CartItemsResponse> cartItemsResponseResponse = response;
                    cartItems.onSuccessCartItems(cartItemsResponseResponse);
                }
            }

            @Override
            public void onFailure(Call<CartItemsResponse> call, Throwable t) {
                    cartItems.onError(t.getMessage());
            }
        });
    }

    public void AddCartItemQuantity(String token,String cart_id,String quantint)
    {
        webAPI.getApi().addtcartquantity(token,cart_id,quantint).enqueue(new Callback<AddCartQuantityResponse>() {
            @Override
            public void onResponse(Call<AddCartQuantityResponse> call, Response<AddCartQuantityResponse> response) {
                if (response != null)
                {
                    Response<AddCartQuantityResponse> addCartItemQuantityResponse = response;
                    addCartItemQuantity.onSucessAddCartQuantity(addCartItemQuantityResponse);
                }
            }

            @Override
            public void onFailure(Call<AddCartQuantityResponse> call, Throwable t) {
                addCartItemQuantity.onError(t.getMessage());
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

    public interface VendorList{
        void onSucessVendorList(Response<VendorAPIResponse> vendorAPIResponseResponse);
        void onError(String error);
    }

    public interface FollowUnfollow{
        void onSucessFollow(Response<FollowAPIResponse> followAPIResponseResponse);
        void onError(String error);
    }

    public interface Products{
        void onSuccessProduct(Response<ProductsAPI> productsAPIResponse);
        void onError(String error);
    }

    public interface FilterScreen{
        void onSucessFilter(Response<FilterDataResponse> filterDataResponseResponse);
        void onError(String error);
    }

    public interface ProductDetail {
        void onSuccessProductDetail(Response<ProductDetailResponse> productDetailResponseResponse);
        void onError(String error);
    }

    public interface GetProfile{
        void onSuccessGetProfile(Response<GetProfileResponse> getProfileResponseResponse);
        void onError(String error);
    }

    public interface AddToCart{
        void onSuccessAddToCart(Response<AddToCartResponse> addToCartResponseResponse);
        void onError(String error);
    }

    public interface UploadProfile{
        void onSuccessUploadProfile(Response<UploadProfileResponse> uploadProfileResponseResponse);
        void onError(String error);
    }

    public interface FavStore{
        void onSuccessFavStore(Response<FavVendorsResponse> favVendorsResponseResponse);
        void onError(String error);
    }

    public interface CartItems{
        void onSuccessCartItems(Response<CartItemsResponse> cartItemsResponseResponse);
        void onError(String error);
    }

    public interface AddCartItemQuantity{
        void onSucessAddCartQuantity(Response<AddCartQuantityResponse> addCartItemQuantityResponse);
        void onError(String error);
    }
}
