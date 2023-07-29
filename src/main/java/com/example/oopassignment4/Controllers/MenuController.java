package com.example.oopassignment4.Controllers;


import com.example.oopassignment4.DBConnector;
import com.example.oopassignment4.Models.Food;
import com.example.oopassignment4.Models.Meal;
import com.example.oopassignment4.Models.Order;
import com.example.oopassignment4.Models.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private ImageView itemImageBox;
    @FXML
    private Label foodIdLabel, foodNameLabel, foodCaloriesLabel, isVeganLabel, isGlutenFreeLabel, mealItemsLabel;
    @FXML
    private Label newOrderIdLabel, orderItemsLabel;
    @FXML
    private TextField tipTextField;
    private ArrayList<Food> foodList = new ArrayList<>();
    private ArrayList<Food> mealItemsList = new ArrayList<>();
    private ArrayList<ArrayList<Food>> orderItemsList = new ArrayList<>();
    private ArrayList<Order> orders = Order.getOrders();
    private String currentMealList = "";
    private String currentOrderItems = "";
    private int lastOrderId = orders.get(orders.size() -1).getId();

    //Method to close the current window using a button
    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    //Pressing Next Button shifts foodList Arraylist to the right
    @FXML
    void nextItem(ActionEvent event) {
        foodList.add(foodList.get(0));
        foodList.remove(0);
        setFoodListArray();
    }

    //Pressing Previous button shifts foodList ArrayList to the left
    @FXML
    void previousItem(ActionEvent event) {
        foodList.add(0, foodList.get(foodList.size()-1));
        foodList.remove(foodList.size()-1);
        setFoodListArray();
    }

    //Adds food items into meal list
    @FXML
    void addToMeal(ActionEvent event) {
        mealItemsList.add(foodList.get(0));
        currentMealList += mealItemsList.get(mealItemsList.size() -1).getName() + "\n";
        mealItemsLabel.setText(currentMealList);
    }

    //Adds meal list into database and adds meal to order
    @FXML
    void addToOrder(ActionEvent event) {

        orderItemsList.add(mealItemsList);

        int orderId = Integer.parseInt(newOrderIdLabel.getText());
        double mealPrice = mealItemsList.size() * 6.25;
        boolean mealVegan = true;
        boolean mealGlutenFree = true;

        for (int i = 0; i < mealItemsList.size(); i++){
            if (!mealItemsList.get(i).isVegan()){
                mealVegan = false;
                break;
            }
        }

        for (int i = 0; i < mealItemsList.size(); i++){
            if (!mealItemsList.get(i).isGlutenFree()) {
                mealGlutenFree = false;
                break;
            }
        }

        String mealName = "";

        for (int i = 0; i < mealItemsList.size(); i++) {
            mealName += " " + mealItemsList.get(i).getName();
        }

        mealName += " Combo";

        currentOrderItems += mealName + "\n\n";
        orderItemsLabel.setText(currentOrderItems);

        mealItemsList.clear();
        currentMealList = "";
        mealItemsLabel.setText("");

        double tip = 0;

        if (!tipTextField.getText().isEmpty() || !tipTextField.getText().isBlank()) {
            tip = Double.parseDouble(tipTextField.getText());
        }

        double total = (mealPrice * 1.13) + tip;

        if (lastOrderId+1 == orderId) {
            Random random = new Random();
            ArrayList<Server> servers = Server.getServers();
            int serverId = random.nextInt(servers.size());
            System.out.println("Creating new Order");
            DBConnector.addOrder(serverId,mealPrice, 1.13, tip, total);
            lastOrderId++;
        }

        DBConnector.addMeal(orderId, mealName, mealPrice, mealVegan, mealGlutenFree);
        System.out.println("Meal Added to Database Successfully!");
    }

    //Sets the ImageBox and Labels for the first Food item in the foodList Arraylist
    private void setFoodListArray() {

        Food activeFood = foodList.get(0);

        itemImageBox.setImage(activeFood.getImage());
        foodIdLabel.setText(String.valueOf(activeFood.getId()));
        foodNameLabel.setText(activeFood.getName());
        foodCaloriesLabel.setText("Calories: " + activeFood.getCalories());

        if (activeFood.isVegan()) {
            isVeganLabel.setText("Vegan");
        } else {
            isVeganLabel.setText("Not Vegan");
        }

        if (activeFood.isGlutenFree()) {
            isGlutenFreeLabel.setText("Gluten Free");
        } else {
            isGlutenFreeLabel.setText("Not Gluten Free");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        foodList.addAll(Food.getFoods());
        setFoodListArray();
        Order lastOrder = orders.get(orders.size() -1);
        newOrderIdLabel.setText(String.valueOf(lastOrder.getId() + 1));
    }
}