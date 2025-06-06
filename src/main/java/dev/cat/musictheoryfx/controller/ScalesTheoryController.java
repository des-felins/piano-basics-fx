package dev.cat.musictheoryfx.controller;

import dev.cat.musictheoryfx.controller.ui.ResizableCanvas;
import dev.cat.musictheoryfx.event.SceneResizeEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


@Component
public class ScalesTheoryController implements Initializable {

    @FXML
    private ResizableCanvas keyboard;
    private double width = 1000;

    EventHandler<KeyEvent> keyPressListener = this::keyPressed;
    EventHandler<KeyEvent> keyReleaseListener = this::keyReleased;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        keyboard.sceneProperty().addListener((observableValue,
                                              oldScene,
                                              newScene) -> {
            if (newScene != null) {
                newScene.addEventHandler(KeyEvent.KEY_PRESSED, keyPressListener);
            }
        });

        keyboard.sceneProperty().addListener((observableValue,
                                              oldScene,
                                              newScene) -> {
            if (newScene != null) {
                newScene.addEventHandler(KeyEvent.KEY_RELEASED, keyReleaseListener);
            }
        });

        keyboard.draw(width);
    }

    private void keyPressed(KeyEvent e) {
        String soundFile = "";
        KeyCode key = e.getCode();
        int keyNumber = 0;
        boolean isWhite = true;
        switch (key) {
            case Q -> {
                soundFile = "/sound/C3.aiff";
                keyNumber = 1;
            }
            case DIGIT2 -> soundFile = "/sound/Db3.aiff";
            case W -> soundFile = "/sound/D3.aiff";
            case DIGIT3 -> soundFile = "/sound/Eb3.aiff";
            case E -> soundFile = "/sound/E3.aiff";
            case R -> soundFile = "/sound/F3.aiff";
            case DIGIT5 -> soundFile = "/sound/Gb3.aiff";
            case T -> soundFile = "/sound/G3.aiff";
            case DIGIT6 -> soundFile = "/sound/Ab3.aiff";
            case Y -> soundFile = "/sound/A3.aiff";
            case DIGIT7 -> soundFile = "/sound/Bb3.aiff";
            case U -> soundFile = "/sound/B3.aiff";
            case I -> soundFile = "/sound/C4.aiff";
            case DIGIT9 -> soundFile = "/sound/Db4.aiff";
            case O -> soundFile = "/sound/D4.aiff";
            case DIGIT0 -> soundFile = "/sound/Eb4.aiff";
            case P -> soundFile = "/sound/E4.aiff";
            case BACK_QUOTE -> soundFile = "/sound/F4.aiff";
            case A -> soundFile = "/sound/Gb4.aiff";
            case Z -> soundFile = "/sound/G4.aiff";
            case S -> soundFile = "/sound/Ab4.aiff";
            case X -> soundFile = "/sound/A4.aiff";
            case D -> soundFile = "/sound/Bb4.aiff";
            case C -> soundFile = "/sound/B4.aiff";
            case V -> soundFile = "/sound/C5.aiff";
            case G -> soundFile = "/sound/Db5.aiff";
            case B -> soundFile = "/sound/D5.aiff";
            case H -> soundFile = "/sound/Eb5.aiff";
            case N -> soundFile = "/sound/E5.aiff";
            case M -> soundFile = "/sound/F5.aiff";
            case K -> soundFile = "/sound/Gb5.aiff";
            case COMMA -> soundFile = "/sound/G5.aiff";
            case L -> soundFile = "/sound/Ab5.aiff";
            case PERIOD -> soundFile = "/sound/A5.aiff";
            case SEMICOLON -> soundFile = "/sound/Bb5.aiff";
            case SLASH -> soundFile = "/sound/B5.aiff";
        }

        playKeySound(soundFile);
        keyboard.redraw(keyNumber, isWhite);
        System.out.println(keyNumber);


    }

    private void playKeySound(String soundFile) {
        String path = Objects.requireNonNull(
                        getClass().getResource(soundFile))
                .toExternalForm();
        AudioClip sound = new AudioClip(path);
        sound.play();

    }

    private void keyReleased(KeyEvent keyEvent) {
        keyboard.draw(width);
    }

    @EventListener
    public void handleSceneResizeEvent(SceneResizeEvent event) {
        width = event.getSceneWidth().doubleValue() - 10;

        if (keyboard != null) {
            keyboard.draw(width);
        }

    }


}
