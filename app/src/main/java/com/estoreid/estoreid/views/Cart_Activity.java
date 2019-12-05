package com.estoreid.estoreid.views;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.AddCartQuantity;
import com.estoreid.estoreid.views.adapter.AsyncTask;
import com.estoreid.estoreid.views.adapter.CartAddedItemAdapter;
import com.estoreid.estoreid.views.adapter.ReletedProductAdpater;
import com.estoreid.estoreid.views.apiResponseModel.AddCartQuantityResponse;
import com.estoreid.estoreid.views.apiResponseModel.CartItemsResponse;
import com.estoreid.estoreid.views.apiResponseModel.count;
import com.estoreid.estoreid.views.controller.Controller;
import com.estoreid.estoreid.views.utils.Constants;
import com.estoreid.estoreid.views.utils.Utils;
import com.estoreid.estoreid.views.webApi.WebAPI;
import com.payumoney.core.PayUmoneySdkInitializer;
import com.payumoney.core.entity.TransactionResponse;
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart_Activity extends BaseActivity  implements Controller.CartItems,Controller.AddCartItemQuantity {


    PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();
    PayUmoneySdkInitializer.PaymentParam paymentParam = null;
    String txnID = "txt12346",amount="20",phone="8427180202",productname="Nike",firstname="Manpreet",email="chahalmanpreet0305@gmail.com";
    ReletedProductAdpater reletedProductAdpater;
    CartAddedItemAdapter adapter;
    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.backontoolbar)
    ImageButton backontoolbar;
    @BindView(R.id.cart_toolbar)
    ImageButton cartToolbar;
    @BindView(R.id.serach_toolbar)
    ImageButton serachToolbar;
    @BindView(R.id.toolbartitle)
    TextView toolbartitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.product_serach)
    ImageButton productSerach;
    @BindView(R.id.toolbar_layout)
    RelativeLayout toolbarLayout;
    @BindView(R.id.carttext)
    TextView carttext;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.cart_itemscount)
    TextView cartItemscount;
    @BindView(R.id.additem_recyclerviw)
    RecyclerView additemRecyclerviw;
    @BindView(R.id.sub_total_tv)
    TextView subTotalTv;
    @BindView(R.id.sub_total_tv1)
    TextView subTotalTv1;
    @BindView(R.id.tax_tv)
    TextView taxTv;
    @BindView(R.id.tax_tv1)
    TextView taxTv1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.total_tv)
    TextView totalTv;
    @BindView(R.id.total_tv1)
    TextView totalTv1;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.placeorder)
    Button placeorder;
    @BindView(R.id.relatedproduct_tv)
    TextView relatedproductTv;
    @BindView(R.id.related_item_recyclerview)
    RecyclerView relatedItemRecyclerview;
    @BindView(R.id.cardview)
    CardView cardview;
    @BindView(R.id.scrollview)
    ScrollView scrollview;
    @BindView(R.id.safetext)
    TextView safetext;
    ArrayList<CartItemsResponse.Datum> cartitems = new ArrayList<>();
    static Integer counter = 0;
    Controller controller;
    Dialog Dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_cart_, null, false);
        drawer.addView(contentView, 0);
        ButterKnife.bind(this);
        Dialog = Utils.showDialog(this);
        Dialog.show();
        additemRecyclerviw.setFocusable(false);
        controller = new Controller((Controller.CartItems)this,(Controller.AddCartItemQuantity)this);
        controller.CartItems("Bearer "+getStringVal(Constants.TOKEN));

        listeners();
    }

    private void listeners() {
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart_Activity.this, CheckOutActivity.class);
                startActivity(intent);
            }
        });

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startpay();
            }
        });

    }


    @SuppressLint("WrongConstant")
    private void setAdapter(ArrayList<CartItemsResponse.Datum> cartitems) {

        //Cart Addet items
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        additemRecyclerviw.setHasFixedSize(true);
        additemRecyclerviw.setLayoutManager(linearLayout);
        adapter = new CartAddedItemAdapter(this,cartitems);
        additemRecyclerviw.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.CartAddedItemAdapter(new AddCartQuantity() {
            @Override
            public void onSuccess(String cart_id, String quantity) {
                controller.AddCartItemQuantity("Bearer "+getStringVal(Constants.TOKEN),cart_id,quantity);
            }
        });

        //Releted Items
        LinearLayoutManager linearLayout1 = new LinearLayoutManager(this);
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        relatedItemRecyclerview.setHasFixedSize(true);
        relatedItemRecyclerview.setLayoutManager(linearLayout1);
        reletedProductAdpater = new ReletedProductAdpater(this);
        relatedItemRecyclerview.setAdapter(reletedProductAdpater);
        reletedProductAdpater.notifyDataSetChanged();
    }

    @Override
    public void onSuccessCartItems(Response<CartItemsResponse> cartItemsResponseResponse) {
            Dialog.dismiss();
        if (cartItemsResponseResponse.body().getStatus()==200)
        {
            for (int i=0;i<cartItemsResponseResponse.body().getData().size();i++)
            {
                CartItemsResponse.Datum datum = cartItemsResponseResponse.body().getData().get(i);
                cartitems.add(datum);
                setAdapter(cartitems);
            }
        }else {
            Utils.showToastMessage(this,cartItemsResponseResponse.body().getMessage(),getResources().getDrawable(R.drawable.ic_error_black_24dp));
        }
    }

    @Override
    public void onSucessAddCartQuantity(Response<AddCartQuantityResponse> addCartItemQuantityResponse) {
        Dialog.dismiss();
        if (addCartItemQuantityResponse.body().getStatus()==200)
        {

        }
    }

    @Override
    public void onError(String error) {
        Dialog.dismiss();
        Utils.showToastMessage(this,error,getResources().getDrawable(R.drawable.ic_error_black_24dp));
    }


    public void startpay(){

        builder.setAmount(amount)                          // Payment amount
                .setTxnId(txnID)                     // Transaction ID
                .setPhone(phone)                   // User Phone number
                .setProductName(productname)                   // Product Name or description
                .setFirstName(firstname)                              // User First name
                .setEmail(email)              // User Email ID
                .setsUrl("https://www.payumoney.com/mobileapp/payumoney/success.php")     // Success URL (surl)
                .setfUrl("https://www.payumoney.com/mobileapp/payumoney/failure.php")     //Failure URL (furl)
                .setUdf1("")
                .setUdf2("")
                .setUdf3("")
                .setUdf4("")
                .setUdf5("")
                .setUdf6("")
                .setUdf7("")
                .setUdf8("")
                .setUdf9("")
                .setUdf10("")
                .setIsDebug(true)                              // Integration environment - true (Debug)/ false(Production)
                .setKey(Constants.MERCHANTKEY)                        // Merchant key
                .setMerchantId(Constants.MERCHANTID);


        try {
            paymentParam = builder.build();
            // generateHashFromServer(paymentParam );
            getHashkey();

        } catch (Exception e) {
            Log.e("+++", " error s "+e.toString());
        }

    }

    public void getHashkey(){
        Call<String> call = WebAPI.getInstance().getApi().getHashCall(Constants.MERCHANTKEY, txnID, amount, productname,
                firstname, email);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("++++", "hash res "+response.body());
                String merchantHash= response.body();
                if (merchantHash.isEmpty() || merchantHash.equals("")) {
                    Toast.makeText(Cart_Activity.this, "Could not generate hash", Toast.LENGTH_SHORT).show();
                    Log.e("++++++", "hash empty");
                } else {
                    // mPaymentParams.setMerchantHash(merchantHash);
                    paymentParam.setMerchantHash(merchantHash);
                    // Invoke the following function to open the checkout page.
                    // PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam, StartPaymentActivity.this,-1, true);
                    PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam, Cart_Activity.this, R.style.AppTheme_default, false);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("+++++", "hash error "+ t.toString());
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
// PayUMoneySdk: Success -- payuResponse{"id":225642,"mode":"CC","status":"success","unmappedstatus":"captured","key":"9yrcMzso","txnid":"223013","transaction_fee":"20.00","amount":"20.00","cardCategory":"domestic","discount":"0.00","addedon":"2018-12-31 09:09:43","productinfo":"a2z shop","firstname":"kamal","email":"kamal.bunkar07@gmail.com","phone":"9144040888","hash":"b22172fcc0ab6dbc0a52925ebbd0297cca6793328a8dd1e61ef510b9545d9c851600fdbdc985960f803412c49e4faa56968b3e70c67fe62eaed7cecacdfdb5b3","field1":"562178","field2":"823386","field3":"2061","field4":"MC","field5":"167227964249","field6":"00","field7":"0","field8":"3DS","field9":" Verification of Secure Hash Failed: E700 -- Approved -- Transaction Successful -- Unable to be determined--E000","payment_source":"payu","PG_TYPE":"AXISPG","bank_ref_no":"562178","ibibo_code":"VISA","error_code":"E000","Error_Message":"No Error","name_on_card":"payu","card_no":"401200XXXXXX1112","is_seamless":1,"surl":"https://www.payumoney.com/sandbox/payment/postBackParam.do","furl":"https://www.payumoney.com/sandbox/payment/postBackParam.do"}
//PayUMoneySdk: Success -- merchantResponse438104
// on successfull txn
        //  request code 10000 resultcode -1
        //tran {"status":0,"message":"payment status for :438104","result":{"postBackParamId":292490,"mihpayid":"225642","paymentId":438104,"mode":"CC","status":"success","unmappedstatus":"captured","key":"9yrcMzso","txnid":"txt12345","amount":"20.00","additionalCharges":"","addedon":"2018-12-31 09:09:43","createdOn":1546227592000,"productinfo":"a2z shop","firstname":"kamal","lastname":"","address1":"","address2":"","city":"","state":"","country":"","zipcode":"","email":"kamal.bunkar07@gmail.com","phone":"9144040888","udf1":"","udf2":"","udf3":"","udf4":"","udf5":"","udf6":"","udf7":"","udf8":"","udf9":"","udf10":"","hash":"0e285d3a1166a1c51b72670ecfc8569645b133611988ad0b9c03df4bf73e6adcca799a3844cd279e934fed7325abc6c7b45b9c57bb15047eb9607fff41b5960e","field1":"562178","field2":"823386","field3":"2061","field4":"MC","field5":"167227964249","field6":"00","field7":"0","field8":"3DS","field9":" Verification of Secure Hash Failed: E700 -- Approved -- Transaction Successful -- Unable to be determined--E000","bank_ref_num":"562178","bankcode":"VISA","error":"E000","error_Message":"No Error","cardToken":"","offer_key":"","offer_type":"","offer_availed":"","pg_ref_no":"","offer_failure_reason":"","name_on_card":"payu","cardnum":"401200XXXXXX1112","cardhash":"This field is no longer supported in postback params.","card_type":"","card_merchant_param":null,"version":"","postUrl":"https:\/\/www.payumoney.com\/mobileapp\/payumoney\/success.php","calledStatus":false,"additional_param":"","amount_split":"{\"PAYU\":\"20.0\"}","discount":"0.00","net_amount_debit":"20","fetchAPI":null,"paisa_mecode":"","meCode":"{\"vpc_Merchant\":\"TESTIBIBOWEB\"}","payuMoneyId":"438104","encryptedPaymentId":null,"id":null,"surl":null,"furl":null,"baseUrl":null,"retryCount":0,"merchantid":null,"payment_source":null,"pg_TYPE":"AXISPG"},"errorCode":null,"responseCode":null}---438104

        // Result Code is -1 send from Payumoney activity
        Log.e("StartPaymentActivity", "request code " + requestCode + " resultcode " + resultCode);
        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data != null) {
            TransactionResponse transactionResponse = data.getParcelableExtra( PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE );

            if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {

                if(transactionResponse.getTransactionStatus().equals( TransactionResponse.TransactionStatus.SUCCESSFUL )){
                    //Success Transaction
                } else{
                    //Failure Transaction
                }

                // Response from Payumoney
                String payuResponse = transactionResponse.getPayuResponse();

                // Response from SURl and FURL
                String merchantResponse = transactionResponse.getTransactionDetails();
                Log.e("+++++++", "tran "+payuResponse+"---"+ merchantResponse);
            } /* else if (resultModel != null && resultModel.getError() != null) {
                Log.d(TAG, "Error response : " + resultModel.getError().getTransactionResponse());
            } else {
                Log.d(TAG, "Both objects are null!");
            }*/
        }
    }
}
