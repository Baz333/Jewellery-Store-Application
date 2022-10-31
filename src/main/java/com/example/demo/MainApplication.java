package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {



    //stores the head of the display case linked list
    public static DisplayCase head = null;



    //loads store view
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("store-view.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load());
        stage.setTitle("Jewellery Store");
        stage.setScene(scene1);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }



}