package com.example.kunalparte.citycashtask.Products;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Consts {

    public static final String BASE_URL = "http://52.25.13.115:7000/interview/";

    public static final String PRODUCT_LIST_API = "productList";

    public static final String PRODUCT_DETAIL_API = "productDetail";

    public static final String PRODUCT_ID_KEY = "productID";

    public static final String NAME_KEY = "name";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
