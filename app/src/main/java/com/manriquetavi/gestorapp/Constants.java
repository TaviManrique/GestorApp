package com.manriquetavi.gestorapp;

public final class Constants {
    //Database
    public static final String DATABASE_NAME = "manager.db";
    public static final Integer DATABASE_VERSION = 1;

    //STORES_TABLE
    public static final String TABLE_NAME_STORE = "stores_table";
    public static final String COLUMN_ID_STORE = "_id";
    public static final String COLUMN_NAME_STORE = "store_name";
    public static final String COLUMN_CODE_STORE = "store_code";
    public static final String COLUMN_ADDRESS_STORE = "store_address";
    public static final String COLUMN_LATITUDE_STORE = "store_latitude";
    public static final String COLUMN_LONGITUDE_STORE = "store_longitude";

    //PRODUCTS_TABLE
    public static final String TABLE_NAME_PRODUCT = "products_table";
    public static final String COLUMN_ID_PRODUCT = "_id";
    public static final String COLUMN_NAME_PRODUCT = "product_name";
    public static final String COLUMN_CODE_STORE_PRODUCT = "product_store_code";
    public static final String COLUMN_STOCK_PRODUCT = "product_stock";
    public static final String COLUMN_PRICE_PRODUCT = "product_price";
    public static final String COLUMN_WHOLESALE_PRICE_PRODUCT= "product_wholesale_price";

    //USERS_TABLE
    public static final String TABLE_NAME_USER = "users_table";
    public static final String COLUMN_ID_USER = "_id";
    public static final String COLUMN_EMAIL_USER = "user_email";
    public static final String COLUMN_NAME_USER = "user_name";
    public static final String COLUMN_PASSWORD_USER = "user_password";

}
