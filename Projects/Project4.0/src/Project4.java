import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Project4.java
 * CMSC 215 - Programming Project 4
 * Author: Rodney
 * Date: August 2, 2026
 *
 * A JavaFX GUI application, the Time Interval Checker, that lets a user
 * enter two time intervals and compare their relationship, or check
 * whether a given time falls within either interval.
 */
public class Project4 extends Application {

    // Text fields for entering the start and end of each interval
    private TextField interval1StartField;
    private TextField interval1EndField;
    private TextField interval2StartField;
    private TextField interval2EndField;

    // Text field for the time to check, and read-only output fields
    private TextField timeToCheckField;
    private TextField compareOutputField;
    private TextField checkOutputField;

    /**
     * Builds and displays the GUI.
     *
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        // Column headers
        grid.add(new Label("Start Time"), 1, 0);
        grid.add(new Label("End Time"), 2, 0);

        // Row for Time Interval 1
        grid.add(new Label("Time Interval 1"), 0, 1);
        interval1StartField = new TextField("10:30 AM");
        interval1EndField = new TextField("12:30 PM");
        grid.add(interval1StartField, 1, 1);
        grid.add(interval1EndField, 2, 1);

        // Row for Time Interval 2
        grid.add(new Label("Time Interval 2"), 0, 2);
        interval2StartField = new TextField("11:05 AM");
        interval2EndField = new TextField("1:00 PM");
        grid.add(interval2StartField, 1, 2);
        grid.add(interval2EndField, 2, 2);

        // Compare Intervals button and its output field
        Button compareButton = new Button("Compare Intervals");
        compareButton.setMaxWidth(Double.MAX_VALUE);
        grid.add(compareButton, 0, 3, 3, 1);

        compareOutputField = new TextField();
        compareOutputField.setEditable(false);
        grid.add(compareOutputField, 0, 4, 3, 1);

        // Time to Check row
        grid.add(new Label("Time to Check"), 0, 5);
        timeToCheckField = new TextField();
        grid.add(timeToCheckField, 1, 5, 2, 1);

        // Check Time button and its output field
        Button checkButton = new Button("Check Time");
        checkButton.setMaxWidth(Double.MAX_VALUE);
        grid.add(checkButton, 0, 6, 3, 1);

        checkOutputField = new TextField();
        checkOutputField.setEditable(false);
        grid.add(checkOutputField, 0, 7, 3, 1);

        // Wire up button actions
        compareButton.setOnAction(e -> handleCompareIntervals());
        checkButton.setOnAction(e -> handleCheckTime());

        Scene scene = new Scene(grid);
        primaryStage.setTitle("Time Interval Checker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Reads both intervals from the text fields, determines their
     * relationship (subinterval, overlap, or disjoint), and displays
     * the result. Displays an error message if any time is invalid.
     */
    private void handleCompareIntervals() {
        try {
            Interval<Time> interval1 = buildInterval(interval1StartField, interval1EndField);
            Interval<Time> interval2 = buildInterval(interval2StartField, interval2EndField);

            String result;
            if (interval2.subinterval(interval1)) {
                result = "Interval 1 is a sub-interval of interval 2";
            } else if (interval1.subinterval(interval2)) {
                result = "Interval 2 is a sub-interval of interval 1";
            } else if (interval1.overlaps(interval2)) {
                result = "The intervals overlap";
            } else {
                result = "The intervals are disjoint";
            }
            compareOutputField.setText(result);
        } catch (InvalidTime ex) {
            compareOutputField.setText("Error: " + ex.getMessage());
        }
    }

    /**
     * Reads both intervals and the time to check from the text fields,
     * determines which interval(s) contain that time, and displays the
     * result. Displays an error message if any time is invalid.
     */
    private void handleCheckTime() {
        try {
            Interval<Time> interval1 = buildInterval(interval1StartField, interval1EndField);
            Interval<Time> interval2 = buildInterval(interval2StartField, interval2EndField);
            Time timeToCheck = new Time(timeToCheckField.getText());

            boolean inInterval1 = interval1.within(timeToCheck);
            boolean inInterval2 = interval2.within(timeToCheck);

            String result;
            if (inInterval1 && inInterval2) {
                result = "Both intervals contain the time " + timeToCheck;
            } else if (inInterval1) {
                result = "Only interval 1 contains the time " + timeToCheck;
            } else if (inInterval2) {
                result = "Only interval 2 contains the time " + timeToCheck;
            } else {
                result = "Neither interval contains the time " + timeToCheck;
            }
            checkOutputField.setText(result);
        } catch (InvalidTime ex) {
            checkOutputField.setText("Error: " + ex.getMessage());
        }
    }

    /**
     * Helper method that builds an Interval&lt;Time&gt; from a pair of
     * text fields containing start and end time strings.
     *
     * @param startField the text field holding the start time
     * @param endField   the text field holding the end time
     * @return the constructed Interval
     * @throws InvalidTime if either time string is invalid
     */
    private Interval<Time> buildInterval(TextField startField, TextField endField) throws InvalidTime {
        Time start = new Time(startField.getText());
        Time end = new Time(endField.getText());
        return new Interval<>(start, end);
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        launch(args);
    }
}