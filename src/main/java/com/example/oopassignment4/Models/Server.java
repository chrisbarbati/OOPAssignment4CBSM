package com.example.oopassignment4.Models;
import com.example.oopassignment4.DBConnector;

import java.util.ArrayList;

public class Server {
    /**
     * Model class for a meal server. One server has one or more Order
     */

    /**
     * Instance variables
     */
    private int id;
    private String name;
    private ArrayList<Order> orders;
    private double totalTips;

    /**
     * Default constructor
     */

    public Server(String name, ArrayList<Order> orders, boolean dbAdd) {
        setName(name);
        setOrders(orders);

        /*
            Calculate total tips based on tips for each of the Server's orders
         */
        setTotalTips(0);
        for(Order order : orders){
            totalTips += order.getTips();
        }

        //Only executes if the passed argument dbAdd is true, indicating that it should be added to the database
        if(dbAdd){
            DBConnector.addServer(name, totalTips);
        }
    }

    /**
     * Get/set
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

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public double getTotalTips() {
        return totalTips;
    }

    public void setTotalTips(double totalTips) {
        this.totalTips = totalTips;
    }
}
