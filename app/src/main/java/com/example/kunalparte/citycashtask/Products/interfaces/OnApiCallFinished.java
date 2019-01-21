package com.example.kunalparte.citycashtask.Products.interfaces;

import com.example.kunalparte.citycashtask.Products.models.ProductList;

public interface OnApiCallFinished {

    void onApiCallResponseSuccess(ProductList productList);
    void onApiCallResponseFailed(Throwable throwable);
}
