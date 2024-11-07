package org.example.fuelcalculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;


public class FuelCalculatorApp extends Application {

    private ResourceBundle bundle;
    private Label distanceLabel, fuelLabel, resultLabel;
    private TextField distanceField, fuelField;
    private Button calculateButton;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Mikko Manninen");

        // Initialize UI components first
        distanceLabel = new Label();
        distanceField = new TextField();
        fuelLabel = new Label();
        fuelField = new TextField();
        calculateButton = new Button();
        resultLabel = new Label();

        // Now load the language, which updates the label text
        loadLanguage("en"); // Default to English

        calculateButton.setOnAction(e -> calculateFuelConsumption());

        // Language buttons
        Button enButton = new Button("EN");
        Button frButton = new Button("FR");
        Button jpButton = new Button("JP");
        Button irButton = new Button("IR");

        enButton.setOnAction(e -> loadLanguage("en"));
        frButton.setOnAction(e -> loadLanguage("fr"));
        jpButton.setOnAction(e -> loadLanguage("ja"));
        irButton.setOnAction(e -> loadLanguage("ir"));

        // Set up layout
        VBox layout = new VBox(10, distanceLabel, distanceField, fuelLabel, fuelField, calculateButton, resultLabel, enButton, frButton, jpButton, irButton);
        Scene scene = new Scene(layout, 300, 400);
        stage.setScene(scene);
        stage.show();
    }


    private void loadLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        bundle = ResourceBundle.getBundle("message", locale);
        updateLabels();
    }

    private void updateLabels() {
        distanceLabel.setText(bundle.getString("distance.label"));
        fuelLabel.setText(bundle.getString("fuel.label"));
        calculateButton.setText(bundle.getString("calculate.button"));
        resultLabel.setText(""); // Clear previous result when language changes
    }

    private void calculateFuelConsumption() {
        try {
            double distance = Double.parseDouble(distanceField.getText());
            double fuel = Double.parseDouble(fuelField.getText());

            double consumption = (fuel / distance) * 100;
            resultLabel.setText(String.format(bundle.getString("result.label"), consumption));
        } catch (NumberFormatException e) {
            resultLabel.setText(bundle.getString("invalid.input"));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

