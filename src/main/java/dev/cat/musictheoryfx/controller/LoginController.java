package dev.cat.musictheoryfx.controller;

import dev.cat.musictheoryfx.config.FxmlView;
import dev.cat.musictheoryfx.config.StageManager;
import dev.cat.musictheoryfx.event.LoginEvent;
import dev.cat.musictheoryfx.user.User;
import dev.cat.musictheoryfx.user.UserService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class LoginController implements Initializable {

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField userName;

    @FXML
    private Label loginErrorLabel;

    StringProperty errorProperty = new SimpleStringProperty();

    @FXML
    private ImageView gClef;

    private final StageManager stageManager;

    private final UserService userService;

    private final ApplicationEventPublisher eventPublisher;

    @Lazy
    public LoginController(StageManager stageManager, UserService userService, ApplicationEventPublisher eventPublisher) {
        this.stageManager = stageManager;
        this.userService = userService;
        this.eventPublisher = eventPublisher;
    }


    public void loadUserAndOpenHomePage() {
        Optional<User> user = userService.findByUsername(userName.getText());
        if (user.isEmpty()) {
            errorProperty.setValue(
                    "No user with this name found.");
        } else stageManager.switchToNextScene(FxmlView.HOME);

    }


    public void saveUserAndOpenHomePage() {

        String name = userName.getText();

        Optional<User> user = userService.findByUsername(name);
        if (user.isPresent()) {
            errorProperty.setValue(
                    "User with this name already exists! Sign in or use another name.");

            if (name.length() > 20) {
                errorProperty.setValue(
                        "Username must not be longer that 20 characters");
            }
        } else {
            userService.saveUser(name);
            stageManager.switchToNextScene(FxmlView.HOME);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gClef.setFitWidth(1000);
        gClef.setPreserveRatio(true);
        gClef.setSmooth(true);

        loginErrorLabel.textProperty().bind(errorProperty);

        userName.textProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldText,
                                String newText) {
                eventPublisher.publishEvent(new LoginEvent(this, newText));
            }
        });

    }
}
