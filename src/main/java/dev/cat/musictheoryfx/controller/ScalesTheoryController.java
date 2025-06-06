package dev.cat.musictheoryfx.controller;

import dev.cat.musictheoryfx.controller.ui.ResizableCanvas;
import dev.cat.musictheoryfx.event.SceneResizeEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;


@Component
public class ScalesTheoryController implements Initializable {

    @FXML
    private ResizableCanvas keyboard;
    private double width = 1000;

    EventHandler<KeyEvent> keyPressListener = this::keyPressed;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        keyboard.sceneProperty().addListener((observableValue,
                                              oldScene,
                                              newScene) -> {
            if (newScene != null) {
                newScene.addEventHandler(KeyEvent.KEY_PRESSED, keyPressListener);
            }
        });

        keyboard.draw(width);
    }

    private void keyPressed(KeyEvent e) {
        KeyCode key = e.getCode();
        switch(key) {
            case Q -> System.out.println("Pressed key Q");
            case DIGIT2 -> System.out.println("Pressed key 1");
            case W -> System.out.println("Pressed key W");
            case DIGIT3 -> System.out.println("Pressed key 3");
            case E -> System.out.println("Pressed key E");
            case R -> System.out.println("Pressed key R");
            case DIGIT5 -> System.out.println("Pressed key 5");
            case T -> System.out.println("Pressed key T");
            case DIGIT6 -> System.out.println("Pressed key 6");
            case Y -> System.out.println("Pressed key Y");
            case DIGIT7 -> System.out.println("Pressed key 7");
            case U -> System.out.println("Pressed key U");
            case I -> System.out.println("Pressed key I");
            case DIGIT9 -> System.out.println("Pressed key 9");
            case O -> System.out.println("Pressed key O");
            case DIGIT0 -> System.out.println("Pressed key 0");
            case P -> System.out.println("Pressed key P");
            case Z -> System.out.println("Pressed key Z");
            case S -> System.out.println("Pressed key S");
            case X -> System.out.println("Pressed key X");
            case D -> System.out.println("Pressed key D");
            case C -> System.out.println("Pressed key C");
            case F -> System.out.println("Pressed key F");
            case V -> System.out.println("Pressed key V");
            case B -> System.out.println("Pressed key B");
            case H -> System.out.println("Pressed key H");
            case N -> System.out.println("Pressed key N");
            case J -> System.out.println("Pressed key J");
            case M -> System.out.println("Pressed key M");
            case COMMA -> System.out.println("Pressed key ,");
            case L -> System.out.println("Pressed key L");
            case PERIOD -> System.out.println("Pressed key .");
            case SEMICOLON -> System.out.println("Pressed key ;");
            case SLASH -> System.out.println("Pressed key /");
            case QUOTE -> System.out.println("Pressed key quote");
            case SHIFT -> System.out.println("Pressed key shift");
        }
    }

    @EventListener
    public void handleSceneResizeEvent(SceneResizeEvent event) {
        width = event.getSceneWidth().doubleValue() - 10;

        if(keyboard != null) {
            keyboard.draw(width);
        }

    }


}
