package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.function.UnaryOperator;

public class TrayController {



    @FXML
    private TextField uidText;
    @FXML
    private ChoiceBox<String> caseIDChoice;
    @FXML
    private ComboBox<String> colourCombo;
    @FXML
    private TextField lengthText;
    @FXML
    private TextField widthText;
    @FXML
    private TextField heightText;
    @FXML
    private Button addTrayButton;
    @FXML
    private Button backButton;



    public void initialize() {

        DisplayCase temp = MainApplication.head;
        while(temp != null) {
            caseIDChoice.getItems().add(temp.getUid());
            temp = temp.getNextCase();
        }
        colourCombo.getItems().addAll("black", "white", "red", "yellow", "green", "blue", "purple", "orange");

        UnaryOperator<TextFormatter.Change> modifyChange = c -> {
            if (c.isContentChange()) {
                int oldLength = c.getControlText().length();
                int newLength = c.getControlNewText().length();
                if(newLength == 1) {
                    if(!(c.getControlNewText().charAt(0) >= 65 && c.getControlNewText().charAt(0) <= 90)) {
                        String head = c.getControlNewText().substring(0, 0);
                        c.setText(head);
                        c.setRange(0, oldLength);
                    }
                } else if(newLength == 2) {
                    if(!(c.getControlNewText().charAt(1) >= 48 && c.getControlNewText().charAt(1) <= 57)) {
                        String head = c.getControlNewText().substring(0, newLength - 1);
                        c.setText(head);
                        c.setRange(0, oldLength);
                    }
                } else if(newLength == 3) {
                    if(!(c.getControlNewText().charAt(2) >= 48 && c.getControlNewText().charAt(2) <= 57)) {
                        String head = c.getControlNewText().substring(0, newLength - 1);
                        c.setText(head);
                        c.setRange(0, oldLength);
                    }
                }
                if (newLength > 3) {
                    String head = c.getControlNewText().substring(0, newLength - 1);
                    c.setText(head);
                    c.setRange(0, oldLength);
                }
            }
            return c;
        };
        uidText.setTextFormatter(new TextFormatter(modifyChange));

    }



    public void OnAddTrayButton() throws IOException {

        //check for unique id and all fields filled out correctly
        if(getCaseFromUID() != null && uniqueUID() && getUidText().length() == 3 && getCaseIDChoice() != null && getColourCombo() != null && getLengthText() != 0 && getWidthText() != 0 && getHeightText() != 0) {

            //add display tray to linked list
            DisplayTray dt = new DisplayTray(null, null, getCaseFromUID(), getUidText(), getColourCombo(), getLengthText(), getWidthText(), getHeightText());
            dt.setNextTray(getCaseFromUID().getHead());
            getCaseFromUID().setHead(dt);

            //list to test if display tray was added
            DisplayTray temp = getCaseFromUID().getHead();
            while (temp != null) {
                System.out.println(temp.getUid() + ": " + temp.getInlayColour() + ", (" + temp.getDimensions()[0] + ", " + temp.getDimensions()[1] + ", " + temp.getDimensions()[2] + ")");
                temp = temp.getNextTray();
            }
            System.out.println();

            //load store view scene
            FXMLLoader storeScene = new FXMLLoader(TrayController.class.getResource("store-view.fxml"));
            addTrayButton.getScene().setRoot(storeScene.load());

        }

    }

    public void OnBackButton() throws IOException {
        FXMLLoader storeScene = new FXMLLoader(TrayController.class.getResource("store-view.fxml"));
        backButton.getScene().setRoot(storeScene.load());
    }



    private boolean uniqueUID() {
        DisplayCase temp = MainApplication.head;
        while(temp != null) {
            DisplayTray temp2 = temp.getHead();
            while(temp2 != null) {
                if(getUidText().equals(temp2.getUid())) {
                    return false;
                }
                temp2 = temp2.getNextTray();
            }
            temp = temp.getNextCase();
        }
        return true;
    }



    public String getUidText() {
        return uidText.getText();
    }

    public String getCaseIDChoice() {
        return caseIDChoice.getValue();
    }

    public DisplayCase getCaseFromUID() {
        DisplayCase temp = MainApplication.head;
        while(temp != null) {
            if(temp.getUid().equals(getCaseIDChoice())) {
                return temp;
            }
            temp = temp.getNextCase();
        }
        return null;
    }

    public String getColourCombo() {
        return colourCombo.getValue();
    }

    public int getLengthText() {
        if(lengthText.getText().length() > 0) {
            return Integer.parseInt(lengthText.getText());
        }
        return 0;
    }

    public int getWidthText() {
        if(widthText.getText().length() > 0) {
            return Integer.parseInt(widthText.getText());
        }
        return 0;
    }

    public int getHeightText() {
        if(heightText.getText().length() > 0) {
            return Integer.parseInt(heightText.getText());
        }
        return 0;
    }



}
