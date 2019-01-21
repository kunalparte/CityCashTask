package com.example.kunalparte.citycashtask.Products.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kunalparte.citycashtask.GlideLoader;
import com.example.kunalparte.citycashtask.Products.Consts;
import com.example.kunalparte.citycashtask.Products.apis.OnApiRequestCallImpl;
import com.example.kunalparte.citycashtask.Products.interfaces.DataViewPresenter;
import com.example.kunalparte.citycashtask.Products.interfaces.ViewInterface;
import com.example.kunalparte.citycashtask.Products.models.Products;
import com.example.kunalparte.citycashtask.Products.presenter.DataViewPresenterImpl;
import com.example.kunalparte.citycashtask.R;

import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity implements ViewInterface {

    private TextView productDetailCat;
    private TextView productDetailCatId;
    private TextView productDetailPrice;
    private TextView productDetailSize;
    private ImageView productDetailImage;

    private DataViewPresenter dataViewPresenter;
    private String productId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        init();
    }
    public void init(){
        if (!getIntent().getStringExtra(Consts.PRODUCT_ID_KEY).isEmpty()){
            productId = getIntent().getStringExtra(Consts.PRODUCT_ID_KEY);
        }
        getSupportActionBar().setTitle(getIntent().getStringExtra(Consts.NAME_KEY));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        productDetailCat = (TextView) findViewById(R.id.productDetailCat);
        productDetailCatId = (TextView) findViewById(R.id.productDetailCatId);
        productDetailPrice = (TextView) findViewById(R.id.productDetailPrice);
        productDetailSize = (TextView) findViewById(R.id.productDetailSize);
        productDetailImage = (ImageView) findViewById(R.id.productDetailImage);

        dataViewPresenter = new DataViewPresenterImpl(new OnApiRequestCallImpl(), this);
        dataViewPresenter.prepareDetailApiCall();
    }

    @Override
    public void setDataOnView(List<Products> productsList) {
        Products products = new Products();
        for (int i = 0; i < productsList.size(); i++){
            if (productsList.get(i).getId().equals(productId)){
                products = productsList.get(i);
                break;
            }
        }
        productDetailCat.setText(products.getCategory_name());
        productDetailCatId.setText(products.getCategory_id());
        productDetailPrice.setText(products.getPrice());
        productDetailSize.setText(products.getSize());
        GlideLoader.url(this).load(products.getImage()).into(productDetailImage);

    }
}
