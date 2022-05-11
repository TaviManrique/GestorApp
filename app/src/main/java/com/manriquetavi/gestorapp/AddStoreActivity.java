package com.manriquetavi.gestorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.manriquetavi.gestorapp.db.DatabaseHelperStore;

public class AddStoreActivity extends AppCompatActivity {

    EditText etNameStore, etCodeStore, etAddressStore, etLatitudeStore, etLongitudeStore;
    Button btnAddStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_store);

        etNameStore = findViewById(R.id.etNameStore);
        etCodeStore = findViewById(R.id.etCodeStore);
        etAddressStore = findViewById(R.id.etAddressStore);
        etLatitudeStore = findViewById(R.id.etLatitudeStore);
        etLongitudeStore = findViewById(R.id.etLongitudeStore);
        btnAddStore = findViewById(R.id.btnAddStore);
        btnAddStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelperStore db = new DatabaseHelperStore(AddStoreActivity.this);
                db.addStore(
                        etNameStore.getText().toString().trim(),
                        Integer.valueOf(etCodeStore.getText().toString().trim()),
                        etAddressStore.getText().toString().trim(),
                        etLatitudeStore.getText().toString().trim(),
                        etLongitudeStore.getText().toString().trim()
                        );
            }
        });
    }
}