package com.example.kunalparte.citycashtask.Products.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kunalparte.citycashtask.DrawerFragment;
import com.example.kunalparte.citycashtask.Products.Consts;
import com.example.kunalparte.citycashtask.Products.adapters.ProductListAdapter;
import com.example.kunalparte.citycashtask.Products.apis.OnApiRequestCallImpl;
import com.example.kunalparte.citycashtask.Products.interfaces.DataViewPresenter;
import com.example.kunalparte.citycashtask.Products.interfaces.OnRecyclerItemClickListener;
import com.example.kunalparte.citycashtask.Products.interfaces.ViewInterface;
import com.example.kunalparte.citycashtask.Products.models.Products;
import com.example.kunalparte.citycashtask.Products.presenter.DataViewPresenterImpl;
import com.example.kunalparte.citycashtask.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DashBoardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ViewInterface, OnRecyclerItemClickListener, SwipeRefreshLayout.OnRefreshListener, TextWatcher, View.OnClickListener {

    private RecyclerView dataRecycler;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DataViewPresenter dataViewPresenter;
    private ProductListAdapter productListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Products> products;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private FrameLayout frameLayout;
    private EditText editText;
    private ImageButton filterButton;
    private InputMethodManager inputMethodManager;
    private List<Products>productsListfiltered;
    private List<Products> productOriginalStateList;
    private boolean isFiltere = false;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        frameLayout.setVisibility(View.VISIBLE);
        int id = item.getItemId();

        if (inputMethodManager.isActive())
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),0);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (id){
            case R.id.nav_account:
                toolbar.setTitle("My Account");
                fragmentTransaction.replace(R.id.frameLayout,DrawerFragment.newInstance()).commit();
                break;
            case R.id.nav_offers:
                toolbar.setTitle("Offers");
                fragmentTransaction.replace(R.id.frameLayout,DrawerFragment.newInstance()).commit();
                break;
            case R.id.nav_my_orders:
                toolbar.setTitle("My Orders");
                fragmentTransaction.replace(R.id.frameLayout,DrawerFragment.newInstance()).commit();
                break;
            case R.id.nav_logout:
                toolbar.setTitle("Logout");
                fragmentTransaction.replace(R.id.frameLayout,DrawerFragment.newInstance()).commit();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void init(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("DashBoard");
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        dataRecycler = (RecyclerView) findViewById(R.id.dataList);
        filterButton = (ImageButton) findViewById(R.id.filterButton);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLay);
        editText = (EditText) findViewById(R.id.searchEditText);
        dataViewPresenter = new DataViewPresenterImpl(new OnApiRequestCallImpl(),this);
        linearLayoutManager = new LinearLayoutManager(this);
        products = new ArrayList<>();
        productOriginalStateList = new ArrayList<>();
        swipeRefreshLayout.setOnRefreshListener(this);
        editText.addTextChangedListener( this);
        filterButton.setOnClickListener(this);
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (Consts.isNetworkAvailable(this)){
            dataViewPresenter.prepareListApiCall();
        }else {
            Toast.makeText(this, "Connect to internet and refresh", Toast.LENGTH_SHORT).show();
        }
    }

    public void setDataOnRecycler(List<Products> productsList) {
        productListAdapter = new ProductListAdapter(this, this, productsList);
        dataRecycler.setAdapter(productListAdapter);
        productListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRecyclerItemClicked(int position) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra(Consts.PRODUCT_ID_KEY, products.get(position).getId());
        if (!isFiltere) {
            intent.putExtra(Consts.NAME_KEY, products.get(position).getName());
        }else {
            intent.putExtra(Consts.NAME_KEY, productsListfiltered.get(position).getName());
        }
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    @Override
    public void setDataOnView(List<Products> productsList) {
        if (swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
        //products = new ArrayList<>();
        if (productOriginalStateList.size() > 0){
            productOriginalStateList.clear();
        }
        productOriginalStateList.addAll(productsList);
        products.clear();
        products.addAll(productsList);
        dataRecycler.setLayoutManager(linearLayoutManager);
        setDataOnRecycler(products);
//        Collections.sort(products, Products.FILTER_A);
//        int size = products.size();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (frameLayout.getVisibility() == View.VISIBLE){
            toolbar.setTitle("DashBoard");
            frameLayout.setVisibility(View.GONE);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRefresh() {
        if (Consts.isNetworkAvailable(this)) {
            if (products.size() > 0)
            dataViewPresenter.prepareListApiCall();
        }else {
            Toast.makeText(this, "Connect to internet and refresh", Toast.LENGTH_SHORT).show();
            if (swipeRefreshLayout.isRefreshing())
                swipeRefreshLayout.setRefreshing(false);
        }
        if (inputMethodManager.isActive())
            inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),0);
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 2){
            isFiltere = true;
            productsListfiltered = new ArrayList<>();
            for (Products product : products){
                String searchWord = s.toString();
                if (product.getName().toLowerCase().contains(searchWord)){
                    productsListfiltered.add(product);
                }
            }
            productListAdapter.setFilteredList(productsListfiltered);
            productListAdapter.notifyDataSetChanged();
        }else {
            isFiltere = false;
            productListAdapter.setFilteredList(products);
            productListAdapter.notifyDataSetChanged();

        }
    }

    public List<Products> returnReveredList(List<Products> productsList){
        List<Products> productsList1 = new ArrayList<>();
        for (int i = productsList.size()-1; i >= 0; i--){
            productsList1.add(productsList.get(i));
        }
        return productsList1;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);
        if (requestCode == 1){
            if (resultCode == 2){
                String filter = intent.getStringExtra(Consts.FILTER_KEY);
                id = intent.getIntExtra(Consts.RADIO_ID,0);
                if (filter != null && !filter.isEmpty()){
                    switch (filter){
                        case Consts.A_ORDER:
                            Collections.sort(products, Products.FILTER_A_ORDER);
                            productListAdapter.setFilteredList(productsListfiltered);
                            break;
                        case Consts.A_REV:
                            Collections.sort(products, Products.FILTER_A_ORDER);
                            productListAdapter.setFilteredList(returnReveredList(products));
                            break;
                        case Consts.B_ORDER:
                            Collections.sort(products, Products.FILTER_B_ORDER);
                            productListAdapter.setFilteredList(products);
                            break;
                        case Consts.B_REV:
                            Collections.sort(products, Products.FILTER_B_ORDER);
                            productListAdapter.setFilteredList(returnReveredList(products));
                            break;
                        case Consts.C_ORDER:
                            Collections.sort(products, Products.FILTER_C_ORDER);
                            productListAdapter.setFilteredList(products);
                            break;
                        case Consts.C_REV:
                            Collections.sort(products, Products.FILTER_C_ORDER);
                            productListAdapter.setFilteredList(returnReveredList(products));
                            break;
                        case Consts.NO_FILTER:
                            products.clear();
                            products.addAll(productOriginalStateList);
                            productListAdapter.setFilteredList(products);
                            break;

                    }
                    productListAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.filterButton:
                Intent intent = new Intent(this,FilterActivity.class);
                if (id != 0){
                    intent.putExtra(Consts.RADIO_ID,id);
                }
                startActivityForResult(intent,1);
                break;
        }
    }
}
