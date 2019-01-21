package com.example.kunalparte.citycashtask.Products.presenter;

import com.example.kunalparte.citycashtask.Products.interfaces.DataViewPresenter;
import com.example.kunalparte.citycashtask.Products.interfaces.OnApiCallFinished;
import com.example.kunalparte.citycashtask.Products.interfaces.OnApiRequestCall;
import com.example.kunalparte.citycashtask.Products.interfaces.ViewInterface;
import com.example.kunalparte.citycashtask.Products.models.ProductList;

public class DataViewPresenterImpl implements DataViewPresenter, OnApiCallFinished {

    private OnApiRequestCall apiRequestCall;
    private ViewInterface viewInterface;

    public DataViewPresenterImpl(OnApiRequestCall onApiRequestCall, ViewInterface viewInterface){
        this.viewInterface = viewInterface;
        this.apiRequestCall = onApiRequestCall;
    }


    @Override
    public void prepareListApiCall() {
        apiRequestCall.requestListDataFromApi(this);
    }

    @Override
    public void prepareDetailApiCall() {
        apiRequestCall.requestDetailsDataFromApi(this);
    }

    @Override
    public void onApiCallResponseSuccess(ProductList productList) {
            viewInterface.setDataOnView(productList.getData());
    }

    @Override
    public void onApiCallResponseFailed(Throwable throwable) {

    }
}
