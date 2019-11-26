package com.estoreid.estoreid.views.filter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.estoreid.estoreid.R;
import com.estoreid.estoreid.views.BaseActivity;
import com.estoreid.estoreid.views.adapter.BrandAdapter;
import com.estoreid.estoreid.views.adapter.Categories_Adapter;
import com.estoreid.estoreid.views.adapter.ColorsAdapter;
import com.estoreid.estoreid.views.apiResponseModel.FilterDataResponse;
import com.estoreid.estoreid.views.controller.Controller;
import com.estoreid.estoreid.views.utils.Constants;
import com.estoreid.estoreid.views.utils.Utils;
import com.jaygoo.widget.RangeSeekBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class FilterScreen extends BaseActivity implements Controller.FilterScreen {

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
    Controller controller;
    ArrayList<FilterDataResponse.Datum> filterdata = new ArrayList<>();
    ArrayList<FilterDataResponse.Datum.Brand> brands = new ArrayList<>();
    ArrayList<FilterDataResponse.Datum.Category> categoryArrayList = new ArrayList<>();
    ArrayList<FilterDataResponse.Datum.Color> colorArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_screen);
        ButterKnife.bind(this);
        controller = new Controller(this);

        controller.FilterScreen("Bearer "+getStringVal(Constants.TOKEN));

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
    private void setColorsAdapter(ArrayList<FilterDataResponse.Datum.Color> colorArrayList) {

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        colorsrecycler.setHasFixedSize(true);
        colorsrecycler.setLayoutManager(linearLayout);
        colorsAdapter = new ColorsAdapter(this,colorArrayList);
        colorsrecycler.setAdapter(colorsAdapter);
        colorsAdapter.notifyDataSetChanged();
    }

    private void setCategoriesAdpater(ArrayList<FilterDataResponse.Datum.Category> categories) {

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        categoriesrecycler.setLayoutManager(gridLayoutManager);
        categories_adapter = new Categories_Adapter(this, categories);
        categoriesrecycler.setAdapter(categories_adapter);
    }

    private void setBrandAdapter(ArrayList<FilterDataResponse.Datum.Brand> brands) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        brandrecycler.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        brandAdapter = new BrandAdapter(this,brands);
        brandrecycler.setAdapter(brandAdapter);
        brandAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSucessFilter(Response<FilterDataResponse> filterDataResponseResponse) {
        brands.clear();
        categoryArrayList.clear();
        if (filterDataResponseResponse.body().getStatus() == 200) {

            for (int i=0;i<filterDataResponseResponse.body().getData().get(0).getBrands().size();i++)
            {
                FilterDataResponse.Datum.Brand brand = filterDataResponseResponse.body().getData().get(0).getBrands().get(i);
                brands.add(brand);
                setBrandAdapter(brands);
            }

            for (int i=0;i<filterDataResponseResponse.body().getData().get(0).getCategories().size();i++)
            {
                FilterDataResponse.Datum.Category category = filterDataResponseResponse.body().getData().get(0).getCategories().get(i);
                categoryArrayList.add(category);
                setCategoriesAdpater(categoryArrayList);
            }

            for (int i=0;i<filterDataResponseResponse.body().getData().get(0).getColors().size();i++)
            {
                FilterDataResponse.Datum.Color color = filterDataResponseResponse.body().getData().get(0).getColors().get(i);
                colorArrayList.add(color);
                setColorsAdapter(colorArrayList);
            }


        }else {
            Utils.showToastMessage(FilterScreen.this, filterDataResponseResponse.body().getMessage(), getResources().getDrawable(R.drawable.ic_error_black_24dp));
        }
    }

    @Override
    public void onError(String error) {
        Utils.showToastMessage(FilterScreen.this, error, getResources().getDrawable(R.drawable.ic_error_black_24dp));
    }
}
