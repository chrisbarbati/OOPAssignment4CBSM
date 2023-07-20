package com.example.oopassignment4.Models;

import javafx.scene.image.Image;

public class Food {
    /**
     * Model class for a Food object. eg. Hamburger
     */

    /**
     * Instance variables
     */

    private String name;
    private int calories;
    private boolean isVegan;
    private boolean glutenFree;
    private Image image;

    /**
     * Default constructor
     */

    public Food(String name, int calories, boolean isVegan, boolean glutenFree, Image image) {
        setName(name);
        setCalories(calories);
        setVegan(isVegan);
        setGlutenFree(glutenFree);
        setImage(image);
    }

    /**
     * Get/Set
     */

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
}
