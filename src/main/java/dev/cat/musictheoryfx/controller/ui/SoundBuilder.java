package dev.cat.musictheoryfx.controller.ui;

import dev.cat.musictheoryfx.notefactory.Octave;
import dev.cat.musictheoryfx.notefactory.Pitch;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class SoundBuilder {

    private static List<Key> currentSounds = new ArrayList<>();
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
        List<String> files = extractFiles();

        for (int i = 0; i < files.size(); i++) {
            String file = files.get(i);

            String path = Objects.requireNonNull(
                            SoundBuilder.class.getResource(file))
                    .toExternalForm();
            AudioClip keySound = new AudioClip(path);
            sounds.add(keySound);
        }

        List<KeyCode> codes = extractCodes();

        keys.addAll(KeyFactory.buildKeys(sounds, codes));

    }

    public static List<String> extractFiles () {
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

        return files;

    }

    public static List<KeyCode> extractCodes() {

        List<KeyCode> codes = new ArrayList<>();
        codes.add(KeyCode.Q);
        codes.add(KeyCode.DIGIT2);
        codes.add(KeyCode.W);
        codes.add(KeyCode.DIGIT3);
        codes.add(KeyCode.E);
        codes.add(KeyCode.R);
        codes.add(KeyCode.DIGIT5);
        codes.add(KeyCode.T);
        codes.add(KeyCode.DIGIT6);
        codes.add(KeyCode.Y);
        codes.add(KeyCode.DIGIT7);
        codes.add(KeyCode.U);
        codes.add(KeyCode.I);
        codes.add(KeyCode.DIGIT9);
        codes.add(KeyCode.O);
        codes.add(KeyCode.DIGIT0);
        codes.add(KeyCode.P);
        codes.add(KeyCode.SHIFT);
        codes.add(KeyCode.A);
        codes.add(KeyCode.Z);
        codes.add(KeyCode.S);
        codes.add(KeyCode.X);
        codes.add(KeyCode.D);
        codes.add(KeyCode.C);
        codes.add(KeyCode.V);
        codes.add(KeyCode.G);
        codes.add(KeyCode.B);
        codes.add(KeyCode.H);
        codes.add(KeyCode.N);
        codes.add(KeyCode.M);
        codes.add(KeyCode.K);
        codes.add(KeyCode.COMMA);
        codes.add(KeyCode.L);
        codes.add(KeyCode.PERIOD);
        codes.add(KeyCode.SEMICOLON);
        codes.add(KeyCode.SLASH);

        return codes;

    }

    public Key getNewKey(int index) {
        return keys.get(index);
    }

    public int getSoundCount() {
        return keys.size();
    }

    public List<AudioClip> getClips() {
        return keys.stream().map(Key::audioClip).toList();
    }

    public List<KeyCode> getCodes() {
        return keys.stream().map(Key::keyCode).toList();
    }

    public void addCurrentKey(Key key) {
        currentSounds.add(key);
    }

    public List<Key> getCurrentKeys() {
        return currentSounds;
    }

    public void clearCurrentKeys() {
        currentSounds.clear();
    }

    public List<Key> getKeys() {
        return keys;
    }



    static final class KeyFactory {

        public static List<Key> buildKeys(List<AudioClip> clips, List<KeyCode> codes) {
            if (clips.size() != 36)
                throw new IllegalArgumentException("Expected 36 audio clips, got " + clips.size());

            Pitch[] pitches = Pitch.values();
            Octave[] octaves = Octave.values();

            List<Key> keys = new ArrayList<>(36);

            for (int i = 0; i < 36; i++) {
                Pitch pitch  = pitches[i % 12];
                Octave octave = octaves[i / 12];
                keys.add(new Key(clips.get(i), pitch, octave, codes.get(i)));
            }
            return List.copyOf(keys);
        }
    }
}
