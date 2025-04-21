package dev.cat.musictheoryfx.controller.ui;

import dev.cat.musictheoryfx.config.FxmlView;
import dev.cat.musictheoryfx.config.StageManager;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ToolbarController implements Initializable {

    @FXML
    private Button homeButton;

    @FXML
    private Button fullScreenButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button exitButton;

    private final StageManager stageManager;

    private static final PseudoClass maximizeIcon = PseudoClass.getPseudoClass("max");
    private static final PseudoClass minimizeIcon = PseudoClass.getPseudoClass("min");

    @Lazy
    public ToolbarController(StageManager stageManager) {

        this.stageManager = stageManager;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (stageManager.isStageFullScreen()) {
            setWindowedGraphicsAndAction();
        } else {
            setFullScreenGraphicsAndAction();
        }
    }


    public void goToHomeScreen() {
        stageManager.switchToNextScene(FxmlView.HOME);
    }

    public void goFullScreen() {
        stageManager.switchToFullScreenMode();
        setWindowedGraphicsAndAction();
    }


    public void goWindowed() {
        stageManager.switchToWindowedMode();
        setFullScreenGraphicsAndAction();

    }

    public void openUserSettings() {

    }

    public void quitApp() {
        stageManager.exit();
    }

    void setFullScreenGraphicsAndAction() {

        fullScreenButton.pseudoClassStateChanged(minimizeIcon, false);
        fullScreenButton.pseudoClassStateChanged(maximizeIcon, true);

        fullScreenButton.setOnAction(e -> goFullScreen());

    }

    public void setWindowedGraphicsAndAction() {

        fullScreenButton.pseudoClassStateChanged(maximizeIcon, false);
        fullScreenButton.pseudoClassStateChanged(minimizeIcon, true);

        fullScreenButton.setOnAction(e -> goWindowed());
    }

}
