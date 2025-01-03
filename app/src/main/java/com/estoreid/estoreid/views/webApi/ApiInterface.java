package com.estoreid.estoreid.views.webApi;

import com.estoreid.estoreid.views.OrderPlaced;
import com.estoreid.estoreid.views.apiResponseModel.AddCartQuantityResponse;
import com.estoreid.estoreid.views.apiResponseModel.AddToCartResponse;
import com.estoreid.estoreid.views.apiResponseModel.AddToWishlistResponse;
import com.estoreid.estoreid.views.apiResponseModel.CartItemsResponse;
import com.estoreid.estoreid.views.apiResponseModel.FavVendorsResponse;
import com.estoreid.estoreid.views.apiResponseModel.FilterDataResponse;
import com.estoreid.estoreid.views.apiResponseModel.FollowAPIResponse;
import com.estoreid.estoreid.views.apiResponseModel.GetBookOrderDetail;
import com.estoreid.estoreid.views.apiResponseModel.GetOrderListResponse;
import com.estoreid.estoreid.views.apiResponseModel.GetProfileResponse;
import com.estoreid.estoreid.views.apiResponseModel.GetWishlistProducts;
import com.estoreid.estoreid.views.apiResponseModel.LoginAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.OrderPlacedResponse;
import com.estoreid.estoreid.views.apiResponseModel.ProductDetailResponse;
import com.estoreid.estoreid.views.apiResponseModel.ProductsAPI;
import com.estoreid.estoreid.views.apiResponseModel.RegisterAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.RemoveCartItemResponse;
import com.estoreid.estoreid.views.apiResponseModel.ResetAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.SetNewPasswordAPIReponse;
import com.estoreid.estoreid.views.apiResponseModel.SocailLoginAPIResponse;
import com.estoreid.estoreid.views.apiResponseModel.UploadProfileResponse;
import com.estoreid.estoreid.views.apiResponseModel.VendorAPIResponse;
import com.estoreid.estoreid.views.apiResponseModel.VerifyAPIReponse;
import com.estoreid.estoreid.views.utils.Constants;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @FormUrlEncoded
    @POST("vendorsList")
    Call<VendorAPIResponse> vendorlist(
            @Header("Authorization") String token,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("pincode") String pincode
    );

    @FormUrlEncoded
    @POST("FollowVendor")
    Call<FollowAPIResponse> FollowVendor(
            @Header("Authorization") String token,
            @Field("vendor_id") String vendor_id
    );

    @FormUrlEncoded
    @POST("get_vendor_products")
    Call<ProductsAPI> products(
            @Header("Authorization") String token,
            @Field("vendor_id") String vendor_id
    );

    @POST("filterScreen")
    Call<FilterDataResponse> filterscreen(
            @Header("Authorization") String token
    );

    @FormUrlEncoded
    @POST("productDetails")
    Call<ProductDetailResponse> productdetails(
            @Header("Authorization") String token,
            @Field("product_id") String product_id
    );

    @POST("userProfile")
    Call<GetProfileResponse> getProfile(
            @Header("Authorization") String token
    );

    @FormUrlEncoded
    @POST("addToCart")
    Call<AddToCartResponse> addtocart(
      @Header("Authorization") String token,
      @Field("product_id") String product_id,
      @Field("color_id") String color_id,
      @Field("size_id") String size_id
    );

    @Multipart
    @POST("updateUserProfile")
    Call<UploadProfileResponse> uploadProfile(
            @Header("Authorization") String token,
            @Query("first_name") String first_name,
            @Query("last_name") String last_name,
            @Query("email") String email,
            @Query("phone") String phone,
            @Query("gender") String gender,
            @Query("dob") String dob,
            @Part MultipartBody.Part user_image
    );



    @POST("followVendorsList")
    Call<FavVendorsResponse> favStores(
         @Header("Authorization") String token
    );

    @POST("get_cart_products")
    Call<CartItemsResponse> cartItems(
            @Header("Authorization") String token
    );


    @POST("updateQuantity")
    Call<AddCartQuantityResponse> addtcartquantity(
            @Header("Authorization") String token,
            @Query("cart_id") String cart_id,
            @Query("quantity") String quantity
    );



    @POST("addToWishList")
    Call<AddToWishlistResponse> addtowistlist(
            @Header("Authorization") String token,
            @Query("product_id") String product_id
    );

    @POST("get_wishlist_products")
    Call<GetWishlistProducts> getWishlist(
            @Header("Authorization") String token
    );


    @POST("save_order")
    Call<OrderPlacedResponse> orderplaced(
            @Header("Authorization") String token,
            @Query("product_id") String product_id,
            @Query("transaction_id") String transaction_id,
            @Query("total_amount") String total_amount
    );

    @POST("removeFromCart")
    Call<RemoveCartItemResponse> removecart(
            @Header("Authorization") String token,
            @Query("cart_id") String cart_id
    );

    @POST("getOrdersList")
    Call<GetOrderListResponse> orderlist(
            @Header("Authorization") String token
    );

    @FormUrlEncoded
    @POST("getOrderDetails")
    Call<GetBookOrderDetail> getbookorderdata(
            @Header("Authorization") String token,
            @Field("order_id") String order_id
    );

}
