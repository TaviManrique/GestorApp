package com.manriquetavi.gestorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manriquetavi.gestorapp.db.DatabaseHelperStore;

public class AddProductActivity extends AppCompatActivity {

    EditText etNameProduct, etPriceProduct, etWholesalePriceProduct, etStock;
    Button btnAddProduct;
    Integer mCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        mCode = getIntent().getIntExtra("code",0);
        etNameProduct = findViewById(R.id.etNameProduct);
        etPriceProduct = findViewById(R.id.etPriceProduct);
        etWholesalePriceProduct = findViewById(R.id.etWholesalePriceProduct);
        etStock = findViewById(R.id.etStock);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelperStore db = new DatabaseHelperStore(AddProductActivity.this);
                db.addProduct(
                        etNameProduct.getText().toString().trim(),
                        mCode,
                        etPriceProduct.getText().toString().trim(),
                        etWholesalePriceProduct.getText().toString().trim(),
                        Integer.valueOf(etStock.getText().toString().trim())
                );
            }
        });
    }
}