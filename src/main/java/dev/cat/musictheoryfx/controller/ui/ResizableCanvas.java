package dev.cat.musictheoryfx.controller.ui;

import dev.cat.musictheoryfx.event.HintEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.springframework.context.event.EventListener;

public class ResizableCanvas extends Canvas {


    public final KeyboardBuilder keyboardBuilder = new KeyboardBuilder();
    private final double MAX_HEIGHT = 300;
    boolean drawWithKeys = false;


    private int savedKeyNumber = 0;
    private int savedKeyType = 0;
    private int savedOctaveBlockNumber = 0;

    public ResizableCanvas() {
        setHeight(210);
    }

    public void draw(double width,
                     int keyNumber,
                     int keyType,
                     int octaveBlockNumber, boolean drawWithKeys) {
        setSize(width);

        savedKeyNumber = keyNumber;
        savedKeyType = keyType;
        savedOctaveBlockNumber = octaveBlockNumber;


        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width, getHeight());
        keyboardBuilder.drawPiano(gc, getWidth(), getHeight(),
                keyNumber, keyType, octaveBlockNumber, drawWithKeys);
    }

    public void setSize(double width) {
        setWidth(width);
        setHeight(width - 790);

        if (getHeight() > MAX_HEIGHT) {
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

//    @EventListener
//    public void handleHintEvent(HintEvent event) {
//        System.out.println("In method handle hint event");
//        drawWithKeys = event.isShowHints();
//        draw(getWidth(), savedKeyNumber, savedKeyType, savedOctaveBlockNumber, drawWithKeys);
//    }

}
