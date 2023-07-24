package com.example.oopassignment4;

import com.example.oopassignment4.Models.Food;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Tests");

        //Must be called once to initiate connection. Required for any database connectivity.
        DBConnector.startup();

        //DBConnector.DBTest();

        DBConnector.addFood("Test meal", 150, true, false, "placeholder.png");
        //DBConnector.addMeal("Test meal", 150, true, false);
        //DBConnector.addOrder(11.4, 113.3, 141.1, 1312.02);
        //DBConnector.addServer("Fictional Fred", 100);

        DBConnector.instantiateFoods();

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
    }
}