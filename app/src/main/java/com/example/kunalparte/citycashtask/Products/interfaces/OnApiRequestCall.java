package com.example.kunalparte.citycashtask.Products.interfaces;

public interface OnApiRequestCall {

    void requestListDataFromApi(OnApiCallFinished onApiCallFinished);
    void requestDetailsDataFromApi(OnApiCallFinished onApiCallFinished);
}
