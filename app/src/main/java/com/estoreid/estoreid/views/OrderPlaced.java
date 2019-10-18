package com.estoreid.estoreid.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.estoreid.estoreid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderPlaced extends AppCompatActivity {

    @BindView(R.id.close_order_placed)
    ImageButton closeOrderPlaced;
    @BindView(R.id.order_placed_toolbar)
    Toolbar orderPlacedToolbar;
    @BindView(R.id.order_placed_image)
    ImageView orderPlacedImage;
    @BindView(R.id.orderplace_text)
    TextView orderplaceText;
    @BindView(R.id.orderplace_decstext)
    TextView orderplaceDecstext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);
        ButterKnife.bind(this);

        listerners();
    }

    private void listerners() {
        closeOrderPlaced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
