package com.example.oopassignment4.Models;

import com.example.oopassignment4.DBConnector;

import java.util.ArrayList;

public class Order {
    /**
     * Model class for Order object.
     * Has at least one meal, possibly more.
     * Eg. Hamburger meal, sushi meal, pasta meal
     */

    /**
     * Instance variables
     */

    private int id;
    private int serverId;
    private ArrayList<Meal> meals;
    private double subTotal;
    private double taxRate;
    private double tips;
    private double total;
    private static ArrayList<Order> orders = new ArrayList<>();

    /**
     * Default constructor
     */

    public Order(int id, int serverId, ArrayList<Meal> meals, double tips, boolean dbAdd) {
        setId(id);
        setServerId(serverId);
        setMeals(meals);
        taxRate = 1.13; //Tax rate will not change, so we will always keep it as 1.13
        setTips(tips);

        //Start total at zero, set based on total price of meals
        setSubTotal(0);
        for(Meal meal : meals){
            subTotal += meal.getPrice();
        }

        //Calculate total based on subtotal and tips
        setTotal((subTotal * taxRate) + tips);


        if(dbAdd){
            DBConnector.addOrder(serverId, subTotal, taxRate, tips, total);
        }
    }

    public void updateTotals(){
        //Start total at zero, set based on total price of meals
        setSubTotal(0);
        for(Meal meal : meals){
            subTotal += meal.getPrice();
        }

        //Calculate total based on subtotal and tips
        setTotal((subTotal * taxRate) + tips);
    }

    /**
     * Get/set
     */

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public static ArrayList<Order> getOrders() {
        return orders;
    }

    public static void setOrders(ArrayList<Order> orders) {
        Order.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getTips() {
        return tips;
    }

    public void setTips(double tips) {
        this.tips = tips;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public static void addOrder(Order order){
        orders.add(order);
    }

    public void addMeal(Meal meal){
        meals.add(meal);

        /**
         * Called whenever a meal is added, to update the totals.
         */
        updateTotals();
    }
}
