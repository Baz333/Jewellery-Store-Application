package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ComponentController {

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

    JewelleryItem ji = TrayViewController.ji;

    public void initialize() {
        nameChoice.getItems().addAll("gold", "platinum", "diamond", "emerald", "silver", "other");
        qualityChoice.getItems().addAll("excellent", "very good", "good", "fair", "poor");

        weightText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-9]*")) {
                weightText.setText(newValue.replaceAll("[^[0-9]]", ""));
            }
        });
    }

    public void OnAddCompButton() throws IOException {

        if(getNameChoice() != null && getDescText() != null && getWeight() != 0 && getQualityChoice() != null) {

            MaterialComponent mc = new MaterialComponent(null, getNameChoice(), getDescText(), getWeight(), getQualityChoice());
            mc.setNextComponent(ji.getHead());
            ji.setHead(mc);

            MaterialComponent temp = ji.getHead();
            while(temp != null) {
                System.out.println(temp.getName() + " (" + temp.getDesc() + "), " + temp.getWeight() + " carats, " + temp.getQuality());
                temp = temp.getNextComponent();
            }
            System.out.println();

            FXMLLoader itemView = new FXMLLoader(ComponentController.class.getResource("item-view.fxml"));
            addCompButton.getScene().setRoot(itemView.load());

        }

    }

    public void OnBackButton() throws IOException {
        FXMLLoader itemView = new FXMLLoader(ComponentController.class.getResource("item-view.fxml"));
        backButton.getScene().setRoot(itemView.load());
    }

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