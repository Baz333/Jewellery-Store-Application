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

    private String filePath;



    public void initialize() {
        typeChoice.getItems().addAll("ring", "watch", "necklace", "pendant", "bracelet", "earring", "other");
        genderChoice.getItems().addAll("male", "female", "unisex");

        DisplayCase temp = MainApplication.head;
        while(temp != null) {
            caseIDChoice.getItems().add(temp.getUid());
            temp = temp.getNextCase();
        }

        priceText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-9]*")) {
                priceText.setText(newValue.replaceAll("[^[0-9]]", ""));
            }
        });
    }



    public void OnAddItemButton() throws IOException {

        //check all fields filled out correctly
        if(getDescText() != null && getRetailPrice() != 0 && getCaseIDChoice() != null && getTrayIDChoice() != null && getTypeChoice() != null && getGenderChoice() != null && getFilePath() != null) {

            //add jewellery item to linked list
            JewelleryItem ji = new JewelleryItem(null, null, getDescText(), getTypeChoice(), getGenderChoice(), getFilePath(), getRetailPrice());
            ji.setNextItem(getTrayFromUIDs().getHead());
            getTrayFromUIDs().setHead(ji);

            //list to test if jewellery item was added
            JewelleryItem temp = getTrayFromUIDs().getHead();
            while(temp != null) {
                System.out.println(temp.getItemDescription());
                temp = temp.getNextItem();
            }
            System.out.println();

            //load store view scene
            FXMLLoader storeView = new FXMLLoader(ItemController.class.getResource("store-view.fxml"));
            addItemButton.getScene().setRoot(storeView.load());

        }
    }

    public void OnTrayIDChoice() {
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
        FXMLLoader storeView = new FXMLLoader(ItemController.class.getResource("store-view.fxml"));
        backButton.getScene().setRoot(storeView.load());
    }



    private DisplayTray getTrayFromUIDs() {
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



    private String getDescText() {
        return descText.getText();
    }

    private int getRetailPrice() {
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
