package dev.cat.musictheoryfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ScalesController implements Initializable {

    @FXML
    private Button nextButton;

    @FXML
    private Button repeatButton;

    @FXML
    private Label dataLabel;

    @FXML
    private Button showNotesButton;

    int count = 0;

    private HashMap<String, String> scalesWithNotes = new HashMap<>();

    List<String> names = new ArrayList<>();

    StringProperty scale = new SimpleStringProperty();

    @FXML
    void getNextScale() {

        if (count < names.size()) {
            scale.setValue(names.get(count));
            count++;

        } else {
            scale.setValue("Done!");
        }
    }

    @FXML
    void shuffleAndRepeat() {
        count = 0;
        shuffle(names);
        getNextScale();
    }

    @FXML
    public void showNotes() {

        String currentScale = scale.getValue();
        scale.setValue(currentScale
                + "\n" + scalesWithNotes.get(currentScale));

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dataLabel.textProperty().bind(scale);

    }

    public void shuffle(List<String> names) {
        Collections.shuffle(names);
    }


    public void getDataOnScalesWithNotes() {
        Path path = Paths.get("src/main/resources", "scales-with-notes.txt");
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Path doesn't exist");
        }

        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/main/resources/scales-with-notes.txt"))) {

            while (reader.ready()) {

                String line = reader.readLine();
                String[] parts = line.split(":");

                String scaleName = parts[0].trim();
                String notes = parts[1].trim();

                if (!scaleName.isEmpty() && !notes.isEmpty())
                    scalesWithNotes.put(scaleName, notes);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        names.addAll(scalesWithNotes.keySet());
        shuffle(names);

    }


}
