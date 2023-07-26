package com.example.oopassignment4.Controllers;


import com.example.oopassignment4.Models.Food;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private ImageView imageBox1, imageBox2, imageBox3;
    @FXML
    private Label foodNameLabel1, foodNameLabel2, foodNameLabel3;


    private ArrayList<String> foodList = new ArrayList<>();

    //Method to close the current window using a button
    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(Food food : Food.getFoods()) {
            foodList.add(food.getName());
        }

    }


}