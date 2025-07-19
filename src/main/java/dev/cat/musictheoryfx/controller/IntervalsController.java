package dev.cat.musictheoryfx.controller;

import dev.cat.musictheoryfx.controller.ui.SoundBuilder;
import dev.cat.musictheoryfx.event.ShowHideKeysEvent;
import dev.cat.musictheoryfx.notefactory.Interval;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class IntervalsController implements Initializable {


    @FXML
    private Button showNotesButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button playAgainButton;
    @FXML
    private Label responseLabel;
    @FXML
    private ComboBox<String> comboBox;

    public final SoundBuilder soundBuilder = SoundBuilder.getInstance();

    private final ApplicationEventPublisher eventPublisher;

    private final Timer timer = new Timer();

    Interval interval;
    private final Random randomInterval = new Random();
    private final Random randomSound = new Random();

    StringProperty responseProperty = new SimpleStringProperty();

    AudioClip firstSound;
    AudioClip secondSound;


    @Lazy
    public IntervalsController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        soundBuilder.clearCurrentSounds();
        populateComboBox();
    }

    private void populateComboBox() {
        String[] intervals = {"Perfect Unison (P1)",
                "Minor Second (m2)",
                "Major Second (M2)",
                "Minor Third (m3)",
                "Major Third (M3)",
                "Perfect Fourth (P4)",
                "Triton (aug4/dim5)",
                "Perfect Fifth (P5)",
                "Minor Sixth (m6)",
                "Major Sixth (M6)",
                "Minor Seventh (m7)",
                "Major Seventh (M7)",
                "Perfect Octave (P8)"};

        comboBox.getItems().addAll(intervals);

        responseLabel.textProperty().bind(responseProperty);

    }

    @FXML
    public void showNotes(ActionEvent actionEvent) {
        eventPublisher.publishEvent(new ShowHideKeysEvent(this, true));
    }

    @FXML
    public void getNextInterval(ActionEvent actionEvent) {

        soundBuilder.clearCurrentSounds();
        eventPublisher.publishEvent(new ShowHideKeysEvent(this, false));


        interval = getRandomInterval();

        int semiTones = interval.getSemiTones();
        int startingPoint = randomSound.nextInt(24);

        firstSound = soundBuilder.getSound(startingPoint);
        soundBuilder.addCurrentSound(firstSound);

        secondSound = soundBuilder.getSound(startingPoint + semiTones);
        soundBuilder.addCurrentSound(secondSound);


        responseProperty.setValue("");
        nextButton.setDisable(true);
        comboBox.setValue("");

        playSounds(firstSound, secondSound);

    }

    private void playSounds(AudioClip firstSound, AudioClip secondSound) {

        firstSound.play();
        secondSound.play();

        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                firstSound.stop();
                secondSound.stop();
            }
        }, 3000);

        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                firstSound.play();
            }
        }, 3100);

        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                firstSound.stop();
                secondSound.play();
            }
        }, 4100);

        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                secondSound.stop();
            }
        }, 5100);

    }

    private Interval getRandomInterval() {
        return Interval.getIntervalBySemitones(randomInterval.nextInt(13));
    }

    @FXML
    public void playAgain(ActionEvent actionEvent) {
        playSounds(firstSound, secondSound);
    }

    @FXML
    public void submitAnswer(ActionEvent actionEvent) {
        String data = comboBox.getValue();

        if (data.isBlank()) {
            responseProperty.setValue("");
        } else if (data.equals(interval.getDescription())) {
            responseProperty.setValue("RIGHT");
            nextButton.setDisable(false);
        } else responseProperty.setValue("WRONG");
    }
}