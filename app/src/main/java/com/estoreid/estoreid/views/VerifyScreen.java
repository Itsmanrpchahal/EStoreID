package com.estoreid.estoreid.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.estoreid.estoreid.R;

public class VerifyScreen extends AppCompatActivity {

    EditText opt_1,opt_2;
    Button verify_confirm_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_screen);

        init();
        listeners();
    }

    private void listeners() {

        verify_confirm_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerifyScreen.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        opt_1 =(EditText) findViewById(R.id.opt_1);
        opt_2 = (EditText) findViewById(R.id.opt_2);
        verify_confirm_bt = (Button)findViewById(R.id.verify_confirm_bt);
    }
}
