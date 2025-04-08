package dev.cat.musictheoryfx.controller;

import dev.cat.musictheoryfx.config.FxmlView;
import dev.cat.musictheoryfx.config.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class HomeController implements Initializable {

    @FXML
    private Button theoryButton;

    @FXML
    private Button scalesButton;

    @FXML
    private Button chordsButton;

    private final StageManager stageManager;

    @Lazy
    public HomeController(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void switchToTheoryScene() {

    }

    @FXML
    void switchToScalesScene() {
        stageManager.switchToNextScene(FxmlView.SCALES);
    }

    @FXML
    void switchToChordsScene() {

    }

    @FXML
    public void switchToChordGeneratorScene(ActionEvent event) {
    }
}
