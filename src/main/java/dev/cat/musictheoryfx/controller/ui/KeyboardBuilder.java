package dev.cat.musictheoryfx.controller.ui;

import dev.cat.musictheoryfx.event.SceneResizeEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KeyboardBuilder {


    private final int OCTAVES = 3;
    private final int WHITE_KEY_WIDTH = 44;
    private final int WHITE_KEY_HEIGHT = 205;
    private final int BLACK_KEY_WIDTH = 29;
    private final int BLACK_KEY_HEIGHT = 125;

    //  private ObjectProperty<Integer> WHITE_KEY_WIDTH = new SimpleObjectProperty<>(44);

    private final List<Integer> blackKeyOffsets = List.of(1, 3, 6, 8, 10); // semitone positions of black keys in octave


    public void drawPiano(GraphicsContext gc) {
        int totalWhiteKeys = OCTAVES * 7;

        // Draw white keys
        for (int i = 0; i < totalWhiteKeys; i++) {
            int x = i * WHITE_KEY_WIDTH;
            gc.setFill(Color.WHITE);
            gc.fillRect(x, 0, WHITE_KEY_WIDTH, WHITE_KEY_HEIGHT);
            gc.setStroke(Color.BLACK);
            gc.strokeRect(x, 0, WHITE_KEY_WIDTH, WHITE_KEY_HEIGHT);
        }

        // Draw black keys (overlapping white ones)
        for (int octave = 0; octave < OCTAVES; octave++) {
            for (int semitone : blackKeyOffsets) {
                int indexInWhiteKeys = getWhiteKeyIndex(semitone);
                int x = (octave * 7 + indexInWhiteKeys + 1) * WHITE_KEY_WIDTH - (BLACK_KEY_WIDTH / 2);
                gc.setFill(Color.BLACK);
                gc.fillRect(x, 0, BLACK_KEY_WIDTH, BLACK_KEY_HEIGHT);
            }
        }
    }

    // Helper: map black key semitone to position in white keys
    private int getWhiteKeyIndex(int semitone) {
        // Mapping: 1(C#)->0, 3(D#)->1, 6(F#)->3, 8(G#)->4, 10(A#)->5
        return switch (semitone) {
            case 1 -> 0;
            case 3 -> 1;
            case 6 -> 3;
            case 8 -> 4;
            case 10 -> 5;
            default -> 0;
        };
    }

    @EventListener
    public void handleSceneResizeEvent(SceneResizeEvent event) {

        //  WHITE_KEY_WIDTH.set((event.getSceneWidth().intValue() / 1010) * WHITE_KEY_WIDTH.getValue());
        
        /*
        WHITE_KEY_WIDTH = (event.getSceneWidth().intValue() / 1010) * WHITE_KEY_WIDTH;
        WHITE_KEY_HEIGHT = (event.getSceneWidth().intValue() / 1010) * WHITE_KEY_HEIGHT;
        BLACK_KEY_WIDTH = (event.getSceneWidth().intValue() / 1010) * BLACK_KEY_WIDTH;
        BLACK_KEY_HEIGHT = (event.getSceneWidth().intValue() / 1010) * BLACK_KEY_HEIGHT;

         */
    }

}
