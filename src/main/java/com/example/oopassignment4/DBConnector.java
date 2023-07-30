package com.example.oopassignment4;

import com.example.oopassignment4.Models.Food;
import com.example.oopassignment4.Models.Meal;
import com.example.oopassignment4.Models.Order;
import com.example.oopassignment4.Models.Server;
import javafx.scene.image.Image;

import java.sql.*;
import java.util.ArrayList;

public class DBConnector {

    /**
     * hostname=oopassignment4cbsm.mysql.database.azure.com
     * SQL Username: oopChristianShania
     * SQL password: j&HhN2BL70K19tB4
     * Port 3306
     */
    private static Connection connection = null;

    static int nextServerId = 0;
    static int nextOrderId = 0;
    static int nextMealId = 0;
    static int nextFoodId = 0;

    /**
     * Method to contain all common connection code.
     *
     * Violates convention because connection is not closed, but
     * should be fine for our purposes.
     */
    public static void startup(){
        try {
            //Below line is used for connectivity, calls static methods in the class
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://oopassignment4cbsm.mysql.database.azure.com:3306/oopassignment?useSSL=true", "oopChristianShania", "j&HhN2BL70K19tB4");
        } catch (Exception e) {
        }
    }

    /**
     * Add methods.
     *
     * Called from the constructors of each respective model, accepts arg for each instance variable,
     * and adds it to the table
     */
    public static void addFood(int mealID, String name, int calories, boolean isVegan, boolean isGlutenFree, String imagePath){
        try{
            //Use prepared statement to prevent sequel injection
            PreparedStatement newFood;
            newFood = connection.prepareStatement("INSERT INTO foods(ID, mealID, name, calories, isVegan, isGlutenFree, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?);");

            /**
             * Set prepared statement values based on passed parameters
             */
            newFood.setInt(1, nextFoodId);
            nextFoodId++;
            newFood.setInt(2, mealID);
            newFood.setString(3, name);
            newFood.setInt(4, calories);
            newFood.setBoolean(5, isVegan);
            newFood.setBoolean(6, isGlutenFree);
            newFood.setString(7, imagePath);

            newFood.executeUpdate();
        }catch(Exception e){
        }
    }
    public static void addMeal(int orderId, String name, double price, boolean isVegan, boolean isGlutenFree){
        try{
            //Use prepared statement to prevent sequel injection
            PreparedStatement newMeal;
            newMeal = connection.prepareStatement("INSERT INTO meals(ID, orderId, name, price, isVegan, isGlutenFree) VALUES (?, ?, ?, ?, ?, ?);");

            /**
             * Set prepared statement values based on passed parameters
             */
            newMeal.setInt(1, nextMealId);
            nextMealId++;
            newMeal.setInt(2, orderId);
            newMeal.setString(3, name);
            newMeal.setDouble(4, price);
            newMeal.setBoolean(5, isVegan);
            newMeal.setBoolean(6, isGlutenFree);

            newMeal.executeUpdate();
        }catch(Exception e){
        }
    }
    public static void addOrder(int serverId, double subTotal, double taxRate, double tips, double total){
        try{
            //Use prepared statement to prevent sequel injection
            PreparedStatement newOrder;
            newOrder = connection.prepareStatement("INSERT INTO orders(ID, serverId, subtotal, taxRate, tips, total) VALUES (?, ?, ?, ?, ?, ?);");

            /**
             * Set prepared statement values based on passed parameters
             */
            newOrder.setInt(1, nextOrderId);
            nextOrderId++;
            newOrder.setInt(2, serverId);
            newOrder.setDouble(3, subTotal);
            newOrder.setDouble(4, taxRate);
            newOrder.setDouble(5, tips);
            newOrder.setDouble(6, total);

            newOrder.executeUpdate();
        }catch(Exception e){
        }
    }
    public static void addServer(String name, double totalTips){
        try{
            //Use prepared statement to prevent sequel injection
            PreparedStatement newServer;
            newServer = connection.prepareStatement("INSERT INTO servers(ID, name, totalTips) VALUES (?, ?, ?);");

            /**
             * Set prepared statement values based on passed parameters
             */
            newServer.setInt(1, nextServerId);
            nextServerId++;
            newServer.setString(2, name);
            newServer.setDouble(3, totalTips);

            newServer.executeUpdate();
        }catch(Exception e){
        }
    }

    /**
     * Instantiate methods.
     *
     * Called by the initialize() method of the Controller at startup. Loads all of the data from the
     * tables and instantiates the objects into memory.
     */


