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
        int octaveBlockNumber = 0;
        boolean isWhite = true;
        switch (key) {
            case Q -> {
                soundFile = "/sound/C3.aiff";
                keyNumber = 1;
            }
            case DIGIT2 -> {
                soundFile = "/sound/Db3.aiff";
                keyNumber = 0;
                isWhite = false;
                octaveBlockNumber = 1;
            }
            case W -> {
                soundFile = "/sound/D3.aiff";
                keyNumber = 2;
            }
            case DIGIT3 -> {
                soundFile = "/sound/Eb3.aiff";
                keyNumber = 1;
                isWhite = false;
                octaveBlockNumber = 1;
            }
            case E -> {
                soundFile = "/sound/E3.aiff";
                keyNumber = 3;
            }
            case R -> {
                soundFile = "/sound/F3.aiff";
                keyNumber = 4;
            }
            case DIGIT5 -> {
                soundFile = "/sound/Gb3.aiff";
                keyNumber = 3;
                isWhite = false;
                octaveBlockNumber = 1;
            }
            case T -> {
                soundFile = "/sound/G3.aiff";
                keyNumber = 5;
            }
            case DIGIT6 -> {
                soundFile = "/sound/Ab3.aiff";
                keyNumber = 4;
                isWhite = false;
                octaveBlockNumber = 1;
            }
            case Y -> {
                soundFile = "/sound/A3.aiff";
                keyNumber = 6;
            }
            case DIGIT7 -> {
                soundFile = "/sound/Bb3.aiff";
                keyNumber = 5;
                isWhite = false;
                octaveBlockNumber = 1;
            }
            case U -> {
                soundFile = "/sound/B3.aiff";
                keyNumber = 7;
            }
            case I -> {
                soundFile = "/sound/C4.aiff";
                keyNumber = 8;
            }
            case DIGIT9 -> {
                soundFile = "/sound/Db4.aiff";
                keyNumber = 0;
                isWhite = false;
                octaveBlockNumber = 2;
            }
            case O -> {
                soundFile = "/sound/D4.aiff";
                keyNumber = 9;
            }
            case DIGIT0 -> {
                soundFile = "/sound/Eb4.aiff";
                keyNumber = 1;
                isWhite = false;
                octaveBlockNumber = 2;
            }
            case P -> {
                soundFile = "/sound/E4.aiff";
                keyNumber = 10;
            }
            case BACK_QUOTE -> {
                soundFile = "/sound/F4.aiff";
                keyNumber = 11;
            }
            case A -> {
                soundFile = "/sound/Gb4.aiff";
                keyNumber = 3;
                isWhite = false;
                octaveBlockNumber = 2;
            }
            case Z -> {
                soundFile = "/sound/G4.aiff";
                keyNumber = 12;
            }
            case S -> {
                soundFile = "/sound/Ab4.aiff";
                keyNumber = 4;
                isWhite = false;
                octaveBlockNumber = 2;
            }
            case X -> {
                soundFile = "/sound/A4.aiff";
                keyNumber = 13;
            }
            case D -> {
                soundFile = "/sound/Bb4.aiff";
                keyNumber = 5;
                isWhite = false;
                octaveBlockNumber = 2;
            }
            case C -> {
                soundFile = "/sound/B4.aiff";
                keyNumber = 14;
            }
            case V -> {
                soundFile = "/sound/C5.aiff";
                keyNumber = 15;
            }
            case G -> {
                soundFile = "/sound/Db5.aiff";
                keyNumber = 0;
                isWhite = false;
                octaveBlockNumber = 3;
            }
            case B -> {
                soundFile = "/sound/D5.aiff";
                keyNumber = 16;
            }
            case H -> {
                soundFile = "/sound/Eb5.aiff";
                keyNumber = 1;
                isWhite = false;
                octaveBlockNumber = 3;
            }
            case N -> {
                soundFile = "/sound/E5.aiff";
                keyNumber = 17;
            }
            case M -> {
                soundFile = "/sound/F5.aiff";
                keyNumber = 18;
            }
            case K -> {
                soundFile = "/sound/Gb5.aiff";
                keyNumber = 3;
                isWhite = false;
                octaveBlockNumber = 3;
            }
            case COMMA -> {
                soundFile = "/sound/G5.aiff";
                keyNumber = 19;
            }
            case L -> {
                soundFile = "/sound/Ab5.aiff";
                keyNumber = 4;
                isWhite = false;
                octaveBlockNumber = 3;
            }
            case PERIOD -> {
                soundFile = "/sound/A5.aiff";
                keyNumber = 20;
            }
            case SEMICOLON -> {
                soundFile = "/sound/Bb5.aiff";
                keyNumber = 5;
                isWhite = false;
                octaveBlockNumber = 3;
            }
            case SLASH -> {
                soundFile = "/sound/B5.aiff";
                keyNumber = 21;
            }
        }

        playKeySound(soundFile);
        keyboard.redraw(keyNumber, isWhite, octaveBlockNumber);


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
