package com.example.oopassignment4.Controllers;

import com.example.oopassignment4.Models.Food;
import com.example.oopassignment4.Models.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ServerController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private ChoiceBox<String> serverChoiceBox;
    @FXML
    private Label serverNameLabel;
    private ArrayList<String> serverNames = new ArrayList<>();
    private ArrayList<String> serverTips = new ArrayList<>();



    //Method to close the current window using a button
    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(Server server : Server.getServers()) {
            serverNames.add(server.getName());

        }


        System.out.println("Server: " + serverNames);
        serverChoiceBox.getItems().addAll(serverNames);
        System.out.println(serverChoiceBox.getItems());
        serverChoiceBox.setOnAction(this::getServerName);


    }
@FXML
    public void getServerName(ActionEvent event) {

        String serverName = serverChoiceBox.getValue();
        serverNameLabel.setText(serverName);
    }


}