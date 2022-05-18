package com.manriquetavi.gestorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manriquetavi.gestorapp.db.DatabaseHelperStore;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnSignIn;
    DatabaseHelperStore db_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                if (db_user.signIn(etEmail.getText().toString().trim(), etPassword.getText().toString().trim())) {
                    Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                    intent.putExtra("email", etEmail.getText().toString().trim());
                    etEmail.setText("");
                    etPassword.setText("");
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Credential Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

        db_user = new DatabaseHelperStore(LoginActivity.this);
        addDefaultUser();
    }

    private void addDefaultUser() {
        db_user.addUser("tavidanner96@gmail.com", "Tavi Manrique", "Tavi123!");
    }

}