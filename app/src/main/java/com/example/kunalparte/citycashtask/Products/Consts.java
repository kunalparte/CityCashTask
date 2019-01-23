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

    public static final String A_ORDER = "A - low - high";

    public static final String A_REV = "A - high - low";

    public static final String B_ORDER = "B - low - high";

    public static final String B_REV = "B - high - low";

    public static final String C_ORDER = "C - low - high";

    public static final String C_REV = "C - high - low";

    public static final String NO_FILTER = "Remove filter";

    public static final String FILTER_KEY = "filterKey";

    public static final String RADIO_ID = "radioId";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
