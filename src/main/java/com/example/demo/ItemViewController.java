package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class ItemViewController {

    @FXML
    private Label typeLabel;
    @FXML
    private Label descLabel;
    @FXML
    private Label caseLabel;
    @FXML
    private Label trayLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private ImageView itemImage;
    @FXML
    private ListView<String> compList;
    @FXML
    private Button backButton;
    @FXML
    private Button compButton;

    public void initialize() {

        DisplayCase dc = StoreController.dc;
        DisplayTray dt = CaseViewController.dt;
        JewelleryItem ji = TrayViewController.ji;

        String str = ji.getType();
        str = str.substring(0, 1).toUpperCase(Locale.ROOT) + str.substring(1);
        typeLabel.setText(str);
        descLabel.setText("Description: " + ji.getItemDescription());
        caseLabel.setText("Case ID: " + dc.getUid());
        trayLabel.setText("Tray ID: " + dt.getUid());
        str = ji.getTargetGender();
        str = str.substring(0, 1).toUpperCase(Locale.ROOT) + str.substring(1);
        genderLabel.setText("Target Gender: " + str);
        priceLabel.setText("Retail Price: " + ji.getRetailPrice() + "€");

        try {
            InputStream stream = new FileInputStream(ji.getImageURL());
            Image image = new Image(stream);
            itemImage.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MaterialComponent temp = ji.getHead();
        while(temp != null) {
            compList.getItems().add(temp.getName() + " (" + temp.getDesc() + "), " + temp.getWeight() + " carats, " + temp.getQuality());
            temp = temp.getNextComponent();
        }

    }

    public void OnBackButton() throws IOException {
        FXMLLoader trayView = new FXMLLoader(ItemViewController.class.getResource("tray-view.fxml"));
        backButton.getScene().setRoot(trayView.load());
    }

    public void OnCompButton() throws IOException {
        FXMLLoader compAddView = new FXMLLoader(ItemViewController.class.getResource("comp-add-view.fxml"));
        compButton.getScene().setRoot(compAddView.load());
    }

}
