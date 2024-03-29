package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.function.UnaryOperator;

public class CaseController {



    //variables from scene builder
    @FXML
    private TextField uidText;
    @FXML
    private ChoiceBox<String> typeChoice;
    @FXML
    private CheckBox litCheck;
    @FXML
    private Button addCaseButton;
    @FXML
    private Button backButton;



    public void initialize() {

        //fill choicebox for choosing type of display case
        typeChoice.getItems().addAll("freestanding", "wall-mounted");

        //ref: https://stackoverflow.com/questions/15159988/javafx-2-2-textfield-maxlength
        //limits characters in textfield
        //changed so that it keeps the first three characters entered rather than replacing the oldest one
        UnaryOperator<TextFormatter.Change> modifyChange = c -> {
            if (c.isContentChange()) {
                int newLength = c.getControlNewText().length();
                if (newLength > 3) {
                    String head = c.getControlNewText().substring(0, 3);
                    c.setText(head);
                    int oldLength = c.getControlText().length();
                    c.setRange(0, oldLength);
                }
            }
            return c;
        };
        uidText.setTextFormatter(new TextFormatter(modifyChange));

        //ref: https://www.codegrepper.com/code-examples/whatever/javafx+textfield+only+numbers
        //only digits 0-9 allowed in textfield
        //changed slightly to be more readable
        uidText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-9]*")) {
                uidText.setText(newValue.replaceAll("[^[0-9]]", ""));
            }
        });
        //end of references

    }



    public void OnCaseAddButton() throws IOException {

        //if the id is unique and all mandatory choices are filled out
        if(uniqueUid() && getTypeChoice() != null && getUidText().length() == 3) {

            //add display case to linked list
            DisplayCase dc = new DisplayCase(null, null, getUidText(), getTypeChoice(), getLitCheck());
            dc.setNextCase(MainApplication.head);
            MainApplication.head = dc;


            //list to test if display case was added
            DisplayCase temp = MainApplication.head;
            while (temp != null) {
                System.out.println(temp.getUid() + ": " + temp.getType() + ", " + temp.isLit());
                temp = temp.getNextCase();
            }
            System.out.println();

            //load store view scene
            FXMLLoader storeScene = new FXMLLoader(CaseController.class.getResource("store-view.fxml"));
            addCaseButton.getScene().setRoot(storeScene.load());

        }

    }

    public void OnBackButton() throws IOException {
        //loads store scene
        FXMLLoader storeScene = new FXMLLoader(CaseController.class.getResource("store-view.fxml"));
        backButton.getScene().setRoot(storeScene.load());
    }



    private boolean uniqueUid() {
        //checks if any other display case has the same uid as was inputted
        //returns true is no other display case has the same uid, false if one does
        DisplayCase temp = MainApplication.head;
        while(temp != null) {
            if(getUidText().equals(temp.getUid())) {
                return false;
            }
            temp = temp.getNextCase();
        }
        return true;
    }



    //getters
    public String getUidText() {
        return uidText.getText();
    }

    public String getTypeChoice() {
        return typeChoice.getValue();
    }

    public boolean getLitCheck() {
        return litCheck.isSelected();
    }

}