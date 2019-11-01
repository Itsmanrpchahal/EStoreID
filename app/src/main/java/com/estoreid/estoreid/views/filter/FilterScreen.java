package com.estoreid.estoreid.views.filter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.adapter.BrandAdapter;
import com.estoreid.estoreid.views.adapter.Categories_Adapter;
import com.estoreid.estoreid.views.adapter.ColorsAdapter;
import com.jaygoo.widget.RangeSeekBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterScreen extends AppCompatActivity {

    BrandAdapter brandAdapter;
    Categories_Adapter categories_adapter;
    ColorsAdapter colorsAdapter;
    @BindView(R.id.close_filter)
    ImageButton closeFilter;
    @BindView(R.id.layout1)
    RelativeLayout layout1;
    @BindView(R.id.brandtext)
    TextView brandtext;
    @BindView(R.id.brandrecycler)
    RecyclerView brandrecycler;
    ArrayList<String> arrayList = new ArrayList<>();
    @BindView(R.id.categories)
    TextView categories;
    @BindView(R.id.categoriesrecycler)
    RecyclerView categoriesrecycler;
    @BindView(R.id.colors)
    TextView colors;
    @BindView(R.id.colorsrecycler)
    RecyclerView colorsrecycler;
    @BindView(R.id.pricerange)
    TextView pricerange;
    @BindView(R.id.rangeseekbar)
    RangeSeekBar rangeseekbar;
    @BindView(R.id.filter_startprice)
    TextView filterStartprice;
    @BindView(R.id.filter_endprice)
    TextView filterEndprice;
    @BindView(R.id.applyfilterbt)
    Button applyfilterbt;
    @BindView(R.id.clearfilterbt)
    Button clearfilterbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_screen);
        ButterKnife.bind(this);

        arrayList.add("Hello");
        arrayList.add("Manpreet");
        arrayList.add("How are you");
        arrayList.add("Hows your");
        arrayList.add("Work");
        arrayList.add("Going on");
        arrayList.add("Manpreet Singh");
        arrayList.add("Manpreet Singh");
        arrayList.add("Chahal Manpreet");

        setBrandAdapter();
        setCategoriesAdpater(arrayList);
        setColorsAdapter();
        listerners();
    }

    private void listerners() {
        closeFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @SuppressLint("WrongConstant")
    private void setColorsAdapter() {

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        colorsrecycler.setHasFixedSize(true);
        colorsrecycler.setLayoutManager(linearLayout);
        colorsAdapter = new ColorsAdapter(this);
        colorsrecycler.setAdapter(colorsAdapter);
        colorsAdapter.notifyDataSetChanged();
    }

    private void setCategoriesAdpater(ArrayList<String> arrayList) {

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        categoriesrecycler.setLayoutManager(gridLayoutManager);
        categories_adapter = new Categories_Adapter(this, arrayList);
        categoriesrecycler.setAdapter(categories_adapter);
    }

    private void setBrandAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        brandrecycler.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        brandAdapter = new BrandAdapter(this);
        brandrecycler.setAdapter(brandAdapter);
        brandAdapter.notifyDataSetChanged();
    }
}
