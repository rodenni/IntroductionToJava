
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Project3.java
 * Author: Rodney Dennise
 * Date: July 21, 2026
 *
 * JavaFX GUI for the Trip Cost Estimator. Collects distance, gasoline
 * cost, gas mileage (each with a unit choice), trip length, hotel cost,
 * food cost, and attractions cost, then converts all inputs to Imperial
 * units and passes them to an immutable TripCost object to compute the
 * total trip cost.
 */
public class Project3 extends Application {

    // Conversion constants, per project spec -- declared as constants
    // rather than embedding magic numbers in the conversion logic.
    private static final double KILOMETERS_PER_MILE = 1.609347;
    private static final double LITERS_PER_GALLON = 3.78541178;

    // Input fields
    private TextField distanceField;
    private TextField gasolineCostField;
    private TextField gasMileageField;
    private TextField numberOfDaysField;
    private TextField hotelCostField;
    private TextField foodCostField;
    private TextField attractionsField;

    // Unit selectors
    private ComboBox<String> distanceUnitCombo;
    private ComboBox<String> gasolineCostUnitCombo;
    private ComboBox<String> gasMileageUnitCombo;

    // Output field (read-only)
    private TextField totalTripCostField;

    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        int row = 0;

        // Distance row
        grid.add(new Label("Distance:"), 0, row);
        distanceField = new TextField();
        grid.add(distanceField, 1, row);
        distanceUnitCombo = new ComboBox<>();
        distanceUnitCombo.getItems().addAll("miles", "kilometers");
        distanceUnitCombo.setValue("miles");
        grid.add(distanceUnitCombo, 2, row);
        row++;

        // Gasoline cost row
        grid.add(new Label("Gasoline Cost:"), 0, row);
        gasolineCostField = new TextField();
        grid.add(gasolineCostField, 1, row);
        gasolineCostUnitCombo = new ComboBox<>();
        gasolineCostUnitCombo.getItems().addAll("dollars/gal", "dollars/liter");
        gasolineCostUnitCombo.setValue("dollars/gal");
        grid.add(gasolineCostUnitCombo, 2, row);
        row++;

        // Gas mileage row
        grid.add(new Label("Gas Mileage:"), 0, row);
        gasMileageField = new TextField();
        grid.add(gasMileageField, 1, row);
        gasMileageUnitCombo = new ComboBox<>();
        gasMileageUnitCombo.getItems().addAll("miles/gallon", "kms/liter");
        gasMileageUnitCombo.setValue("miles/gallon");
        grid.add(gasMileageUnitCombo, 2, row);
        row++;

        // Number of days row
        grid.add(new Label("Number Of Days:"), 0, row);
        numberOfDaysField = new TextField();
        grid.add(numberOfDaysField, 1, row);
        row++;

        // Hotel cost row
        grid.add(new Label("Hotel Cost:"), 0, row);
        hotelCostField = new TextField();
        grid.add(hotelCostField, 1, row);
        row++;

        // Food cost row
        grid.add(new Label("Food Cost:"), 0, row);
        foodCostField = new TextField();
        grid.add(foodCostField, 1, row);
        row++;

        // Attractions row
        grid.add(new Label("Attractions:"), 0, row);
        attractionsField = new TextField();
        grid.add(attractionsField, 1, row);
        row++;

        // Calculate button
        Button calculateButton = new Button("Calculate");
        calculateButton.setOnAction(event -> calculateTripCost());
        grid.add(calculateButton, 1, row);
        row++;

        // Total trip cost row (read-only output)
        grid.add(new Label("Total Trip Cost:"), 0, row);
        totalTripCostField = new TextField();
        totalTripCostField.setEditable(false);
        grid.add(totalTripCostField, 1, row);

        Scene scene = new Scene(grid);
        primaryStage.setTitle("Trip Cost Estimator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Reads all input fields, converts any Metric values to Imperial
     * units using the unit chosen in each combo box, builds a TripCost
     * object with the converted values, and displays the result.
     */
    private void calculateTripCost() {
        try {
            // --- Distance: convert to miles if needed ---
            double distanceInput = Double.parseDouble(distanceField.getText());
            double distanceMiles = distanceUnitCombo.getValue().equals("kilometers")
                    ? distanceInput / KILOMETERS_PER_MILE
                    : distanceInput;

            // --- Gasoline cost: convert to dollars/gallon if needed ---
            double gasolineCostInput = Double.parseDouble(gasolineCostField.getText());
            double gasolineCostPerGallon = gasolineCostUnitCombo.getValue().equals("dollars/liter")
                    ? gasolineCostInput * LITERS_PER_GALLON
                    : gasolineCostInput;

            // --- Gas mileage: convert to miles/gallon if needed ---
            double gasMileageInput = Double.parseDouble(gasMileageField.getText());
            double gasMileageMpg = gasMileageUnitCombo.getValue().equals("kms/liter")
                    ? gasMileageInput * (LITERS_PER_GALLON / KILOMETERS_PER_MILE)
                    : gasMileageInput;

            // --- Remaining inputs have no unit choice ---
            int numberOfDays = Integer.parseInt(numberOfDaysField.getText());
            double hotelCostPerDay = Double.parseDouble(hotelCostField.getText());
            double foodCostPerDay = Double.parseDouble(foodCostField.getText());
            double attractionsCost = Double.parseDouble(attractionsField.getText());

            // Build the immutable TripCost object with fully-converted values
            TripCost trip = new TripCost(distanceMiles, gasMileageMpg, gasolineCostPerGallon,
                    numberOfDays, hotelCostPerDay, foodCostPerDay, attractionsCost);

            totalTripCostField.setText(currencyFormat.format(trip.getTotalTripCost()));
        } catch (NumberFormatException e) {
            totalTripCostField.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
