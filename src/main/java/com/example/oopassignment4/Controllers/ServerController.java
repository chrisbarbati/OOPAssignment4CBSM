package com.example.oopassignment4.Controllers;

import com.example.oopassignment4.Models.Order;
import com.example.oopassignment4.Models.Server;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ServerController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private ComboBox<Server> serverChoiceBox;
    @FXML
    private Label orderInformation;

    @FXML
    private ListView<String> ordersListView;
    @FXML
    private Label totalTipsLabel;
    private ArrayList<String> serverNames = new ArrayList<>();
    private ArrayList<String> serverTips = new ArrayList<>();



    //Method to close the current window using a button
    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void updateOrders(){
        if(serverChoiceBox.getValue().getOrders() != null){
            ObservableList<String> serverOrders = FXCollections.observableArrayList();

            for(Order order : serverChoiceBox.getValue().getOrders()){
                serverOrders.add(String.valueOf(order.getId()));
            }

            ordersListView.setItems(serverOrders);
        }

        getServerTips();
    }

    public void updateOrderInfo(){
        int selectedOrderNum = Integer.parseInt(ordersListView.getSelectionModel().getSelectedItem());

        Order selectedOrder = Order.getOrders().get(selectedOrderNum);

        orderInformation.setText(
                "Sub-Total: " + String.format("$%.2f", selectedOrder.getSubTotal()) + "\n" +
                        "Tip: " + String.format("$%.2f", selectedOrder.getTips()) + "\n"    +
                        "Tax-Rate: " + String.format("$%.2f", (selectedOrder.getTaxRate() * 100 - 100)) + "%\n" +
                        "Total: " + String.format("$%.2f", (selectedOrder.getTotal()))
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        serverChoiceBox.setItems(FXCollections.observableArrayList(Server.getServers()));
        serverChoiceBox.setOnAction(event -> updateOrders());
        serverChoiceBox.getSelectionModel().selectFirst(); //Set first item to default selection
        //Run both update functions once for the default selection
        updateOrders();


        //Call an update function whenever the ListView items are selected
        ordersListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                updateOrderInfo();
            }
        });

        ordersListView.getSelectionModel().selectFirst();

        //updateOrderInfo();

        getServerTips();

    }
@FXML
    public void getServerTips() {

        String serverName = String.format("$%.2f",serverChoiceBox.getValue().getTotalTips());
        totalTipsLabel.setText(serverName);
    }




}