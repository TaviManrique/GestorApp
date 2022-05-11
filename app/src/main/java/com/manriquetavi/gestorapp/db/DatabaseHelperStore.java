package com.manriquetavi.gestorapp.db;

import static com.manriquetavi.gestorapp.Constants.COLUMN_ADDRESS_STORE;
import static com.manriquetavi.gestorapp.Constants.COLUMN_CODE_STORE;
import static com.manriquetavi.gestorapp.Constants.COLUMN_CODE_STORE_PRODUCT;
import static com.manriquetavi.gestorapp.Constants.COLUMN_EMAIL_USER;
import static com.manriquetavi.gestorapp.Constants.COLUMN_ID_PRODUCT;
import static com.manriquetavi.gestorapp.Constants.COLUMN_ID_STORE;
import static com.manriquetavi.gestorapp.Constants.COLUMN_ID_USER;
import static com.manriquetavi.gestorapp.Constants.COLUMN_LATITUDE_STORE;
import static com.manriquetavi.gestorapp.Constants.COLUMN_LONGITUDE_STORE;
import static com.manriquetavi.gestorapp.Constants.COLUMN_NAME_PRODUCT;
import static com.manriquetavi.gestorapp.Constants.COLUMN_NAME_STORE;
import static com.manriquetavi.gestorapp.Constants.COLUMN_NAME_USER;
import static com.manriquetavi.gestorapp.Constants.COLUMN_PASSWORD_USER;
import static com.manriquetavi.gestorapp.Constants.COLUMN_PRICE_PRODUCT;
import static com.manriquetavi.gestorapp.Constants.COLUMN_STOCK_PRODUCT;
import static com.manriquetavi.gestorapp.Constants.COLUMN_WHOLESALE_PRICE_PRODUCT;
import static com.manriquetavi.gestorapp.Constants.DATABASE_NAME;
import static com.manriquetavi.gestorapp.Constants.DATABASE_VERSION;
import static com.manriquetavi.gestorapp.Constants.TABLE_NAME_PRODUCT;
import static com.manriquetavi.gestorapp.Constants.TABLE_NAME_STORE;
import static com.manriquetavi.gestorapp.Constants.TABLE_NAME_USER;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelperStore extends SQLiteOpenHelper {

    private final Context context;


    public DatabaseHelperStore(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTableStore =
                "CREATE TABLE " + TABLE_NAME_STORE +
                        " (" + COLUMN_ID_STORE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME_STORE + " TEXT, " +
                        COLUMN_CODE_STORE + " INTEGER UNIQUE, " +
                        COLUMN_ADDRESS_STORE + " TEXT, " +
                        COLUMN_LATITUDE_STORE + " TEXT, " +
                        COLUMN_LONGITUDE_STORE + " TEXT);";

        String queryCreateTableProduct =
                "CREATE TABLE " + TABLE_NAME_PRODUCT +
                        " (" + COLUMN_ID_PRODUCT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME_PRODUCT + " TEXT UNIQUE, " +
                        COLUMN_CODE_STORE_PRODUCT + " INTEGER, " +
                        COLUMN_PRICE_PRODUCT + " TEXT, " +
                        COLUMN_WHOLESALE_PRICE_PRODUCT + " TEXT, " +
                        COLUMN_STOCK_PRODUCT + " INTEGER, " +
                        "FOREIGN KEY (" + COLUMN_CODE_STORE_PRODUCT + ") " +
                        "REFERENCES " + TABLE_NAME_STORE +"(" + COLUMN_CODE_STORE +"));";

        String queryCreateTableUser =
                "CREATE TABLE " + TABLE_NAME_USER +
                        " (" + COLUMN_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_EMAIL_USER + " TEXT UNIQUE, " +
                        COLUMN_NAME_USER + " TEXT, " +
                        COLUMN_PASSWORD_USER + " TEXT);";

        db.execSQL(queryCreateTableStore);
        db.execSQL(queryCreateTableProduct);
        db.execSQL(queryCreateTableUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_STORE);
        onCreate(db);
    }

    //STORE TABLE
    public void addStore(String name, Integer code, String address, String latitude, String longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME_STORE, name);
        cv.put(COLUMN_CODE_STORE, code);
        cv.put(COLUMN_ADDRESS_STORE, address);
        cv.put(COLUMN_LATITUDE_STORE, latitude);
        cv.put(COLUMN_LONGITUDE_STORE, longitude);
        long result = db.insert(TABLE_NAME_STORE, null, cv);

        if(result == -1) {
            Toast.makeText(context, "Store added Failed :(", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Store added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllStores() {
        String query = "SELECT * FROM " + TABLE_NAME_STORE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //PRODUCT TABLE
    public void addProduct(String name, Integer code, String price, String wholesalePrice, Integer stock) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME_PRODUCT, name);
        cv.put(COLUMN_CODE_STORE_PRODUCT, code);
        cv.put(COLUMN_PRICE_PRODUCT, price);
        cv.put(COLUMN_WHOLESALE_PRICE_PRODUCT, wholesalePrice);
        cv.put(COLUMN_STOCK_PRODUCT, stock);
        long result = db.insert(TABLE_NAME_PRODUCT, null, cv);

        if(result == -1) {
            Toast.makeText(context, "Product added Failed :(", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Product added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllProducts(Integer code) {
        String query = "SELECT * FROM " + TABLE_NAME_PRODUCT + " WHERE " + COLUMN_CODE_STORE_PRODUCT + "=" + code;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateProduct(String row_id, String price, String wholesalePrice, Integer stock) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PRICE_PRODUCT, price);
        cv.put(COLUMN_WHOLESALE_PRICE_PRODUCT, wholesalePrice);
        cv.put(COLUMN_STOCK_PRODUCT, stock);

        long result = db.update(TABLE_NAME_PRODUCT, cv, "_id=?", new String[]{row_id});
        if(result == -1) {
            Toast.makeText(context, "Failed update product", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully update product", Toast.LENGTH_SHORT).show();
        }
    }

    //USER TABLE
    public void addUser(String email, String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_EMAIL_USER, email);
        cv.put(COLUMN_NAME_USER, name);
        cv.put(COLUMN_PASSWORD_USER, password);
        long result = db.insert(TABLE_NAME_USER, null, cv);

        if(result == -1) {
            Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean signIn(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query =
                "SELECT * FROM " + TABLE_NAME_USER +
                        " WHERE " + COLUMN_EMAIL_USER + "=? " +
                        " AND " + COLUMN_PASSWORD_USER + "=? ";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});
        return cursor.getCount() > 0;
    }

    public Cursor readName(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query =
                "SELECT * FROM " + TABLE_NAME_USER + " WHERE " + COLUMN_EMAIL_USER + "=\'" + email + "\'";

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


}
