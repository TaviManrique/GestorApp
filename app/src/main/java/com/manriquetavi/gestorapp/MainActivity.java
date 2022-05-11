package com.manriquetavi.gestorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.manriquetavi.gestorapp.db.DatabaseHelperStore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvStores;
    FloatingActionButton fabAddStore;
    DrawerLayout drawerLayout;
    TextView tvEmail, tvName, tvLogOut;

    DatabaseHelperStore db_store;
    ArrayList<String> store_ids, store_names, store_address, store_latitudes, store_longitudes;
    ArrayList<Integer> store_codes;
    ItemStoreAdapter itemStoreAdapter;
    ImageView ibProfile;

    String mEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmail = "tavidanner96@gmail.com";
        drawerLayout = findViewById(R.id.drawer_layout);
        ibProfile = findViewById(R.id.ibProfile);
        tvEmail = findViewById(R.id.tvEmail);
        tvName = findViewById(R.id.tvName);
        tvLogOut = findViewById(R.id.tvLogOut);
        rvStores = findViewById(R.id.rvStores);
        tvEmail.setText(mEmail);
        fabAddStore = findViewById(R.id.fabAddStore);
        fabAddStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddStoreActivity.class);
                startActivity(intent);
            }
        });
        ibProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeDrawer(drawerLayout);
            }
        });
        tvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut(MainActivity.this);
            }
        });

        //DB
        db_store = new DatabaseHelperStore(MainActivity.this);
        store_ids = new ArrayList<>();
        store_names = new ArrayList<>();
        store_codes = new ArrayList<>();
        store_address = new ArrayList<>();
        store_latitudes = new ArrayList<>();
        store_longitudes = new ArrayList<>();

        storeDataInArrays();
        itemStoreAdapter = new ItemStoreAdapter(
                MainActivity.this,
                store_ids,
                store_names,
                store_codes,
                store_address,
                store_latitudes,
                store_longitudes);
        rvStores.setAdapter(itemStoreAdapter);
        rvStores.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        getName();
    }

    private void logOut(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog
                .Builder(activity)
                .setTitle("LogOut")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.finishAffinity();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        builder.show();
    }

    private void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if(id == R.id.Option1){
            Toast.makeText(this, "Profile option", Toast.LENGTH_SHORT).show();
            openDrawer(drawerLayout);
        }
        return super.onOptionsItemSelected(item);
    }

    private void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    void storeDataInArrays() {
        Cursor cursor = db_store.readAllStores();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data stores.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                store_ids.add(cursor.getString(0));
                store_names.add(cursor.getString(1));
                store_codes.add(cursor.getInt(2));
                store_address.add(cursor.getString(3));
                store_latitudes.add(cursor.getString(4));
                store_longitudes.add(cursor.getString(5));
            }
        }
    }

    private void getName() {
        Cursor cursor = db_store.readName(mEmail);
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No name.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                tvName.setText(String.valueOf(cursor.getString(2)));
            }
        }
    }
}