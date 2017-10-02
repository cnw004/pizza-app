package com.colewhitley.pizzaappv2;

/**
 * Created by whitguy on 9/24/17.
 */

public class Pizza {

    private String size;
    private String cost;
    private String toppings;
    private int isFavorite;

    public Pizza(String size, String cost, String toppings, int isFavorite){
        this.size = size;
        this.cost = cost;
        this.toppings = toppings;
        this.isFavorite = isFavorite;

    }

    public String getSize(){
        return this.size;
    }
    public String getCost(){
        return this.cost;
    }
    public String getToppings(){
        return this.toppings;
    }
    public int isFavorite(){
        return this.isFavorite;
    }

}
