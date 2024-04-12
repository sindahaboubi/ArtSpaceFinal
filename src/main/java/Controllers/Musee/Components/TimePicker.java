package Controllers.Musee.Components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.time.LocalTime;

public class TimePicker extends HBox {

    @FXML
    private TextField textField;

    private Spinner<Integer> hourSpinner;
    private Spinner<Integer> minuteSpinner;

    public TimePicker() {
        // Load the FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TimePicker.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        // Create hour spinner (0-23)
        hourSpinner = new Spinner<>(0, 23, 0);
        hourSpinner.setEditable(true);

        // Create minute spinner (0-59)
        minuteSpinner = new Spinner<>(0, 59, 0);
        minuteSpinner.setEditable(true);

        // Add value change listeners to update text field
        hourSpinner.valueProperty().addListener((observable, oldValue, newValue) -> updateTextField());
        minuteSpinner.valueProperty().addListener((observable, oldValue, newValue) -> updateTextField());

        // Set initial value for text field
        updateTextField();

        // Add spinners to the layout
        this.getChildren().addAll(hourSpinner, minuteSpinner);
    }

    private void updateTextField() {
        String hour = String.format("%02d", hourSpinner.getValue());
        String minute = String.format("%02d", minuteSpinner.getValue());
        textField.setText(hour + ":" + minute);
    }

    public String getTime() {
        return textField.getText();
    }
    /**
     * Get the selected time from the TimePicker.
     * @return The selected time as LocalTime object.
     */
    public LocalTime getLocalTime() {
        String time = getTime();
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return LocalTime.of(hours, minutes);
    }
    public void setTime(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        hourSpinner.getValueFactory().setValue(hours);
        minuteSpinner.getValueFactory().setValue(minutes);
        updateTextField();
    }
}
