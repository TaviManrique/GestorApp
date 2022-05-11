package com.manriquetavi.gestorapp;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemProductAdapter extends RecyclerView.Adapter<ItemProductAdapter.ViewHolder>{

    private final Context mContext;
    private final ArrayList product_ids;
    private final ArrayList product_names;
    private final ArrayList product_prices;
    private final ArrayList product_wholesale_prices;
    private final ArrayList product_stocks;

    ItemProductAdapter(
            Context context,
            ArrayList product_ids,
            ArrayList product_names,
            ArrayList product_prices,
            ArrayList product_wholesale_prices,
            ArrayList product_stocks
    ) {
        this.mContext = context;
        this.product_ids = product_ids;
        this.product_names = product_names;
        this.product_prices = product_prices;
        this.product_wholesale_prices = product_wholesale_prices;
        this.product_stocks = product_stocks;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNameProduct.setText(String.valueOf(product_names.get(position)));
        holder.etPriceProduct.setText(String.valueOf(product_prices.get(position)));
        holder.etWholesalePriceProduct.setText(String.valueOf(product_wholesale_prices.get(position)));
        holder.etStock.setText(String.valueOf(product_stocks.get(position)));
    }

    @Override
    public int getItemCount() {
        return product_ids.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvNameProduct;
        private final EditText etPriceProduct;
        private final EditText etWholesalePriceProduct;
        private final EditText etStock;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
            etPriceProduct = itemView.findViewById(R.id.etPriceProduct);
            etWholesalePriceProduct = itemView.findViewById(R.id.etWholesalePriceProduct);
            etStock = itemView.findViewById(R.id.etStock);

        }
    }
}
