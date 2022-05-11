package com.manriquetavi.gestorapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemStoreAdapter extends RecyclerView.Adapter<ItemStoreAdapter.ViewHolder> {

    private final Context mContext;
    private final ArrayList store_ids;
    private final ArrayList store_names;
    private final ArrayList store_codes;
    private final ArrayList store_address;
    private final ArrayList store_latitudes;
    private final ArrayList store_longitudes;

    ItemStoreAdapter(
            Context context,
            ArrayList store_ids,
            ArrayList store_names,
            ArrayList store_codes,
            ArrayList store_address,
            ArrayList store_latitudes,
            ArrayList store_longitudes
    ) {
        this.mContext = context;
        this.store_ids = store_ids;
        this.store_names = store_names;
        this.store_codes = store_codes;
        this.store_address = store_address;
        this.store_latitudes = store_latitudes;
        this.store_longitudes = store_longitudes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_store, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String latitude = (String) store_latitudes.get(position);
        final String longitude = (String) store_longitudes.get(position);
        final String name = String.valueOf(store_names.get(position));
        final Integer code = (Integer) store_codes.get(position);
        holder.tvNameStore.setText(name);
        holder.tvCodeStore.setText(String.valueOf(code));
        holder.tvAddressStore.setText(String.valueOf(store_address.get(position)));
        holder.imgMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MapActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                mContext.startActivity(intent);
            }
        });
        holder.itemStoreLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog
                        .Builder(mContext)
                        .setTitle(name)
                        .setMessage("Atendera el pdv ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(mContext, VisitActivity.class);
                                intent.putExtra("code", code);
                                mContext.startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(mContext, "No button clicked", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return store_ids.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvNameStore;
        private final TextView tvCodeStore;
        private final TextView tvAddressStore;
        private final ImageView imgMap;
        private ImageView imgIconArrow;
        private LinearLayout itemStoreLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameStore = itemView.findViewById(R.id.tvNameStore);
            tvCodeStore = itemView.findViewById(R.id.tvCodeStore);
            tvAddressStore = itemView.findViewById(R.id.tvAddressStore);
            imgMap = itemView.findViewById(R.id.imgMap);
            itemStoreLayout = itemView.findViewById(R.id.itemStoreLayout);
        }
    }
}
