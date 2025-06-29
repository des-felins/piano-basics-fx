package dev.cat.musictheoryfx.controller.ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KeyboardBuilder {


    private final int OCTAVES = 3;
    private int WHITE_KEY_WIDTH = 44;
    private int WHITE_KEY_HEIGHT = 205;
    private int BLACK_KEY_WIDTH = 29;
    private int BLACK_KEY_HEIGHT = 125;

    private List<Character> keyToWhiteKey = List.of('Q', 'W', 'E', 'R', 'T', 'Y',
            'U', 'I', 'O', 'P', '⇧', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', '<', '>', '/');
    private List<Character> keyToBlackKey = List.of('2', '3', '5', '6', '7', '9',
            '0', 'A', 'S', 'D', 'G', 'H', 'K', 'L', ';');



    private final List<Integer> blackKeyOffsets = List.of(1, 3, 6, 8, 10); // semitone positions of black keys in octave


    public void drawPiano(GraphicsContext gc,
                          double width,
                          double height, boolean drawWithKeys) {

        resizeKeys(width, height);

        int totalWhiteKeys = OCTAVES * 7;

        // Draw white keys
        for (int i = 0; i < totalWhiteKeys; i++) {
            int x = i * WHITE_KEY_WIDTH;
                gc.setFill(Color.WHITE);


            gc.fillRect(x, 0, WHITE_KEY_WIDTH, WHITE_KEY_HEIGHT);
            gc.setStroke(Color.BLACK);
            gc.strokeRect(x, 0, WHITE_KEY_WIDTH, WHITE_KEY_HEIGHT);

            if(drawWithKeys) {
                gc.setFont(new Font("Verdana", 12));
                gc.strokeText(String.valueOf(keyToWhiteKey.get(i)), x + 10, WHITE_KEY_HEIGHT - 10);
            }
        }

        int blackKeyIndex = 0;

        // Draw black keys (overlapping white ones)
        for (int octave = 0; octave < OCTAVES; octave++) {

            for (int semitone : blackKeyOffsets) {
                int indexInWhiteKeys = getWhiteKeyIndex(semitone);
                int x = (octave * 7 + indexInWhiteKeys + 1) * WHITE_KEY_WIDTH - (BLACK_KEY_WIDTH / 2);
                    gc.setFill(Color.BLACK);
                gc.fillRect(x, 0, BLACK_KEY_WIDTH, BLACK_KEY_HEIGHT);

                if(drawWithKeys) {
                    gc.setStroke(Color.WHITE);
                    gc.setFont(new Font("Verdana", 12));
                    gc.strokeText(String.valueOf(keyToBlackKey.get(blackKeyIndex)), x + 10, BLACK_KEY_HEIGHT - 10);
                }
                blackKeyIndex++;
            }
        }
    }

    public void drawPianoWithPressedKeys(GraphicsContext gc,
                                         double width,
                                         double height,
                                         List<KeyInfo> keyInfos,
                                         boolean drawWithKeys) {

        resizeKeys(width, height);


        int totalWhiteKeys = OCTAVES * 7;

        // Draw white keys
        for (int i = 0; i < totalWhiteKeys; i++) {
            int x = i * WHITE_KEY_WIDTH;

            gc.setFill(Color.WHITE);

            for (KeyInfo info : keyInfos) {
                if (info.keyType() == 1 && info.keyNumber() - 1 == i) {
                    gc.setFill(Color.LAVENDER);
                }
            }

                gc.fillRect(x, 0, WHITE_KEY_WIDTH, WHITE_KEY_HEIGHT);
                gc.setStroke(Color.BLACK);
                gc.strokeRect(x, 0, WHITE_KEY_WIDTH, WHITE_KEY_HEIGHT);

                if (drawWithKeys) {
                    gc.setFont(new Font("Verdana", 12));
                    gc.strokeText(String.valueOf(keyToWhiteKey.get(i)), x + 10, WHITE_KEY_HEIGHT - 10);
                }

        }
            int blackKeyIndex = 0;

            // Draw black keys (overlapping white ones)
            for (int octave = 0; octave < OCTAVES; octave++) {

                for (int semitone : blackKeyOffsets) {
                    int indexInWhiteKeys = getWhiteKeyIndex(semitone);
                    int x = (octave * 7 + indexInWhiteKeys + 1) * WHITE_KEY_WIDTH - (BLACK_KEY_WIDTH / 2);

                    gc.setFill(Color.BLACK);

                    for(KeyInfo info : keyInfos) {

                        if (info.keyType() == 2 && info.keyNumber() == indexInWhiteKeys && info.octaveBlockNumber() - 1 == octave) {
                            gc.setFill(Color.LAVENDER);
                        }
                    }
                    gc.fillRect(x, 0, BLACK_KEY_WIDTH, BLACK_KEY_HEIGHT);

                    if (drawWithKeys) {
                        gc.setStroke(Color.WHITE);
                        gc.setFont(new Font("Verdana", 12));
                        gc.strokeText(String.valueOf(keyToBlackKey.get(blackKeyIndex)), x + 10, BLACK_KEY_HEIGHT - 10);
                    }
                    blackKeyIndex++;
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

    private void resizeKeys(double width, double height) {
        setStartingKeySize();

        WHITE_KEY_WIDTH = (int) ((width / 1000) * WHITE_KEY_WIDTH);
        WHITE_KEY_HEIGHT = (int) (((height - 5) / 205) * WHITE_KEY_HEIGHT);
        BLACK_KEY_WIDTH = (int) ((width / 1000) * BLACK_KEY_WIDTH);
        BLACK_KEY_HEIGHT = (int) (((height - 85) / 125) * BLACK_KEY_HEIGHT);

    }

    private void setStartingKeySize() {
        WHITE_KEY_WIDTH = 44;
        WHITE_KEY_HEIGHT = 205;
        BLACK_KEY_WIDTH = 29;
        BLACK_KEY_HEIGHT = 125;

    }

}
