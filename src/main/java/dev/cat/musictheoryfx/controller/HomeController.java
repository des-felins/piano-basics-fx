package dev.cat.musictheoryfx.controller;

import dev.cat.musictheoryfx.config.FxmlView;
import dev.cat.musictheoryfx.config.StageManager;
import dev.cat.musictheoryfx.event.LoginEvent;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
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

    @FXML
    private Label helloLabel;

    @FXML
    private ImageView gClef;

    private final StageManager stageManager;

    StringProperty nameProperty = new SimpleStringProperty();

    @Lazy
    public HomeController(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gClef.setFitWidth(1000);
        gClef.setPreserveRatio(true);
        gClef.setSmooth(true);

        helloLabel.textProperty().bind(nameProperty);

    }

    @FXML
    void switchToTheoryScene() {
        stageManager.switchToNextScene(FxmlView.SCALES_THEORY);
    }

    @FXML
    void switchToScalesScene() {
        stageManager.switchToNextScene(FxmlView.SCALES);
    }

    @FXML
    void switchToChordsScene() {

    }

    @FXML
    public void switchToChordGeneratorScene() {
    }

    @EventListener
    public void handleLoginEvent(LoginEvent event) {
        nameProperty.setValue("Hello, " + event.getUserName() + "!");
    }

}
