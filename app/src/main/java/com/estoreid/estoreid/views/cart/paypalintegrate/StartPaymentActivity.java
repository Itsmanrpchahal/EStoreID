package com.estoreid.estoreid.views.cart.paypalintegrate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.BaseActivity;
import com.estoreid.estoreid.views.MainActivity;
import com.estoreid.estoreid.views.OrderPlaced;
import com.estoreid.estoreid.views.apiResponseModel.OrderPlacedResponse;
import com.estoreid.estoreid.views.cart.Cart_Activity;
import com.estoreid.estoreid.views.controller.Controller;
import com.estoreid.estoreid.views.utils.Constants;
import com.estoreid.estoreid.views.utils.Utils;
import com.payumoney.core.PayUmoneySdkInitializer;
import com.payumoney.core.entity.TransactionResponse;
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartPaymentActivity extends BaseActivity implements Controller.OrderPlaced {

    PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();
    PayUmoneySdkInitializer.PaymentParam paymentParam = null;
    String txnID = "4012", amount = "200", phone = "8427180202", productname = "Nike", firstname = "Manpreet", email = "abhitron01@gmail.com";
    Controller controller;
    android.app.Dialog Dialog;
    String user_id, total_amount, token,user_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_payment);
        user_id = getStringVal(Constants.USER_ID);
        token = getStringVal(Constants.TOKEN);
        user_number = getStringVal(Constants.USER_NUMBER);
        total_amount = getStringVal(Constants.TOTALAMOUNT);
        Dialog = Utils.showDialog(this);

        controller = new Controller(this);
        startpay();
    }

    public void startpay() {

        builder.setAmount(total_amount)                          // Payment amount
                .setTxnId(txnID)                     // Transaction ID
                .setPhone(user_number)                   // User Phone number
                .setProductName(productname)                   // Product Name or description
                .setFirstName(getStringVal(Constants.USER_NAME))                              // User First name
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
            Log.e("++++++++", " error s " + e.toString());
        }

    }

    public void getHashkey() {
        ServiceWrapper service = new ServiceWrapper(null);
        Call<String> call = service.newHashCall(Constants.MERCHANTKEY, txnID, total_amount, productname,
                getStringVal(Constants.USER_NAME), email);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.e("++++", "hash res " + response.body());
                String merchantHash = response.body();
                if (merchantHash.isEmpty() || merchantHash.equals("")) {
                    Toast.makeText(StartPaymentActivity.this, "Could not generate hash", Toast.LENGTH_SHORT).show();
                    Log.e("++++", "hash empty");
                } else {
                    // mPaymentParams.setMerchantHash(merchantHash);
                    paymentParam.setMerchantHash(merchantHash);
                    // Invoke the following function to open the checkout page.
                    // PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam, StartPaymentActivity.this,-1, true);
                    PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam, StartPaymentActivity.this, R.style.AppTheme_default, false);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Dialog.dismiss();
                Log.e("+++++", "hash error " + t.toString());
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
            TransactionResponse transactionResponse = data.getParcelableExtra(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE);
            if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {

                if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.SUCCESSFUL)) {

                    //Success Transaction
                    if (Utils.isOnline() != false) {
                        Dialog.show();
                        controller.OrderPlaced("Bearer "+token, "", transactionResponse.getTransactionDetails(), total_amount);

                    } else {
                        Dialog.dismiss();
                        Utils.showToastMessage(StartPaymentActivity.this, "No Internet Connection", getResources().getDrawable(R.drawable.ic_nointernet));
                    }
                } else {
                    Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();
                    //Failure Transaction
                }

                // Response from Payumoney
                String payuResponse = transactionResponse.getPayuResponse();

                // Response from SURl and FURL
                String merchantResponse = transactionResponse.getTransactionDetails();
                Log.e("++++++", "tran " + payuResponse + "---" + merchantResponse);
            }else {
                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
            } /* else if (resultModel != null && resultModel.getError() != null) {
                Log.d(TAG, "Error response : " + resultModel.getError().getTransactionResponse());
            } else {
                Log.d(TAG, "Both objects are null!");
            }*/
        }
    }

    @Override
    public void onSuccessOrderPlaced(Response<OrderPlacedResponse> orderPlacedResponseResponse) {
        Dialog.dismiss();
        if (orderPlacedResponseResponse.body().getStatus() == 200) {
            Intent intent = new Intent(StartPaymentActivity.this, OrderPlaced.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onError(String error) {
        Dialog.dismiss();
        Utils.showToastMessage(StartPaymentActivity.this,error,getResources().getDrawable(R.drawable.ic_error_black_24dp));
    }
}
