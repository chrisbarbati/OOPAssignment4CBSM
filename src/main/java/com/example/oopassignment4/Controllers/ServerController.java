package com.example.oopassignment4.Controllers;

import com.example.oopassignment4.Models.Food;
import com.example.oopassignment4.Models.Order;
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
import java.util.List;
import java.util.ResourceBundle;

public class ServerController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private ChoiceBox<String> serverChoiceBox, orderChoiceBox;
    @FXML
    private Label serverNameLabel, totalTipsLabel, orderIdLabel, orderTaxRateLabel, orderTotalLabel, orderSubtotalLabel, orderTipsLabel;
    private ArrayList<ArrayList<Order>> serverOrders = new ArrayList<>();
    private ArrayList<Server> listOfServers = new ArrayList<>();
    private Server activeServer;


    //Method to close the current window using a button
    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    //Initializes items in page
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Adds Servers to Arraylist and their orders to a separate Arraylist
        for(Server server : Server.getServers()) {
            listOfServers.add(server);
            serverOrders.add(server.getOrders());
        }

        //Adds Server names to choice box
        for (int names = 0; names < listOfServers.size(); names++) {
            serverChoiceBox.getItems().add(listOfServers.get(names).getName());
        }

        //Action event for selecting a server name from choice box
        serverChoiceBox.setOnAction(this::getServerInfo);
    }

    //Method for when server choice box is selected
    @FXML
    public void getServerInfo(ActionEvent event) {

        String activeServerName = serverChoiceBox.getValue();
        serverNameLabel.setText(activeServerName);

        //Sets active server information
        for (int i = 0; i < listOfServers.size(); i++) {
            if (activeServerName.equals(listOfServers.get(i).getName())){
                activeServer = listOfServers.get(i);
                totalTipsLabel.setText("$" + String.format("%.2f", activeServer.getTotalTips()));
                break;
            }
        }

        //Resets the Order Choice box each time a new Server is selected
        orderChoiceBox.getItems().removeAll(orderChoiceBox.getItems());

        //Sets order numbers in choice box for selected server
        for (int i = 0; i < activeServer.getOrders().size(); i++) {
            Order currentOrder = activeServer.getOrders().get(i);
            int currentOrderId = currentOrder.getId();
            orderChoiceBox.getItems().add(String.valueOf(currentOrderId));
        }

        //Action event for selecting an order id from choice box
        orderChoiceBox.setOnAction(this::getOrderInfo);
    }

    @FXML
    public void getOrderInfo(ActionEvent event) {
        Order activeOrder;

        String activeOrderId = orderChoiceBox.getValue();
        orderIdLabel.setText(activeOrderId);

        try {
            //Sets active order information
            for (int i = 0; i < serverOrders.size(); i++) {
                for (int j = 0; j < serverOrders.get(i).size(); j++) {
                    if (Integer.valueOf(activeOrderId) == serverOrders.get(i).get(j).getId()){
                        activeOrder = serverOrders.get(i).get(j);
                        orderSubtotalLabel.setText("$" + String.format("%.2f", activeOrder.getSubTotal()));
                        orderTipsLabel.setText("$" + String.format("%.2f", activeOrder.getTips()));
                        orderTaxRateLabel.setText(String.format("%.2f", activeOrder.getTaxRate()));
                        orderTotalLabel.setText("$" + String.format("%.2f", activeOrder.getTotal()));
                        break;
                    }
                }
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }
}