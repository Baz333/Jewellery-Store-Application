package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;

public class ValueController {

    @FXML
    private ListView<String> valueList;
    @FXML
    private Label valueLabel;
    @FXML
    private Button backButton;

    public void initialize() {
        int i= 0;
        DisplayCase temp = MainApplication.head;
        while(temp != null) {
            int j = 0;
            DisplayTray temp2 = temp.getHead();
            while(temp2 != null) {
                JewelleryItem temp3 = temp2.getHead();
                while(temp3 != null) {
                    i += temp3.getRetailPrice();
                    j += temp3.getRetailPrice();
                    temp3 = temp3.getNextItem();
                }
                temp2 = temp2.getNextTray();
            }
            valueList.getItems().add(temp.getUid() + ": " + j + "€");
            temp = temp.getNextCase();
        }
        valueLabel.setText("Total Value: " + i + "€");
    }

    public void OnBackButton() throws IOException {
        FXMLLoader storeView = new FXMLLoader(ValueController.class.getResource("store-view.fxml"));
        backButton.getScene().setRoot(storeView.load());
    }

}
