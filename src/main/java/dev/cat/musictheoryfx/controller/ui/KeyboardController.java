package dev.cat.musictheoryfx.controller.ui;

import dev.cat.musictheoryfx.event.HintEvent;
import dev.cat.musictheoryfx.event.SceneResizeEvent;
import dev.cat.musictheoryfx.event.ShowHidePlayedKeysEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.*;

@Component
public class KeyboardController implements Initializable {

    @FXML
    public ResizableCanvas keyboard;
    @FXML
    public CheckBox showKeys;
    private double width = 1000;

    EventHandler<KeyEvent> keyPressListener = this::keyPressed;
    EventHandler<KeyEvent> keyReleaseListener = this::keyReleased;

    private final List<Key> keys = new ArrayList<>();
    private final Set<KeyCode> pressedKeys = new HashSet<>();
    private final Map<KeyCode, KeyInfo> keyInfos = new HashMap<>();

    private final ApplicationEventPublisher eventPublisher;

    boolean drawWithKeys = false;

    public final SoundBuilder soundBuilder = SoundBuilder.getInstance();

    public KeyboardController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fillKeySounds();

        showKeys.selectedProperty().addListener(new ChangeListener<>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                                Boolean oldProperty,
                                Boolean newProperty) {
                eventPublisher.publishEvent(new HintEvent(this, newProperty));
            }
        });

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

        keyboard.draw(width, drawWithKeys);
    }

    private void keyPressed(KeyEvent e) {

        for(Key key : keys) {
            if (key.keyCode().equals(e.getCode())) {
                pressedKeys.add(key.keyCode());
            }
        }

        drawAndPlay();

    }

    private void drawAndPlay() {

        for (KeyCode key : pressedKeys) {

            int keyNumber = 0;
            int octaveBlockNumber = 0;

            //1 for white key, 2 for black key
            int keyType = 1;

            playKeySound(key);

            switch (key) {
                case Q -> keyNumber = 1;
                case DIGIT2 -> {
                    keyType = 2;
                    octaveBlockNumber = 1;
                }
                case W -> keyNumber = 2;
                case DIGIT3 -> {
                    keyNumber = 1;
                    keyType = 2;
                    octaveBlockNumber = 1;
                }
                case E -> keyNumber = 3;
                case R -> keyNumber = 4;
                case DIGIT5 -> {
                    keyNumber = 3;
                    keyType = 2;
                    octaveBlockNumber = 1;
                }
                case T -> keyNumber = 5;
                case DIGIT6 -> {
                    keyNumber = 4;
                    keyType = 2;
                    octaveBlockNumber = 1;
                }
                case Y -> keyNumber = 6;
                case DIGIT7 -> {
                    keyNumber = 5;
                    keyType = 2;
                    octaveBlockNumber = 1;
                }
                case U -> keyNumber = 7;
                case I -> keyNumber = 8;
                case DIGIT9 -> {
                    keyType = 2;
                    octaveBlockNumber = 2;
                }
                case O -> keyNumber = 9;
                case DIGIT0 -> {
                    keyNumber = 1;
                    keyType = 2;
                    octaveBlockNumber = 2;
                }
                case P -> keyNumber = 10;
                case SHIFT -> keyNumber = 11;
                case A -> {
                    keyNumber = 3;
                    keyType = 2;
                    octaveBlockNumber = 2;
                }
                case Z -> keyNumber = 12;
                case S -> {
                    keyNumber = 4;
                    keyType = 2;
                    octaveBlockNumber = 2;
                }
                case X -> keyNumber = 13;
                case D -> {
                    keyNumber = 5;
                    keyType = 2;
                    octaveBlockNumber = 2;
                }
                case C -> keyNumber = 14;
                case V -> keyNumber = 15;
                case G -> {
                    keyType = 2;
                    octaveBlockNumber = 3;
                }
                case B -> keyNumber = 16;
                case H -> {
                    keyNumber = 1;
                    keyType = 2;
                    octaveBlockNumber = 3;
                }
                case N -> keyNumber = 17;
                case M -> keyNumber = 18;
                case K -> {
                    keyNumber = 3;
                    keyType = 2;
                    octaveBlockNumber = 3;
                }
                case COMMA -> keyNumber = 19;
                case L -> {
                    keyNumber = 4;
                    keyType = 2;
                    octaveBlockNumber = 3;
                }
                case PERIOD -> keyNumber = 20;
                case SEMICOLON -> {
                    keyNumber = 5;
                    keyType = 2;
                    octaveBlockNumber = 3;
                }
                case SLASH -> keyNumber = 21;
            }

            keyInfos.put(key, new KeyInfo(keyNumber, octaveBlockNumber, keyType));
        }

        keyboard.drawWithPressedKeys(width, keyInfos.values().stream().toList(), drawWithKeys);
    }


    private void playKeySound(KeyCode key) {
        for(Key curKey : keys) {
           if(curKey.keyCode().equals(key)) {
            AudioClip sound = curKey.audioClip();

            if (!sound.isPlaying()) {
                sound.play();
            }
               break;
           }
        }
    }

    private void keyReleased(KeyEvent keyEvent) {

        for(Key key : keys) {
            if (key.keyCode().equals(keyEvent.getCode())) {
                pressedKeys.remove(keyEvent.getCode());
                keyInfos.remove(keyEvent.getCode());
                keyboard.drawWithPressedKeys(width, keyInfos.values().stream().toList(), drawWithKeys);
                stopKeySound(keyEvent.getCode());

                break;
            }
        }
    }

    private void stopKeySound(KeyCode code) {
        for(Key key : keys) {
            if(key.keyCode().equals(code)) {
                key.audioClip().stop();

                break;
            }
        }
    }


    @EventListener
    public void handleSceneResizeEvent(SceneResizeEvent event) {
        width = event.getSceneWidth().doubleValue() - 10;

        if (keyboard != null) {
            keyboard.draw(width, drawWithKeys);
        }

    }

    public void fillKeySounds() {
        keys.addAll(soundBuilder.getKeys());
    }


    @EventListener
    public void handleHintEvent(HintEvent event) {
        drawWithKeys = event.isShowHints();
        keyboard.draw(width, drawWithKeys);
    }

    @EventListener
    public void handleShowNotesEvent(ShowHidePlayedKeysEvent event) {
        if (event.mustShowKeys()) {
            matchKeyToSound(soundBuilder.getCurrentKeys());
        } else {
            keyboard.draw(width, drawWithKeys);
        }
    }


    public void matchKeyToSound(List<Key> sounds) {

        pressedKeys.clear();
        keyInfos.clear();

        for (Key sound : sounds) {
            if(keys.contains(sound)) {
                pressedKeys.add(sound.keyCode());
            }
        }
        drawAndPlay();

    }


}
