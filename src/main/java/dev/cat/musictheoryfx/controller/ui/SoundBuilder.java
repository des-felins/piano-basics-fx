package dev.cat.musictheoryfx.controller.ui;

import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class SoundBuilder {

    private static final List<AudioClip> sounds = new ArrayList<>();
    private static List<AudioClip> currentSounds = new ArrayList<>();
    private static volatile SoundBuilder instance;

    public static SoundBuilder getInstance() {
        SoundBuilder localInstance = instance;
        if (localInstance == null) {
            synchronized (SoundBuilder.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SoundBuilder();
                }
            }
        }
        populateSounds();
        return localInstance;
    }

    private static void populateSounds() {
        List<String> files = new ArrayList<>();

        files.add("/sound/C3.wav");
        files.add("/sound/Db3.wav");
        files.add("/sound/D3.wav");
        files.add("/sound/Eb3.wav");
        files.add("/sound/E3.wav");
        files.add("/sound/F3.wav");
        files.add("/sound/Gb3.wav");
        files.add("/sound/G3.wav");
        files.add("/sound/Ab3.wav");
        files.add("/sound/A3.wav");
        files.add("/sound/Bb3.wav");
        files.add("/sound/B3.wav");
        files.add("/sound/C4.wav");
        files.add("/sound/Db4.wav");
        files.add("/sound/D4.wav");
        files.add("/sound/Eb4.wav");
        files.add("/sound/E4.wav");
        files.add("/sound/F4.wav");
        files.add("/sound/Gb4.wav");
        files.add("/sound/G4.wav");
        files.add("/sound/Ab4.wav");
        files.add("/sound/A4.wav");
        files.add("/sound/Bb4.wav");
        files.add("/sound/B4.wav");
        files.add("/sound/C5.wav");
        files.add("/sound/Db5.wav");
        files.add("/sound/D5.wav");
        files.add("/sound/Eb5.wav");
        files.add("/sound/E5.wav");
        files.add("/sound/F5.wav");
        files.add("/sound/Gb5.wav");
        files.add("/sound/G5.wav");
        files.add("/sound/Ab5.wav");
        files.add("/sound/A5.wav");
        files.add("/sound/Bb5.wav");
        files.add("/sound/B5.wav");

        for (int i = 0; i < files.size(); i++) {
            String file = files.get(i);

            String path = Objects.requireNonNull(
                            SoundBuilder.class.getResource(file))
                    .toExternalForm();
            AudioClip keySound = new AudioClip(path);
            sounds.add(keySound);
        }
    }

    public AudioClip getSound(int index) {
        return sounds.get(index);
    }

    public int getSoundCount() {
        return sounds.size();
    }

    public List<AudioClip> getSounds() {
        return sounds;
    }

    public void addCurrentSound(AudioClip sound) {
        currentSounds.add(sound);
    }

    public List<AudioClip> getCurrentSounds() {
        return currentSounds;
    }

    public void clearCurrentSounds() {
        currentSounds.clear();
    }
}
