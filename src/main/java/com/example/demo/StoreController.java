package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;

public class StoreController {



    //variables from scene builder
    @FXML
    private ListView<String> caseList;
    @FXML
    private Button caseAddViewButton;
    @FXML
    private Button trayAddViewButton;
    @FXML
    private Button itemAddViewButton;
    @FXML
    private Button valueButton;
    @FXML
    private TextField searchText;
    @FXML
    private Button searchButton;

    //stores a display case to be loaded in another scene
    public static DisplayCase dc;
    //store a string to be searched in another scene
    public static String strToSearch;



    public void initialize() {

        //list all cases in store
        repopulate();

        //ref: https://stackoverflow.com/questions/22542015/how-to-add-a-mouse-doubleclick-event-listener-to-the-cells-of-a-listview-in-java
        //drill down function
        //gets uid of selected case, finds case it belongs to, assigns it to static variable to be used in CaseViewController, and loads case scene
        caseList.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                if (caseList.getSelectionModel().getSelectedItem() != null) {
                    String uidSelected = caseList.getSelectionModel().getSelectedItem();
                    //end of reference
                    uidSelected = uidSelected.substring(0, 3);
                    DisplayCase temp2 = MainApplication.head;
                    while (temp2 != null) {
                        if (temp2.getUid().equals(uidSelected)) {
                            dc = temp2;
                            FXMLLoader caseScene = new FXMLLoader(StoreController.class.getResource("case-view.fxml"));
                            try {
                                caseList.getScene().setRoot(caseScene.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        temp2 = temp2.getNextCase();
                    }
                }
            }
        });

    }



    //name of file to be stored in save/load function
    public String filename() {
        return "jewellery-store.xml";
    }

    //lists all cases in store
    public void repopulate() {
        caseList.getItems().clear();
        DisplayCase temp = MainApplication.head;
        while(temp != null) {
            caseList.getItems().add(temp.getUid() + ": " + temp.getType() + ", " + (temp.isLit() ? "lit" : "unlit") + " (" + temp.numberOfDisplayTrays() + " trays)");
            temp = temp.getNextCase();
        }
    }



    public void OnSearchButton() throws IOException {
        //assigns string to static variable to be used in SearchController
        strToSearch = searchText.getText();
        //loads search scene
        FXMLLoader searchScene = new FXMLLoader(StoreController.class.getResource("search-view.fxml"));
        searchButton.getScene().setRoot(searchScene.load());
    }

    public void OnCaseAddViewButton() throws IOException {
        //loads scene to add a display case
        FXMLLoader caseAddScene = new FXMLLoader(StoreController.class.getResource("case-add-view.fxml"));
        caseAddViewButton.getScene().setRoot(caseAddScene.load());
    }

    public void OnTrayAddViewButton() throws IOException {
        //loads scene to add a display tray
        FXMLLoader trayAddScene = new FXMLLoader(StoreController.class.getResource("tray-add-view.fxml"));
        trayAddViewButton.getScene().setRoot(trayAddScene.load());
    }

    public void OnItemAddViewButton() throws IOException {
        //loads scene to add a jewellery item
        FXMLLoader itemAddScene = new FXMLLoader(StoreController.class.getResource("item-add-view.fxml"));
        itemAddViewButton.getScene().setRoot(itemAddScene.load());
    }

    public void OnValueButton() throws IOException {
        //loads valuation scene
        FXMLLoader valueScene = new FXMLLoader(StoreController.class.getResource("value-stock-view.fxml"));
        valueButton.getScene().setRoot(valueScene.load());
    }

    public void OnResetButton() {
        //resets store, getting rid of all cases
        MainApplication.head = null;
        repopulate();
    }

    public void OnSaveButton() throws Exception {
        //saves all cases, trays, items + mat/coms
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename()));
        out.writeObject(MainApplication.head);
        out.close();
    }

    public void OnLoadButton() throws Exception {
        //loads all cases, trays, items + mat/coms
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename()));
        MainApplication.head = (DisplayCase) in.readObject();
        in.close();
        repopulate();
    }

}
