package com.example.kunalparte.citycashtask.Products.apis;

import com.example.kunalparte.citycashtask.Products.Consts;
import com.example.kunalparte.citycashtask.Products.models.ProductList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductClient {

    @GET(Consts.PRODUCT_LIST_API)
    Call<ProductList> getProductList();

    @GET(Consts.PRODUCT_DETAIL_API)
    Call<ProductList> getProductListWithDetails();

}
