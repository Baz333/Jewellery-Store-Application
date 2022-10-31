package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;

public class CaseViewController {

    //variables from scene builder
    @FXML
    private ListView<String> trayList;
    @FXML
    private Button backButton;
    @FXML
    private Label caseNumberLabel;

    //stores a display tray to be loaded in another scene
    public static DisplayTray dt;



    public void initialize() {

        //imports display case to load in
        DisplayCase dc = StoreController.dc;

        //sets up label to show case uid
        caseNumberLabel.setText("Case " + dc.getUid());

        //list all trays in case
        DisplayTray temp = dc.getHead();
        while (temp != null) {
            trayList.getItems().add(temp.getUid() + ": " + temp.getInlayColour() + ", (" + temp.getDimensions()[0] + ", " + temp.getDimensions()[1] + ", " + temp.getDimensions()[2] + ")");
            temp = temp.getNextTray();
        }

        //drill down function
        //gets uid of selected tray, finds tray it belongs to, assigns it to static variable to be used in TrayViewController and loads tray scene
        trayList.setOnMouseClicked(click -> {
            if(click.getClickCount() == 2) {
                String uidSelected = trayList.getSelectionModel().getSelectedItem();
                uidSelected = uidSelected.substring(0, 3);
                DisplayTray temp2 = dc.getHead();
                while(temp2 != null) {
                    if(temp2.getUid().equals(uidSelected)) {
                        dt = temp2;
                        FXMLLoader trayScene = new FXMLLoader(CaseViewController.class.getResource("tray-view.fxml"));
                        try {
                            trayList.getScene().setRoot(trayScene.load());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    temp2 = temp2.getNextTray();
                }
            }
        });

    }



    public void OnBackButton() throws IOException {
        //loads store scene
        FXMLLoader storeView = new FXMLLoader(CaseViewController.class.getResource("store-view.fxml"));
        backButton.getScene().setRoot(storeView.load());
    }

}
