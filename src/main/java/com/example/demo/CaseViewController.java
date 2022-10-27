package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;

public class CaseViewController {

    @FXML
    private ListView<String> trayList;
    @FXML
    private Button backButton;
    @FXML
    private Label caseNumberLabel;

    public static DisplayTray dt;



    public void initialize() {

        DisplayCase dc = StoreController.dc;
        caseNumberLabel.setText("Case " + dc.getUid());
        DisplayTray temp = dc.getHead();
        while (temp != null) {
            trayList.getItems().add(temp.getUid() + ": " + temp.getInlayColour() + ", (" + temp.getDimensions()[0] + ", " + temp.getDimensions()[1] + ", " + temp.getDimensions()[2] + ")");
            temp = temp.getNextTray();
        }

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
        FXMLLoader storeView = new FXMLLoader(CaseViewController.class.getResource("store-view.fxml"));
        backButton.getScene().setRoot(storeView.load());
    }

}
