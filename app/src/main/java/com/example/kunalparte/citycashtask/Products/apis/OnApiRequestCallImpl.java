package com.example.kunalparte.citycashtask.Products.apis;


import com.example.kunalparte.citycashtask.Products.interfaces.OnApiCallFinished;
import com.example.kunalparte.citycashtask.Products.interfaces.OnApiRequestCall;
import com.example.kunalparte.citycashtask.Products.models.ProductList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnApiRequestCallImpl implements OnApiRequestCall {

    public OnApiRequestCallImpl(){

    }

    @Override
    public void requestListDataFromApi(final OnApiCallFinished onApiCallFinished) {
        Call<ProductList> productListCall = ServiceGenerater.createProductApiService(ProductClient.class).getProductList();
        productListCall.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                onApiCallFinished.onApiCallResponseSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                onApiCallFinished.onApiCallResponseFailed(t);
            }
        });
    }

    @Override
    public void requestDetailsDataFromApi(final OnApiCallFinished onApiCallFinished) {
        Call<ProductList> productListCall = ServiceGenerater.createProductApiService(ProductClient.class).getProductListWithDetails();
        productListCall.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                onApiCallFinished.onApiCallResponseSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                onApiCallFinished.onApiCallResponseFailed(t);
            }
        });
    }
}
