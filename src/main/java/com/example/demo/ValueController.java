package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;

public class ValueController {

    //variables from scene builder
    @FXML
    private ListView<String> valueList;
    @FXML
    private Label valueLabel;
    @FXML
    private Button backButton;

    public void initialize() {

        //i represents total value of stock
        int i = 0;
        DisplayCase temp = MainApplication.head;

        while(temp != null) { //shows value of stock

            //j represents value of a display case
            int j = 0;
            DisplayTray temp2 = temp.getHead();

            while(temp2 != null) { //shows value of each case

                JewelleryItem temp3 = temp2.getHead();
                while(temp3 != null) {
                    i += temp3.getRetailPrice();
                    j += temp3.getRetailPrice();
                    temp3 = temp3.getNextItem();
                }
                temp2 = temp2.getNextTray();

            }

            valueList.getItems().add(temp.getUid() + ": " + j + "€");
            temp2 = temp.getHead();

            while(temp2 != null) { //shows value of each tray

                //k represents value of a display tray
                int k = 0;
                JewelleryItem temp3 = temp2.getHead();

                while(temp3 != null) {
                    k+= temp3.getRetailPrice();
                    temp3 = temp3.getNextItem();
                }

                valueList.getItems().add("\t" + temp2.getUid() + ": " + k + "€");
                temp2 = temp2.getNextTray();

            }

            temp = temp.getNextCase();

        }

        valueLabel.setText("Total Value: " + i + "€");

    }

    public void OnBackButton() throws IOException {
        //loads store scene
        FXMLLoader storeView = new FXMLLoader(ValueController.class.getResource("store-view.fxml"));
        backButton.getScene().setRoot(storeView.load());
    }

}
