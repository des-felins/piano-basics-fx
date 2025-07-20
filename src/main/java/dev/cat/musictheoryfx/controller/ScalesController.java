package dev.cat.musictheoryfx.controller;

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
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.*;

@Component
public class ScalesController implements Initializable {

    public final ScaleBuilder builder;

    @FXML
    private Button nextButton;

    @FXML
    private Button repeatButton;

    @FXML
    private Label dataLabel;

    private int scaleCount = 0;

    StringProperty scaleNameProperty = new SimpleStringProperty();

    private final HashMap<RootScaleNote, List<Scale>> majorScales = new HashMap<>();
    private final HashMap<RootScaleNote, List<Scale>> minorScales = new HashMap<>();

    private List<RootScaleNote> rootNotes = new ArrayList<>();

    private RootScaleNote currentNote;


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
        majorScales.putAll(builder.getMajorScalesFromCircle());
        rootNotes.addAll(majorScales.keySet());


        minorScales.putAll(builder.getMinorScalesFromCircle());
        rootNotes.addAll(minorScales.keySet());

        shuffle(rootNotes);
    }


}
