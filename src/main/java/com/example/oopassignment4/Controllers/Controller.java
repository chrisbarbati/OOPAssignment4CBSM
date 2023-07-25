package com.example.oopassignment4.Controllers;

import com.example.oopassignment4.DBConnector;
import com.example.oopassignment4.Models.Food;
import com.example.oopassignment4.Models.Meal;
import com.example.oopassignment4.Models.Order;
import com.example.oopassignment4.Models.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button closeButton;
    @FXML
    private Label loginErrorText, loginHintTextBox;
    @FXML
    private PasswordField passwordTextBox;
    @FXML
    private TextField usernameTextBox;


    //Method to close the current window using a button
    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    //Method to open the Servers page
    @FXML
    void goToServers(ActionEvent event) {
        System.out.println("USER: " + usernameTextBox.getText());
        System.out.println("PASS: " + passwordTextBox.getText());
        System.out.println("ERROR TEXT: " + loginErrorText.getText());
        if (usernameTextBox.getText().equals("user") && passwordTextBox.getText().equals("password")) {
            loginErrorText.setText(" ");
            loginHintTextBox.setText(" ");
            openNewPage("Servers", "server-view.fxml");
        } else if (usernameTextBox.getText().isEmpty() && passwordTextBox.getText().isEmpty()) {
            loginErrorText.setText("Please enter your credentials");
            loginHintTextBox.setText("Hint: Username is user and Password is password");
        } else if (!usernameTextBox.getText().equals("user")) {
            loginErrorText.setText("Error! Username incorrect.");
        } else if (!passwordTextBox.getText().equals("password")) {
            loginErrorText.setText("Error! Password incorrect.");
        }
    }

    //Method to open the Menu page
    @FXML
    void goToMenu(ActionEvent event) {
        openNewPage("Menu", "menu-view.fxml");
    }

    //Method to open the Order page
    @FXML
    void goToOrder(ActionEvent event) {
        openNewPage("Orders", "order-view.fxml");
    }

    //Method to open a page based off the title and FXML path
    private void openNewPage(String title, String fxmlPath) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource(fxmlPath));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle(title);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Must be called once to initiate connection. Required for any database connectivity.
        DBConnector.startup();

//        DBConnector.addFood("Burger", 850, false, false);
//        DBConnector.addFood("Apple", 150, true, true);
//        DBConnector.addMeal("Burger Combo", 14.99, false, false);
//        DBConnector.addMeal("Rice Bowl Combo", 9.99, true, true);
//        DBConnector.addOrder(11.4, 113.3, 141.1, 1312.02);
//        DBConnector.addServer("Fictional Fred", 100);

        DBConnector.instantiateFoods();
        DBConnector.instantiateMeals();
        DBConnector.instantiateOrders();
        DBConnector.instantiateServers();

        /**
         * Try/catch required in case of empty ArrayList
         */
        try{
            for(Food food : Food.getFoods()){
                System.out.println(food.getName());
            }
        }catch(Exception e){
            System.out.println(e);
        }

        try{
            for(Meal meal : Meal.getMeals()){
                System.out.println(meal.getName());
            }
        }catch(Exception e){
            System.out.println(e);
        }

        try{
            for(Order order : Order.getOrders()){
                System.out.println(order.getId());
            }
        }catch(Exception e){
            System.out.println(e);
        }

        try{
            for(Server server : Server.getServers()){
                System.out.println(server.getName());
            }
        }catch(Exception e){
            System.out.println(e);
        }

    }
}

