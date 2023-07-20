package com.example.oopassignment4.Models;
import java.util.ArrayList;

public class Server {
    /**
     * Model class for a meal server. One server has one or more Order
     */

    /**
     * Instance variables
     */
    private String name;
    private ArrayList<Order> orders;
    private double totalTips;

    /**
     * Default constructor
     */

    public Server(String name, ArrayList<Order> orders) {
        setName(name);
        setOrders(orders);

        /*
            Calculate total tips based on tips for each of the Server's orders
         */
        setTotalTips(0);
        for(Order order : orders){
            totalTips += order.getTips();
        }
    }

    /**
     * Get/set
     */

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
