package com.colewhitley.pizzaappv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class CollectInformation extends AppCompatActivity {

    Bundle bundle;
    TextView textView;
    Button order;
    ArrayList toppings;
    String size;
    String customerName;
    String customerPhone;
    String customerNotes;
    ToggleButton favorite;
    public int isFavorite;
    DatabaseHelper db;
    String totalString;
    EditText text_name;

    private void init(){
        //init db
        db = new DatabaseHelper(this);

        //grab bundle
        bundle = getIntent().getExtras();

        //set up textView
        textView = (TextView)findViewById(R.id.textView2);
        setTextView();

        //set up toggle button
        favorite = (ToggleButton)findViewById(R.id.favorite);
        isFavorite = 0;

        //collect toppings
        toppings = bundle.getStringArrayList("toppingsList");

        //get the size
        size = bundle.getString("size");

        //get the name
        text_name = (EditText)findViewById(R.id.text_name);


        //get the phone number
        EditText text_phone = (EditText)findViewById(R.id.text_phone);
        customerPhone = text_phone.toString();

        //get the notes
        EditText text_notes = (EditText)findViewById(R.id.text_notes);
        customerNotes = text_notes.toString();

        //set up the button
        order = (Button)findViewById(R.id.order_button);
        order.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                customerName = text_name.getText().toString();
                Toast.makeText(CollectInformation.this, "Your pizza is on the way!", Toast.LENGTH_SHORT).show();
                Intent nextScreen = new Intent(CollectInformation.this, MainActivity.class);
                addToDB();
                startActivity(nextScreen);
            }
        });

        favorite.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                if(favorite.isChecked())
                    isFavorite = 1;
                else
                    isFavorite = 0;
            }
        });
    }

    private boolean addToDB(){
        db.addCustomer(customerName, customerPhone);
        db.addPizza(size, totalString, toppingsToString(), isFavorite);

        return true;
    }

    private String toppingsToString(){
        String toppingString = "";
        if(toppings.size() > 0) {
            for (int i = 0; i < toppings.size() - 1; i++)
                toppingString += toppings.get(i).toString() + ", ";
            toppingString += toppings.get(toppings.size() - 1).toString();
        }
        return toppingString;
    }

    private void setTextView(){
        String stringSubtotal = (String) bundle.get("subtotal");
        Double subtotal = Double.parseDouble(stringSubtotal);
        Double tax = subtotal * 0.06;
        String taxString = String.format("%.2f", tax);
        Double total = subtotal + tax;
        totalString = String.format("%.2f", total);

        textView.setText("Subtotal:  $" + stringSubtotal + "\n\nTax:  $" + taxString + "\n\nTotal:  $" + totalString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_information);
        init();
    }
}
