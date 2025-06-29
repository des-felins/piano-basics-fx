package dev.cat.musictheoryfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IntervalsController implements Initializable {


    @FXML
    public Button showNotesButton;
    @FXML
    public Button nextButton;

    @FXML
    public Button playAgainButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

}
