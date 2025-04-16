package dev.cat.musictheoryfx.controller;

import dev.cat.musictheoryfx.event.SceneResizeEvent;
import dev.cat.musictheoryfx.notefactory.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.*;

@Component
public class ScalesController implements Initializable {

    public final ScaleChordBuilder builder;

    @FXML
    private Button nextButton;

    @FXML
    private Button repeatButton;

    @FXML
    private Label dataLabel;

    @FXML
    private Label scalesLabel;

    @FXML
    private Button showNotesButton;

    private int scaleCount = 0;

    StringProperty scaleNameProperty = new SimpleStringProperty();
    StringProperty scalesProperty = new SimpleStringProperty();

    private final HashMap<RootScaleNote, List<Scale>> majorScales = new HashMap<>();
    private final HashMap<RootScaleNote, List<Scale>> minorScales = new HashMap<>();

    private List<RootScaleNote> rootNotes = new ArrayList<>();

    private RootScaleNote currentNote;

    private ObjectProperty<Font> fontTracker = new SimpleObjectProperty<>(Font.getDefault());


    @Lazy
    public ScalesController(ScaleChordBuilder builder) {
        this.builder = builder;
    }

    @FXML
    void getNextScale() {
        scalesProperty.setValue("");

        if (scaleCount < rootNotes.size()) {

            currentNote = rootNotes.get(scaleCount);
            scaleNameProperty.setValue(currentNote.getNote().getLetterName() + " " +
                    currentNote.getScaleType().getDescription());
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

    @FXML
    public void showNotes() {

        String scaleNotes = createScalesList();
        scalesProperty.setValue(scaleNotes);

    }

    private String createScalesList() {

        List<Scale> scales = new ArrayList<>();

        if (currentNote.getScaleType() == ScaleType.SCALE_MAJOR) {
            scales.addAll(majorScales.get(currentNote));
        } else scales.addAll(minorScales.get(currentNote));
        StringBuilder scalesAccumulator = new StringBuilder();

        for (Scale scale : scales) {
            scalesAccumulator.append(scale.getScaleType().getDescription().toUpperCase())
                    .append("\n\n")
                    .append("Ascending: ")
                    .append(scale.getScaleNotesAscending()
                            .stream()
                            .map(Note::getNotation)
                            .toList())
                    .append("\n\n")
                    .append("Descending: ")
                    .append(scale.getScaleNotesDescending()
                            .stream()
                            .map(Note::getNotation)
                            .toList())
                    .append("\n\n");

        }

        return StringUtils.replaceChars(
                scalesAccumulator.toString(), "[],", null);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataLabel.textProperty().bind(scaleNameProperty);
        scalesLabel.textProperty().bind(scalesProperty);

        scalesLabel.fontProperty().bind(fontTracker);

        getDataOnScalesWithNotes();

    }

    @EventListener
    public void handleSceneResizeEvent(SceneResizeEvent event) {
        fontTracker.set(Font.font(event.getSceneWidth().doubleValue() / 60));
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
