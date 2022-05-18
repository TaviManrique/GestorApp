package com.manriquetavi.gestorapp.report;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.manriquetavi.gestorapp.R;

import java.util.ArrayList;

public class ItemProductAdapter extends RecyclerView.Adapter<ItemProductAdapter.ViewHolder>{

    private final Context mContext;
    private final ArrayList<Integer> product_ids;
    private final ArrayList<String> product_names;
    private final ArrayList<String> product_prices;
    private final ArrayList<String> product_wholesale_prices;
    private final ArrayList<String> product_stocks;

    boolean isOnTextChanged = false;

    ItemProductAdapter(
            Context context,
            ArrayList<Integer> product_ids,
            ArrayList<String> product_names,
            ArrayList<String> product_prices,
            ArrayList<String> product_wholesale_prices,
            ArrayList<String> product_stocks
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
        holder.etPriceProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isOnTextChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(isOnTextChanged) {
                    isOnTextChanged = false;
                    product_prices.set(holder.getAdapterPosition(), editable.toString());
                }
            }
        });

        holder.etWholesalePriceProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isOnTextChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(isOnTextChanged) {
                    isOnTextChanged = false;
                    product_wholesale_prices.set(holder.getAdapterPosition(), editable.toString());
                }
            }
        });

        holder.etStock.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isOnTextChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(isOnTextChanged) {
                    isOnTextChanged = false;
                    product_stocks.set(holder.getAdapterPosition(), editable.toString());
                }
            }
        });
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
