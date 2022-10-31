package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ItemController {



    //variables from scene builder
    @FXML
    private TextArea descText;
    @FXML
    private TextField priceText;
    @FXML
    private ChoiceBox<String> caseIDChoice;
    @FXML
    private ChoiceBox<String> trayIDChoice;
    @FXML
    private ChoiceBox<String> typeChoice;
    @FXML
    private ChoiceBox<String> genderChoice;
    @FXML
    private Button fileButton;
    @FXML
    private Label fileLabel;
    @FXML
    private Button addItemButton;
    @FXML
    private ImageView imageView;
    @FXML
    private Button backButton;

    //variable for adding image
    private String filePath;



    public void initialize() {

        //fill type and gender choiceboxes
        typeChoice.getItems().addAll("ring", "watch", "necklace", "pendant", "bracelet", "earring", "other");
        genderChoice.getItems().addAll("male", "female", "unisex");

        //fill case id choicebox
        DisplayCase temp = MainApplication.head;
        while(temp != null) {
            caseIDChoice.getItems().add(temp.getUid());
            temp = temp.getNextCase();
        }

        //only numbers can be inputted into the price textfield
        priceText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-9]*")) {
                priceText.setText(newValue.replaceAll("[^[0-9]]", ""));
            }
        });

    }



    public void OnAddItemButton() throws IOException {

        //check all fields filled out correctly
        if (getTrayFromUIDs() != null && getDescText() != null && getRetailPrice() != 0 && getCaseIDChoice() != null && getTrayIDChoice() != null && getTypeChoice() != null && getGenderChoice() != null && getFilePath() != null) {

            //add jewellery item to linked list
            JewelleryItem ji = new JewelleryItem(null, null, getTrayFromUIDs(), getDescText(), getTypeChoice(), getGenderChoice(), getFilePath(), getRetailPrice());
            ji.setNextItem(getTrayFromUIDs().getHead());
            getTrayFromUIDs().setHead(ji);

            //list to test if jewellery item was added
            JewelleryItem temp = getTrayFromUIDs().getHead();
            while (temp != null) {
                System.out.println(temp.getItemDescription() + " " + temp.getParent().getUid());
                temp = temp.getNextItem();
            }
            System.out.println();

            //load store view scene
            FXMLLoader storeView = new FXMLLoader(ItemController.class.getResource("store-view.fxml"));
            addItemButton.getScene().setRoot(storeView.load());

        }

    }

    public void OnSmartAddButton() throws IOException {

        //if all fields (except case id and tray id) filled out correctly
        if (getDescText() != null && getRetailPrice() != 0 && getTypeChoice() != null && getGenderChoice() != null && getFilePath() != null) {

            DisplayCase temp = MainApplication.head;
            DisplayTray bestTray = temp.getHead();
            while(temp != null) {
                DisplayTray temp2 = temp.getHead();
                while(temp2 != null) {
                    //for each display tray instantiate 3 sets of 2 variables
                    //i keeps track of how many items in the current tray have the same target gender as the item being added
                    int i = 0;
                    //i2 keeps track of how many items in the best tray have the same target gender as the item being added
                    int i2 = 0;
                    //j keeps track of how many items in the current tray are the same type as the item being added
                    int j = 0;
                    //j2 keeps track of how many items in the best tray are the same type as the item being added
                    int j2 = 0;
                    //k keeps track of how many items in the current tray are within 50€ as the item being added
                    int k = 0;
                    //k2 keeps track of how many items in the best tray are within 50€ as the item being added
                    int k2 = 0;
                    JewelleryItem temp3 = temp2.getHead();
                    while(temp3 != null) { //cycles through current tray and finds out values of i, j, k
                        if(getGenderChoice().equals(temp3.getTargetGender())) {
                            i++;
                        }
                        if(getTypeChoice().equals(temp3.getType())) {
                            j++;
                        }
                        if(getRetailPrice() > temp3.getRetailPrice() - 50 && getRetailPrice() < temp3.getRetailPrice() + 50) {
                            k++;
                        }
                        temp3 = temp3.getNextItem();
                    }
                    temp3 = bestTray.getHead();
                    while(temp3 != null) { //cycles through best tray and finds out values of i2, j2, k2
                        if(getGenderChoice().equals(temp3.getTargetGender())) {
                            i2++;
                        }
                        if(getTypeChoice().equals(temp3.getType())) {
                            j2++;
                        }
                        if(getRetailPrice() > temp3.getRetailPrice() - 50 && getRetailPrice() < temp3.getRetailPrice() + 50) {
                            k2++;
                        }
                        temp3 = temp3.getNextItem();
                    }
                    //evaluates if current tray is better than best tray
                    if(i > i2) { //if current tray has more items of the same target gender, current tray becomes best tray
                        bestTray = temp2;
                    } else if(i == i2) { //if current and best trays have same amount, test j
                        if(j > j2) { //if current tray has more items of the same type, current tray becomes best tray
                            bestTray = temp2;
                        } else if(j == j2) { //if current and best trays have same amount, test k
                            if(k > k2) { //if current tray has more items of similar price, current tray becomes best tray (else, best tray stays same)
                                bestTray = temp2;
                            }
                        }
                    }
                    temp2 = temp2.getNextTray();
                }
                temp = temp.getNextCase();
            }

            System.out.println("Tray " + bestTray.getUid() + " identified as most suitable tray. Adding...");

            //add jewellery item to linked list
            JewelleryItem ji = new JewelleryItem(null, null, bestTray, getDescText(), getTypeChoice(), getGenderChoice(), getFilePath(), getRetailPrice());
            ji.setNextItem(bestTray.getHead());
            bestTray.setHead(ji);

            //list to test if jewellery item was added
            JewelleryItem temp2 = bestTray.getHead();
            while (temp2 != null) {
                System.out.println(temp2.getItemDescription() + " " + temp2.getParent().getUid());
                temp2 = temp2.getNextItem();
            }
            System.out.println();

            //load store view scene
            FXMLLoader storeView = new FXMLLoader(ItemController.class.getResource("store-view.fxml"));
            addItemButton.getScene().setRoot(storeView.load());


        }

    }

    public void OnTrayIDChoice() {
        //gets all tray uids for given case
        trayIDChoice.getItems().removeAll();
        DisplayCase temp = MainApplication.head;
        while(temp != null) {
            if(temp.getUid().equals(getCaseIDChoice())) {
                DisplayTray temp2 = temp.getHead();
                while(temp2 != null) {
                    trayIDChoice.getItems().add(temp2.getUid());
                    temp2 = temp2.getNextTray();
                }
                break;
            }
            temp = temp.getNextCase();
        }
    }

    public void OnFileButton() throws IOException {
        //adds image and saves filepath to private variable
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(fileButton.getScene().getWindow());
        if(selectedFile != null) {
            fileLabel.setText(selectedFile.getName());
            filePath = selectedFile.getAbsolutePath();
        }
        InputStream stream = new FileInputStream(filePath);
        Image image = new Image(stream);
        imageView.setImage(image);
    }

    public void OnBackButton() throws IOException {
        //loads store view
        FXMLLoader storeView = new FXMLLoader(ItemController.class.getResource("store-view.fxml"));
        backButton.getScene().setRoot(storeView.load());
    }



    private DisplayTray getTrayFromUIDs() {
        //returns tray given its uid
        DisplayCase temp = MainApplication.head;
        while(temp != null) {
            if(temp.getUid().equals(getCaseIDChoice())) {
                DisplayTray temp2 = temp.getHead();
                while(temp2 != null) {
                    if(temp2.getUid().equals(getTrayIDChoice())) {
                        return temp2;
                    }
                    temp2 = temp2.getNextTray();
                }
            }
            temp = temp.getNextCase();
        }
        return null;
    }



    //getters
    private String getDescText() {
        return descText.getText();
    }

    private int getRetailPrice() {
        if(priceText.getText().equals("")) {
            return 0;
        }
        return Integer.parseInt(priceText.getText());
    }

    private String getCaseIDChoice() {
        return caseIDChoice.getValue();
    }

    private String getTrayIDChoice() {
        return trayIDChoice.getValue();
    }

    private String getTypeChoice() {
        return typeChoice.getValue();
    }

    private String getGenderChoice() {
        return genderChoice.getValue();
    }

    private String getFilePath() {
        return filePath;
    }



}
