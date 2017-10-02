package com.colewhitley.pizzaappv2;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List list = new ArrayList();
    ArrayAdapter adapter;
    AlertDialog alert;
    Button newOrder;
    DatabaseHelper db;
    ArrayList<Pizza> pizzaList;
    ArrayList<Customer> customerList;

    public void init(){
        //init db
        db = new DatabaseHelper(this);

        //init pizzas from db
        pizzaList = db.getPizzas();

        //init customers from db
        customerList = db.getCustomers();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Pizza Toppings");
        builder.setNegativeButton("Order", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Your pizza is on the way!", Toast.LENGTH_SHORT).show();
            }
        });
        alert = builder.create();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String toppings = pizzaList.get(i).getToppings();
                String toppingString = toppings.replaceAll(", ", "\n");
                alert.setMessage(toppingString);
                alert.show();

            }
        });

        newOrder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent nextScreen = new Intent(MainActivity.this, CustomizePizza.class);
                startActivity(nextScreen);
            }
        });

        populateListview();

    }

    public void populateListview(){
        for(int i = 0; i < pizzaList.size(); i++) {
            if(pizzaList.get(i).isFavorite() == 1)
                list.add(customerList.get(i).getName() + "'s " + pizzaList.get(i).getSize() + " Pizza" + "  ($" + pizzaList.get(i).getCost() + ")");

        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView)findViewById(R.id.list_view);
        newOrder = (Button) findViewById(R.id.new_order);

        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_expandable_list_item_1, list);
        listView.setAdapter(adapter);


        init();
    }
}
