package dev.cat.musictheoryfx.controller.ui;

import dev.cat.musictheoryfx.notefactory.Octave;
import dev.cat.musictheoryfx.notefactory.Pitch;
import javafx.scene.media.AudioClip;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class SoundBuilder {

    private static List<AudioClip> currentSounds = new ArrayList<>();
    private static volatile SoundBuilder instance;

    private static List<Key> keys = new ArrayList<>();

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
        populateKeys();
        return localInstance;
    }

    private static void populateKeys() {
        List<AudioClip> sounds = new ArrayList<>();
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

        keys.addAll(KeyFactory.buildKeys(sounds));

    }

    public AudioClip getSound(int index) {
        return keys.get(index).audioClip();
    }

    public int getSoundCount() {
        return keys.size();
    }

    public List<AudioClip> getSounds() {
        return keys.stream().map(Key::audioClip).toList();
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

    static final class KeyFactory {

        public static List<Key> buildKeys(List<AudioClip> clips) {
            return buildKeys(clips, Pitch.C, Octave.SMALL);
        }


        public static List<Key> buildKeys(List<AudioClip> clips,
                                          Pitch startingPitch,
                                          Octave startingOctave) {

            if (clips.size() != 36)
                throw new IllegalArgumentException("Expected 36 audio clips, got " + clips.size());

            Pitch[] pitches = Pitch.values();
            Octave[] octaves = Octave.values();

            List<Key> keys = new ArrayList<>(36);

            for (int i = 0; i < 36; i++) {
                Pitch pitch  = pitches[i % 12];
                Octave octave = octaves[i / 12];
                keys.add(new Key(clips.get(i), pitch, octave));
            }
            return List.copyOf(keys);

        }
    }
}
