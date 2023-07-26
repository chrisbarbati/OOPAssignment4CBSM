package com.example.oopassignment4.Models;

import com.example.oopassignment4.DBConnector;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Food {
    /**
     * Model class for a Food object. eg. Hamburger
     */

    /**
     * Static ArrayList to hold all foods instantiated and make them easier to access.
     */
    private static ArrayList<Food> foods = new ArrayList<>();

    /**
     * Instance variables
     */
    private int id;
    private int mealId;
    private String name;
    private int calories;
    private boolean isVegan;
    private boolean glutenFree;
    private Image image;
    private String imagePath;

    /**
     * Default constructor
     */

    public Food(int id, int mealId, String name, int calories, boolean isVegan, boolean glutenFree, String imagePath, boolean dbAdd) {
        setId(id);
        setMealId(mealId);
        setName(name);
        setCalories(calories);
        setVegan(isVegan);
        setGlutenFree(glutenFree);
        setImagePath(imagePath);
        setImage(new Image(imagePath));


        if(dbAdd){
            DBConnector.addFood(mealId, name, calories, isVegan, isGlutenFree(), imagePath);
        }

    }

    /**
     * Get/Set
     */

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public static void addFood(Food food){
        foods.add(food);
    }

    public static ArrayList<Food> getFoods(){
        return foods;
    }
}
