package com.example.kunalparte.citycashtask.Products.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kunalparte.citycashtask.GlideLoader;
import com.example.kunalparte.citycashtask.Products.interfaces.OnRecyclerItemClickListener;
import com.example.kunalparte.citycashtask.Products.models.Products;
import com.example.kunalparte.citycashtask.R;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductVh> {

    private Activity activity;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    private List<Products> productsList;

    public ProductListAdapter(Activity activity, OnRecyclerItemClickListener onRecyclerItemClickListener, List<Products> products){
        this.activity = activity;
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
        this.productsList = products;
    }

    @Override
    public ProductVh onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.product_list_item_layout, parent,false);
        return new ProductVh(view);
    }

    @Override
    public void onBindViewHolder(ProductVh holder, int position) {
        holder.pos = position;
        holder.productName.setText(productsList.get(position).getName());
        holder.productCatName.setText(productsList.get(position).getName());
        holder.productQty.setText("QTY:"+" "+productsList.get(position).getQty());
        holder.productPrice.setText(productsList.get(position).getPrice());
        GlideLoader.url(activity).load(productsList.get(position).getImage()).into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public void setFilteredList(List<Products> products) {
        productsList = new ArrayList<>();
        productsList.addAll(products);
    }

    public class ProductVh extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView productImage;
        private TextView productName;
        private TextView productQty;
        private TextView productPrice;
        private TextView productCatName;
        int pos = 0;
        public ProductVh(View itemView) {
            super(itemView);
            productImage = (ImageView) itemView.findViewById(R.id.productIamge);
            productName = (TextView) itemView.findViewById(R.id.productNameTV);
            productCatName = (TextView) itemView.findViewById(R.id.productCatTV);
            productQty = (TextView) itemView.findViewById(R.id.quantityTV);
            productPrice = (TextView) itemView.findViewById(R.id.priceTV);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onRecyclerItemClickListener.onRecyclerItemClicked(pos);
        }
    }
}
