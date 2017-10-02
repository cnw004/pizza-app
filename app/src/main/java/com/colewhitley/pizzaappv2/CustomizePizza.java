package com.colewhitley.pizzaappv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class CustomizePizza extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<String> adapterSize;
    ArrayAdapter<String> adapterToppings;
    TextView total;
    Button checkout;
    ListView listView;
    ArrayList<ToggleButton> toggleList;
    List list = new ArrayList();
    Double cost;
    Double sizeCost;
    Bundle bundle;

    public void init(){
        //init bundle
        bundle = new Bundle();

        //the cost
        cost = 0.0;
        sizeCost = 10.0;

        //set up the listview
        listView = (ListView)findViewById(R.id.list_view2);
        adapterToppings = new ArrayAdapter(CustomizePizza.this, android.R.layout.simple_expandable_list_item_1, list);
        //listView.setAdapter(adapter);

        //set up the textview
        total = (TextView)findViewById(R.id.text_total);
        total.setText("Subtotal\n\n$" + getCost());

        //set up the spinner
        spinner = (Spinner)findViewById(R.id.size_spinner);
        String[] items = new String[]{"Small", "Medium", "Large", "X-Large"};
        adapterSize = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapterSize);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                growImage();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //set up the button
        checkout = (Button)findViewById(R.id.checkout_btn);
        checkout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                bundle.putString("subtotal", getCost());
                bundle.putStringArrayList("toppingsList", (ArrayList)list);
                bundle.putString("size", getSize());
                Intent nextScreen = new Intent(CustomizePizza.this, CollectInformation.class);
                nextScreen.putExtras(bundle);
                startActivity(nextScreen);
            }
        });

        //set up toggle button listeners
        toggleList = new ArrayList<ToggleButton>();
        toggleList.add((ToggleButton)findViewById(R.id.toggle1));
        toggleList.add((ToggleButton)findViewById(R.id.toggle2));
        toggleList.add((ToggleButton)findViewById(R.id.toggle3));
        toggleList.add((ToggleButton)findViewById(R.id.toggle4));
        toggleList.add((ToggleButton)findViewById(R.id.toggle5));
        toggleList.add((ToggleButton)findViewById(R.id.toggle6));
        toggleList.add((ToggleButton)findViewById(R.id.toggle7));
        toggleList.add((ToggleButton)findViewById(R.id.toggle8));
        toggleList.add((ToggleButton)findViewById(R.id.toggle9));

        toggleList.get(0).setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String topping = toggleList.get(0).getText().toString();
                if(toggleList.get(0).isChecked()) {
                    list.add(topping + "  ($1.50)");
                    listView.setAdapter(adapterToppings);
                    cost += 1.50;
                }
                else{
                    list.remove(topping  + "  ($1.50)");
                    listView.setAdapter(adapterToppings);
                    cost -= 1.50;
                }
                total.setText("Subtotal\n\n$" + getCost());
            }
        });
        toggleList.get(1).setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String topping = toggleList.get(1).getText().toString();
                if(toggleList.get(1).isChecked()) {
                    list.add(topping  + "  ($1.50)");
                    listView.setAdapter(adapterToppings);
                    cost+=1.50;
                }
                else {
                    list.remove(topping  + "  ($1.50)");
                    listView.setAdapter(adapterToppings);
                    cost-=1.50;
                }
                total.setText("Subtotal\n\n$" + getCost());
            }
        });
        toggleList.get(2).setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String topping = toggleList.get(2).getText().toString();
                if(toggleList.get(2).isChecked()) {
                    list.add(topping  + "  ($1.50)");
                    listView.setAdapter(adapterToppings);
                    cost+=1.50;
                }
                else {
                    list.remove(topping  + "  ($1.50)");
                    listView.setAdapter(adapterToppings);
                    cost -= 1.50;
                }
                total.setText("Subtotal\n\n$" + getCost());
            }
        });
        toggleList.get(3).setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String topping = toggleList.get(3).getText().toString();
                if(toggleList.get(3).isChecked()) {
                    list.add(topping  + "  ($1.00)");
                    listView.setAdapter(adapterToppings);
                    cost += 1.00;
                }
                else {
                    list.remove(topping  + "  ($1.00)");
                    listView.setAdapter(adapterToppings);
                    cost -= 1.00;
                }
                total.setText("Subtotal\n\n$" + getCost());
            }
        });
        toggleList.get(4).setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String topping = toggleList.get(4).getText().toString();
                if(toggleList.get(4).isChecked()) {
                    list.add(topping  + "  ($1.00)");
                    listView.setAdapter(adapterToppings);
                    cost += 1.00;
                }
                else {
                    list.remove(topping  + "  ($1.00)");
                    listView.setAdapter(adapterToppings);
                    cost -= 1.00;
                }
                total.setText("Subtotal\n\n$" + getCost());
            }
        });
        toggleList.get(5).setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String topping = toggleList.get(5).getText().toString();
                if(toggleList.get(5).isChecked()) {
                    list.add(topping  + "  ($1.00)");
                    listView.setAdapter(adapterToppings);
                    cost += 1.00;
                }
                else {
                    list.remove(topping  + "  ($1.00)");
                    listView.setAdapter(adapterToppings);
                    cost -= 1.00;
                }
                total.setText("Subtotal\n\n$" + getCost());
            }
        });
        toggleList.get(6).setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String topping = toggleList.get(6).getText().toString();
                if(toggleList.get(6).isChecked()) {
                    list.add(topping  + "  ($1.00)");
                    listView.setAdapter(adapterToppings);
                    cost += 1.00;
                }
                else {
                    list.remove(topping  + "  ($1.00)");
                    listView.setAdapter(adapterToppings);
                    cost -= 1.00;
                }
                total.setText("Subtotal\n\n$" + getCost());
            }
        });
        toggleList.get(7).setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String topping = toggleList.get(7).getText().toString();
                if(toggleList.get(7).isChecked()) {
                    list.add(topping  + "  ($1.00)");
                    listView.setAdapter(adapterToppings);
                    cost += 1.00;
                }
                else {
                    list.remove(topping  + "  ($1.00)");
                    listView.setAdapter(adapterToppings);
                    cost -= 1.00;
                }
                total.setText("Subtotal\n\n$" + getCost());
            }
        });
        toggleList.get(8).setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String topping = toggleList.get(8).getText().toString();
                if(toggleList.get(8).isChecked()) {
                    list.add(topping  + "  ($1.00)");
                    listView.setAdapter(adapterToppings);
                    cost += 1.00;
                }
                else {
                    list.remove(topping  + "  ($1.00)");
                    listView.setAdapter(adapterToppings);
                    cost -= 1.00;
                }
                total.setText("Subtotal\n\n$" + getCost());
            }
        });

    }

    private String getCost(){
        return String.format("%.2f", (cost+sizeCost));
    }

    private String getSize(){
        return spinner.getSelectedItem().toString();
    }


    private void growImage(){
        ImageView pizza = (ImageView)findViewById(R.id.pizza_picture);
        String size = getSize();

        if(size.equals("Small")) {
            pizza.setScaleX(3.0f);
            pizza.setScaleY(3.0f);
            sizeCost = 10.00;
        }
        if(size.equals("Medium")) {
            pizza.setScaleX(3.5f);
            pizza.setScaleY(3.5f);
            sizeCost = 12.00;
        }
        if(size.equals("Large")) {
            pizza.setScaleX(4.0f);
            pizza.setScaleY(4.0f);
            sizeCost = 14.00;
        }
        if(size.equals("X-Large")) {
            pizza.setScaleX(4.5f);
            pizza.setScaleY(4.5f);
            sizeCost = 16.00;
        }
        total.setText("Subtotal\n\n$" + getCost());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_pizza);
        init();
    }
}
