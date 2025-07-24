package dev.cat.musictheoryfx.controller;

import dev.cat.musictheoryfx.controller.ui.Key;
import dev.cat.musictheoryfx.controller.ui.SoundBuilder;
import dev.cat.musictheoryfx.notefactory.RootScaleNote;
import dev.cat.musictheoryfx.notefactory.Scale;
import dev.cat.musictheoryfx.notefactory.ScaleBuilder;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.*;

@Component
public class ScalesController implements Initializable {

    //six data labels? one is visible at first, then it is green , and the next one is visible

    public final ScaleBuilder builder;

    @FXML
    private Button nextButton;

    @FXML
    private Button repeatButton;

    @FXML
    private Label dataLabel;

    private int scaleCount = 0;

    StringProperty scaleNameProperty = new SimpleStringProperty();

    private final HashMap<RootScaleNote, List<Scale>> scales = new HashMap<>();
    private List<RootScaleNote> rootNotes = new ArrayList<>();

    private RootScaleNote currentNote;
    private Scale currentScale;

    private List<List<Key>> keysForScales = new ArrayList<>();

    public final SoundBuilder soundBuilder = SoundBuilder.getInstance();


    @Lazy
    public ScalesController(ScaleBuilder builder) {
        this.builder = builder;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataLabel.textProperty().bind(scaleNameProperty);
        getDataOnScalesWithNotes();

    }

    // the app should not build scales in advance
    // it waits for the first key pushed by user and if it is correct and the octave can be built
    // it builds the scale from here

    @FXML
    void getNextScale() {

        if (scaleCount < rootNotes.size()) {

            currentNote = rootNotes.get(scaleCount);
            currentScale = scales.get(currentNote).getFirst();

            scaleNameProperty.setValue(currentNote.getNote().getLetterName() + " " +
                    currentNote.getScaleType().getDescription());
            nextButton.setVisible(false);
            scaleCount++;

        } else {
            scaleNameProperty.setValue("Done!");
        }
    }



    @FXML
    void shuffleAndRepeat() {
        scaleCount = 0;
        shuffle(rootNotes);
        getNextScale();

    }

    public void shuffle(List<RootScaleNote> names) {
        Collections.shuffle(names);
    }


    public void getDataOnScalesWithNotes() {
        scales.putAll(builder.getMajorScalesFromCircle());
        scales.putAll(builder.getMinorScalesFromCircle());

        rootNotes.addAll(scales.keySet());

        shuffle(rootNotes);
    }

    // check whether root note
    // when the whole current scale is played correctly, choose the next list from the list of keysforscale

    @EventListener
    public void handleKeyActionEvent(Key key) {
        if(key.pitch().equals(currentNote.getNote().getPitch())) {
            if (buildScalesWithKeys()) {
               //add a counter to fields for steps
                // i++
            }
            // else throw error
            // "can't build complete scale from this note, choose the root note in previous octave"
        }

        // do while i < scalelist.size

        // use i to get next key in list and verify against user input
        // if right, i++
        // else "wrong" and don't increment i


        // after ending loop - make data label fir this scale green and unblock the next scale

    }

    private boolean buildScalesWithKeys() {
        //measure in intervals

        //if can't build whole scale - break and return false




        return true;
    }


}
