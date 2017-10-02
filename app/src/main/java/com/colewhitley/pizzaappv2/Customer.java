package com.colewhitley.pizzaappv2;

/**
 * Created by whitguy on 9/25/17.
 */

public class Customer {

    private String name;
    private String phone;

    public Customer(String name, String phone){

        this.name = name;
        this.phone = phone;

    }

    public String getName(){
        return this.name;
    }
    public String getPhone(){
        return this.phone;
    }

}
