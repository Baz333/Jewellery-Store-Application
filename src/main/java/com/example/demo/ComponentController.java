package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ComponentController {

    //variables from scene builder
    @FXML
    private ChoiceBox<String> nameChoice;
    @FXML
    private TextArea descText;
    @FXML
    private TextField weightText;
    @FXML
    private ChoiceBox<String> qualityChoice;
    @FXML
    private Button addCompButton;
    @FXML
    private Button backButton;

    //reference to the jewellery item to add material/component to
    private JewelleryItem ji;

    public void initialize() {

        //if this scene was reached through drilling down, ji references the jewellery item in tray-view.fxml
        if(ItemViewController.drillDown) {
            ji = TrayViewController.ji;
        } else { //else ji references the jewellery item in search-view.fxml
            ji = SearchController.ji;
        }

        //fills choiceboxes for the component name and quality
        nameChoice.getItems().addAll("gold", "platinum", "diamond", "emerald", "silver", "other");
        qualityChoice.getItems().addAll("excellent", "very good", "good", "fair", "poor");

        //only numbers can be inputted into the weight textfield
        weightText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-9]*")) {
                weightText.setText(newValue.replaceAll("[^[0-9]]", ""));
            }
        });

    }

    public void OnAddCompButton() throws IOException {

        //if all fields are filled out
        if(getNameChoice() != null && getDescText() != null && getWeight() != 0 && getQualityChoice() != null) {

            //add material/component to linked list
            MaterialComponent mc = new MaterialComponent(null, getNameChoice(), getDescText(), getWeight(), getQualityChoice());
            mc.setNextComponent(ji.getHead());
            ji.setHead(mc);

            //list to test if material/component was added
            MaterialComponent temp = ji.getHead();
            while(temp != null) {
                System.out.println(temp.getName() + " (" + temp.getDesc() + "), " + temp.getWeight() + " carats, " + temp.getQuality());
                temp = temp.getNextComponent();
            }
            System.out.println();

            //load item scene
            FXMLLoader itemView = new FXMLLoader(ComponentController.class.getResource("item-view.fxml"));
            addCompButton.getScene().setRoot(itemView.load());

        }

    }

    public void OnBackButton() throws IOException {
        //load item scene
        FXMLLoader itemView = new FXMLLoader(ComponentController.class.getResource("item-view.fxml"));
        backButton.getScene().setRoot(itemView.load());
    }



    //getters
    public String getNameChoice() {
        return nameChoice.getValue();
    }

    public String getDescText() {
        return descText.getText();
    }

    public int getWeight() {
        return Integer.parseInt(weightText.getText());
    }

    public String getQualityChoice() {
        return qualityChoice.getValue();
    }

}