package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;

public class SearchController {

    @FXML
    private ListView<String> searchList;
    @FXML
    private Button backButton;
    public static JewelleryItem ji;

    public void initialize() {

        String strToSearch = StoreController.strToSearch;
        DisplayCase temp = MainApplication.head;
        while(temp != null) {
            DisplayTray temp2 = temp.getHead();
            while(temp2 != null) {
                JewelleryItem temp3 = temp2.getHead();
                int i = 0;
                while(temp3 != null) {
                    String str = "[" + temp.getUid() + ", " + temp2.getUid() + ": " + i + "] " + temp3.getType() + " (" + temp3.getTargetGender() + "), " + temp3.getItemDescription() + " " + temp3.getRetailPrice() + "€";
                    if(str.contains(strToSearch) || temp3.materialsToString().contains(strToSearch)) {
                        searchList.getItems().add(str);
                    }
                    i++;
                    temp3 = temp3.getNextItem();
                }
                temp2 = temp2.getNextTray();
            }
            temp = temp.getNextCase();
        }

        searchList.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                if (searchList.getSelectionModel().getSelectedItem() != null) {
                    String itemSelected = searchList.getSelectionModel().getSelectedItem();
                    DisplayCase temp2 = MainApplication.head;
                    while (temp2 != null) {
                        if (itemSelected.substring(1, 4).equals(temp2.getUid())) {
                            DisplayTray temp3 = temp2.getHead();
                            while(temp3 != null) {
                                if(itemSelected.substring(6, 9).equals(temp3.getUid())) {
                                    JewelleryItem temp4 = temp3.getHead();
                                    int i = Integer.parseInt(itemSelected.substring(11, 12));
                                    for(int j = 0; j < i; j++) {
                                        temp4 = temp4.getNextItem();
                                    }
                                    ji = temp4;
                                }
                                temp3 = temp3.getNextTray();
                            }
                        }
                        temp2 = temp2.getNextCase();
                    }
                    ItemViewController.drillDown = false;
                    FXMLLoader itemScene = new FXMLLoader(SearchController.class.getResource("item-view.fxml"));
                    try {
                        searchList.getScene().setRoot(itemScene.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public void OnBackButton() throws IOException {
        FXMLLoader storeScene = new FXMLLoader(SearchController.class.getResource("store-view.fxml"));
        backButton.getScene().setRoot(storeScene.load());
    }

}
