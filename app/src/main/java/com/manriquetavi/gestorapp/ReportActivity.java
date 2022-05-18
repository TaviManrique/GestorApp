package com.manriquetavi.gestorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.manriquetavi.gestorapp.db.DatabaseHelperStore;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    RecyclerView rvProducts;
    FloatingActionButton fabAddStore;

    DatabaseHelperStore db_product;
    ArrayList<String> product_names, product_prices, product_wholesale_prices, product_stocks;
    ArrayList<Integer> product_ids;
    Integer mCode;
    ItemProductAdapter itemProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        mCode = getIntent().getIntExtra("code",0);
        rvProducts = findViewById(R.id.rvProducts);
        fabAddStore = findViewById(R.id.fabAddProduct);
        fabAddStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportActivity.this, AddProductActivity.class);
                intent.putExtra("code", mCode);
                finish();
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        //DB
        db_product = new DatabaseHelperStore(ReportActivity.this);
        product_ids = new ArrayList<>();
        product_names = new ArrayList<>();
        product_prices = new ArrayList<>();
        product_wholesale_prices = new ArrayList<>();
        product_stocks = new ArrayList<>();

        productDataInArrays();

        itemProductAdapter = new ItemProductAdapter(
                ReportActivity.this,
                product_ids,
                product_names,
                product_prices,
                product_wholesale_prices,
                product_stocks
        );
        rvProducts.setAdapter(itemProductAdapter);
        rvProducts.setLayoutManager(new LinearLayoutManager(ReportActivity.this));
        super.onResume();
    }

    void productDataInArrays() {
        Cursor cursor = db_product.readAllProducts(mCode);
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data products.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                product_ids.add(cursor.getInt(0));
                product_names.add(cursor.getString(1));
                product_prices.add(cursor.getString(3));
                product_wholesale_prices.add(cursor.getString(4));
                product_stocks.add(String.valueOf(cursor.getInt(5)));
            }
        }
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.Option1){
            saveProducts();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveProducts() {
        for(int i = 0; i < product_ids.size(); i++) {
            db_product.updateProduct(product_ids.get(i),
                    product_prices.get(i),
                    product_wholesale_prices.get(i),
                    product_stocks.get(i));
        }
        finish();
    }
}