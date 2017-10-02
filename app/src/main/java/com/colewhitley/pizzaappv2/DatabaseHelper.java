package com.colewhitley.pizzaappv2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by whitguy on 9/24/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "myPizzaDB";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public boolean addCustomer(String name, String phone){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name); //col name, value to add
        contentValues.put("phone", phone);
        db.insert("customers", null, contentValues);
        db.close();
        return true;
    }

    public boolean addPizza(String size, String cost, String toppings, int favorite){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("size", size);
        contentValues.put("cost", cost);
        contentValues.put("toppings", toppings);
        contentValues.put("favorite", favorite);
        db.insert("pizzas", null, contentValues);
        db.close();

        return true;
    }

    public ArrayList<Pizza> getPizzas(){
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"size", "cost", "toppings", "favorite"};
        Cursor cursor = db.query("pizzas", columns, null, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        ArrayList<Pizza> pizzas = new ArrayList<Pizza>();

        //currently not looking at favorite
        Pizza pizzaOne = new Pizza(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
        pizzas.add(pizzaOne);
        while(cursor.moveToNext()){
            Pizza p = new Pizza(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            pizzas.add(p);
        }
        return pizzas;

    }

    public ArrayList<Customer> getCustomers(){
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"name", "phone"};
        Cursor cursor = db.query("customers", columns, null, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        ArrayList<Customer> customers = new ArrayList<Customer>();

        //currently not looking at favorite
        Customer customerOne = new Customer(cursor.getString(0), cursor.getString(1));
        customers.add(customerOne);
        while(cursor.moveToNext()){
            Customer c = new Customer(cursor.getString(0), cursor.getString(1));
            customers.add(c);
        }
        return customers;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCustomers = "CREATE TABLE customers(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, phone VARCHAR);";
        String sqlPizzas = "CREATE TABLE pizzas(id INTEGER PRIMARY KEY AUTOINCREMENT, size VARCHAR, cost VARCHAR, toppings VARCHAR, favorite INTEGER, customer_id INTEGER, FOREIGN KEY(customer_id) REFERENCES customers(id));";
        sqLiteDatabase.execSQL(sqlCustomers);
        sqLiteDatabase.execSQL(sqlPizzas);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqlCustomers = "DROP TABLE IF EXISTS customers;";
        String sqlPizzas = "DROP TABLE IF EXISTS pizzas;";

        sqLiteDatabase.execSQL(sqlCustomers);
        sqLiteDatabase.execSQL(sqlPizzas);

        //onCreate(sqLiteDatabase);

    }
}
