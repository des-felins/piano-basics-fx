package dev.cat.musictheoryfx.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IntervalsController implements Initializable {


    @FXML
    private Button showNotesButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button playAgainButton;
    @FXML
    private Label responseLabel;
    @FXML
    private ComboBox<String> comboBox;

    StringProperty responseProperty = new SimpleStringProperty();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateComboBox();

    }

    private void populateComboBox() {
        String[] intervals = {"Perfect Unison (P1)",
                "Minor Second (m2)",
                "Major Second (M2)",
                "Minor Third (m3)",
                "Major Third (M3)",
                "Perfect Fourth (P4)",
                "Triton (aug4/dim5)",
                "Perfect Fifth (P5)",
                "Minor Sixth (m6)",
                "Major Sixth (M6)",
                "Minor Seventh (m7)",
                "Major Seventh (M7)", "Perfect Octave (P8)"};

        comboBox.getItems().addAll(intervals);

        responseLabel.textProperty().bind(responseProperty);

    }

    @FXML
    public void showNotes(ActionEvent actionEvent) {

    }

    @FXML
    public void getNextInterval(ActionEvent actionEvent) {

    }

    @FXML
    public void playAgain(ActionEvent actionEvent) {

    }

    @FXML
    public void submitAnswer(ActionEvent actionEvent) {
        String data = comboBox.getValue();
        responseProperty.setValue(data);
    }
}