    public static void instantiateServers(){
        try{
            //Use prepared statement to prevent sequel injection
            PreparedStatement getServers;

            //Get all records from the food table
            getServers = connection.prepareStatement("SELECT * FROM servers");

            ResultSet servers = getServers.executeQuery();

            int id;
            String name;
            double totalTips;

            /**
             * Iterate over each record and instantiate the object into memory, with the dbAdd parameter in the constructor
             * set to false, preventing duplicate records
             */
            while(servers.next()){
                id = servers.getInt("ID");
                name = servers.getString("name");
                totalTips = servers.getDouble("totalTips");
                Server.addServer(new Server(id, name, new ArrayList<>(), false));
                nextServerId = id + 1;
            }
        }catch(Exception e){
        }
    }
    public static void instantiateOrders(){
        try{
            //Use prepared statement to prevent sequel injection
            PreparedStatement getOrders;

            //Get all records from the food table
            getOrders = connection.prepareStatement("SELECT * FROM orders");

            ResultSet orders = getOrders.executeQuery();

            int id;
            int serverId;
            double subTotal;
            double taxRate;
            double tips;
            double total;

            /**
             * Iterate over each record and instantiate the object into memory, with the dbAdd parameter in the constructor
             * set to false, preventing duplicate records
             */
            while(orders.next()){
                id = orders.getInt("ID");
                serverId = orders.getInt("serverID");
                subTotal = orders.getDouble("subtotal");
                taxRate = orders.getDouble("taxRate");
                tips = orders.getDouble("tips");
                total = orders.getDouble("total");

                nextOrderId = id + 1;

                /**
                 * Instantiate a new Order object, then add it to the master ArrayList for Orders
                 * and add it to this Server's orders Arraylist
                 */
                Order currentOrder = new Order(id, serverId, new ArrayList<>(), tips, false);
                Order.addOrder(currentOrder);
                Server.getServers().get(serverId).addOrder(currentOrder);

            }
        }catch(Exception e){
        }
    }
    public static void instantiateMeals(){
        try{
            //Use prepared statement to prevent sequel injection
            PreparedStatement getMeals;

            //Get all records from the food table
            getMeals = connection.prepareStatement("SELECT * FROM meals");

            ResultSet meals = getMeals.executeQuery();

            int id;
            int orderId;
            String name;
            double price;
            boolean isVegan;
            boolean isGlutenFree;

            /**
             * Iterate over each record and instantiate the object into memory, with the dbAdd parameter in the constructor
             * set to false, preventing duplicate records
             */

            while(meals.next()){
                id = meals.getInt("ID");
                orderId = meals.getInt("orderID");
                name = meals.getString("name");
                price = meals.getDouble("price");

                /**
                 * Instantiate a new Meal object, then add it to the master ArrayList for Meals
                 * and add it to this Order's meals Arraylist
                 */
                Meal currentMeal = new Meal(id, orderId, name, new ArrayList<>(), price, false);
                Meal.addMeal(currentMeal);
                Order.getOrders().get(orderId).addMeal(currentMeal);

                nextMealId = id + 1;
            }
        }catch(Exception e){
        }
    }
    public static void instantiateFoods(){
        try{
            //Use prepared statement to prevent sequel injection
            PreparedStatement getFood;

            //Get all records from the food table
            getFood = connection.prepareStatement("SELECT * FROM foods");

            ResultSet foods = getFood.executeQuery();

            int id;
            int mealId;
            String name;
            int calories;
            boolean isVegan;
            boolean isGlutenFree;
            String imagePath;

            /**
             * Iterate over each record and instantiate the object into memory, with the dbAdd parameter in the constructor
             * set to false, preventing duplicate records
             */
            while(foods.next()){
                id = foods.getInt("ID");
                mealId = foods.getInt("mealID");
                name = foods.getString("name");
                calories = foods.getInt("calories");
                isVegan = foods.getBoolean("isVegan");
                isGlutenFree = foods.getBoolean("isGlutenFree");
                imagePath = foods.getString("imagePath");

                nextFoodId = id + 1;

                if(imagePath == null){
                    imagePath = "placeholder.png";
                }

                /**
                 * Instantiate a new Meal object, then add it to the master ArrayList for Meals
                 * and add it to this Order's meals Arraylist
                 */
                Food currentFood = new Food(id, mealId, name, calories, isVegan, isGlutenFree, imagePath, false);
                Food.addFood(currentFood);
                Meal.getMeals().get(mealId).addFood(currentFood);
            }
        }catch(Exception e){
        }
    }
}