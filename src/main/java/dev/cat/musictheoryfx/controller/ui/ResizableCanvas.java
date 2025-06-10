package dev.cat.musictheoryfx.controller.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class ResizableCanvas extends Canvas {


    public final KeyboardBuilder keyboardBuilder = new KeyboardBuilder();
    private final double MAX_HEIGHT = 300;

    public ResizableCanvas() {
        setHeight(210);
    }

    public void draw(double width,
                     int keyNumber,
                     int keyType,
                     int octaveBlockNumber) {
        setSize(width);

        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width, getHeight());
        keyboardBuilder.drawPiano(gc, getWidth(), getHeight(),
                keyNumber, keyType, octaveBlockNumber);
    }

    public void setSize(double width) {
        setWidth(width);
        setHeight(width - 790);

        if(getHeight() > MAX_HEIGHT) {
            setHeight(MAX_HEIGHT);
        }
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }

}
