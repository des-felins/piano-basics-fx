package dev.cat.musictheoryfx.notefactory;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class CircleOfFifths {

    private final HashMap<Note, KeySignature> majorKeys = new HashMap<>();
    private final HashMap<Note, KeySignature> minorKeys = new HashMap<>();

    public CircleOfFifths() {
        collectKeys();
    }

    private void collectKeys() {
        int steps = Interval.P5.getSemiTones();
        for (int i = -7; i <= steps; i++) {

            Note rootNoteMajor = Note.getNoteForKey(i * steps, ScaleType.SCALE_MAJOR, i);

            Note rootPitchMinor = Note.getNoteForKey(i * steps, ScaleType.SCALE_MINOR, i);

            KeySignature keySignatureMajor = getKeySignature(i);

            majorKeys.put(rootNoteMajor, keySignatureMajor);

            minorKeys.put(rootPitchMinor, keySignatureMajor);
        }
    }

    private KeySignature getKeySignature(int i) {
        KeySignature keySignatureMajor = new KeySignature();

        if (i < 0) {
            keySignatureMajor.setSignature(Accidental.FLAT);
        }
        if (i == 0) {
            keySignatureMajor.setSignature(Accidental.NATURAL);
        }
        if (i > 0) {
            keySignatureMajor.setSignature(Accidental.SHARP);
        }

        keySignatureMajor.setNumberOfAccidentals(i);
        return keySignatureMajor;
    }

    public Map<Note, KeySignature> getMajorKeys() {
        return majorKeys;
    }

    public Map<Note, KeySignature> getMinorKeys() {
        return minorKeys;
    }


}