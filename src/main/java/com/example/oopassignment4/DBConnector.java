package com.example.oopassignment4;

import com.example.oopassignment4.Models.Food;
import javafx.scene.image.Image;

import java.sql.*;

public class DBConnector {

    /**
     * hostname=oopassignment4cbsm.mysql.database.azure.com
     * SQL Username: oopChristianShania
     * SQL password: j&HhN2BL70K19tB4
     * Port 3306
     */
    private static Connection connection = null;

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
            System.out.println(e);
        }
    }

    /**
     * Add methods.
     *
     * Called from the constructors of each respective model, accepts arg for each instance variable,
     * and adds it to the table
     */
    public static void addFood(String name, int calories, boolean isVegan, boolean isGlutenFree, String imagePath){
        try{
            //Use prepared statement to prevent sequel injection
            PreparedStatement newFood;
            newFood = connection.prepareStatement("INSERT INTO foods(name, calories, isVegan, isGlutenFree, imagePath) VALUES (?, ?, ?, ?, ?);");

            /**
             * Set prepared statement values based on passed parameters
             */
            newFood.setString(1, name);
            newFood.setInt(2, calories);
            newFood.setBoolean(3, isVegan);
            newFood.setBoolean(4, isGlutenFree);
            newFood.setString(5, imagePath);

            newFood.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void addMeal(String name, double price, boolean isVegan, boolean isGlutenFree){
        try{
            //Use prepared statement to prevent sequel injection
            PreparedStatement newMeal;
            newMeal = connection.prepareStatement("INSERT INTO meals(name, price, isVegan, isGlutenFree) VALUES (?, ?, ?, ?);");

            /**
             * Set prepared statement values based on passed parameters
             */
            newMeal.setString(1, name);
            newMeal.setDouble(2, price);
            newMeal.setBoolean(3, isVegan);
            newMeal.setBoolean(4, isGlutenFree);

            newMeal.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void addOrder(double subTotal, double taxRate, double tips, double total){
        try{
            //Use prepared statement to prevent sequel injection
            PreparedStatement newOrder;
            newOrder = connection.prepareStatement("INSERT INTO orders(subtotal, taxRate, tips, total) VALUES (?, ?, ?, ?);");

            /**
             * Set prepared statement values based on passed parameters
             */
            newOrder.setDouble(1, subTotal);
            newOrder.setDouble(2, taxRate);
            newOrder.setDouble(3, tips);
            newOrder.setDouble(4, total);

            newOrder.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void addServer(String name, double totalTips){
        try{
            //Use prepared statement to prevent sequel injection
            PreparedStatement newServer;
            newServer = connection.prepareStatement("INSERT INTO servers(name, totalTips) VALUES (?, ?);");

            /**
             * Set prepared statement values based on passed parameters
             */
            newServer.setString(1, name);
            newServer.setDouble(2, totalTips);

            newServer.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * Instantiate methods.
     *
     * Called by the initialize() method of the Controller at startup. Loads all of the data from the
     * tables and instantiates the objects into memory.
     */

    public static void instantiateFoods(){
        try{
            //Use prepared statement to prevent sequel injection
            PreparedStatement getFood;

            //Get all records from the food table
            getFood = connection.prepareStatement("SELECT * FROM foods");

            ResultSet foods = getFood.executeQuery();

            int id;
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
                name = foods.getString("name");
                calories = foods.getInt("calories");
                isVegan = foods.getBoolean("isVegan");
                isGlutenFree = foods.getBoolean("isGlutenFree");
                imagePath = foods.getString("imagePath");

                if(imagePath == null){
                    imagePath = "placeholder.png";
                }

                /**
                 * TODO
                 * Implement image functionality.
                 *
                 * Pass argument as String instead, String being the path of the image,
                 * and then instantiate new Image(path) inside the constructor.
                 *
                 * This lets us store the file path in the database.
                 */
                Food.addFood(new Food(id, name, calories, isVegan, isGlutenFree, imagePath, false));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void instantiateMeals(){

    }

    public static void instantiateOrders(){

    }

    public static void instantiateServers(){

    }
}