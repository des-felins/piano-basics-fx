package dev.cat.musictheoryfx.controller;

import dev.cat.musictheoryfx.controller.ui.KeyboardBuilder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;


@Component
public class ScalesTheoryController implements Initializable {

    @FXML
    private Canvas keyboard;

    public final KeyboardBuilder keyboardBuilder;

    public ScalesTheoryController(KeyboardBuilder keyboardBuilder) {
        this.keyboardBuilder = keyboardBuilder;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        GraphicsContext gc = keyboard.getGraphicsContext2D();
        keyboardBuilder.drawPiano(gc);

    }

}
