package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;

import java.io.IOException;

public class TrayViewController {

    //variables from scene builder
    @FXML
    private ListView<String> itemList;
    @FXML
    private Button backButton;
    @FXML
    private Label trayNumberLabel;

    //stores a jewellery item to be loaded in another scene
    public static JewelleryItem ji;



    public void initialize() throws IOException {

        //imports display tray to load in
        DisplayTray dt = CaseViewController.dt;

        //sets up label to show tray uid
        trayNumberLabel.setText("Tray " + dt.getUid());

        //lists all items in tray
        repopulate();

        //drill down function
        //gets selected item, finds item it belongs to, assigns it to static variable to be used in ItemViewController and loads item scene
        itemList.setOnMouseClicked(click -> {
            if(click.getClickCount() == 2) {
                int selectedIndex = itemList.getSelectionModel().getSelectedIndex();
                JewelleryItem temp2 = CaseViewController.dt.getHead();
                for(int i = 0; i < selectedIndex; i++) {
                    temp2 = temp2.getNextItem();
                }
                ji = temp2;
                ItemViewController.drillDown = true;
                FXMLLoader itemScene = new FXMLLoader(CaseViewController.class.getResource("item-view.fxml"));
                try {
                    itemList.getScene().setRoot(itemScene.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //delete item on delete key pressed
        itemList.setOnKeyPressed(keyEvent -> {
            KeyCode code = keyEvent.getCode();
            if(code==KeyCode.DELETE) {
                if(dt.getHead() == null) {
                    return;
                } else if(dt.getHead().getNextItem() == null) {
                    dt.setHead(null);
                    repopulate();
                } else {
                    int i = itemList.getSelectionModel().getSelectedIndex();
                    JewelleryItem temp2 = dt.getHead();
                    for (int j = 0; j < i - 1; j++) {
                        temp2 = temp2.getNextItem();
                    }
                    temp2.setNextItem(temp2.getNextItem().getNextItem());
                    repopulate();
                }
            }
        });

    }

    //lists all items in tray
    private void repopulate() {
        itemList.getItems().clear();
        JewelleryItem temp = CaseViewController.dt.getHead();
        while(temp != null) {
            itemList.getItems().add(temp.getType() + " (" + temp.getTargetGender() + "), " + temp.getItemDescription() + " " + temp.getRetailPrice() + "€");
            temp = temp.getNextItem();
        }
    }



    public void OnBackButton() throws IOException {
        //loads case scene
        FXMLLoader caseView = new FXMLLoader(CaseViewController.class.getResource("case-view.fxml"));
        backButton.getScene().setRoot(caseView.load());
    }

}


