package com.example.oopassignment4.Models;

import com.example.oopassignment4.DBConnector;

import java.util.ArrayList;

public class Meal {
    /**
     * Model class for a Meal Object. Has multiple foods.
     * eg. Hamburger, FrenchFries, Milkshake
     */

    /**
     * Instance variables
     */
    private int id;
    private String name;
    private ArrayList<Food> foods;
    private double price;
    private boolean isVegan;
    private boolean glutenFree;

    /**
     * Default constructor
     */

    public Meal(String name, ArrayList<Food> foods, double price, boolean dbAdd) {
        setName(name);
        setFoods(foods);
        setPrice(price);
        /*
            Check if all the foods inside are vegan, and set whether meal
            is vegan based on this
         */
        for(Food food : foods){
            if(food.isVegan()){
                setVegan(true);
            }else{
                setVegan(false);
                break;
            }
        }
        /*
            Check if all the foods inside are gluten-free, and set whether meal
            is gluten-free based on this
         */
        for(Food food : foods){
            if(food.isGlutenFree()){
                setGlutenFree(true);
            }else{
                setGlutenFree(false);
                break;
            }
        }

        if(dbAdd){
            DBConnector.addMeal(name, price, isVegan, isGlutenFree());
        }
    }

    /**
     * Get/Set
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }
}
