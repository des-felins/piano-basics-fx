package dev.cat.musictheoryfx.controller;

import dev.cat.musictheoryfx.controller.ui.ResizableCanvas;
import dev.cat.musictheoryfx.event.SceneResizeEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;


@Component
public class ScalesTheoryController implements Initializable {

    @FXML
    private ResizableCanvas keyboard;
    private double width = 1000;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        keyboard.draw(width);

    }

    @EventListener
    public void handleSceneResizeEvent(SceneResizeEvent event) {

        width = event.getSceneWidth().doubleValue() - 10;

        if(keyboard != null) {
            keyboard.draw(width);
        }

    }


}
