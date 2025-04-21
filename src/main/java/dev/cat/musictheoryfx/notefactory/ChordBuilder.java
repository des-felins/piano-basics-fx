package dev.cat.musictheoryfx.notefactory;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class ChordBuilder {

    private final CircleOfFifths circleOfFifths;

    private final HashMap<RootScaleNote, List<Chord>> chordsForMajorScalesFromCircle = new HashMap<>();
    private final HashMap<RootScaleNote, List<Chord>> chordsForMinorScalesFromCircle = new HashMap<>();


    public ChordBuilder(CircleOfFifths circleOfFifths) {
        this.circleOfFifths = circleOfFifths;
        populateChordsForMajorScales();
        populateChordsForMinorScales();
    }


    private void populateChordsForMajorScales() {
        Set<Map.Entry<Note, KeySignature>> entries = circleOfFifths.getMajorKeys().entrySet();



    }

    private void populateChordsForMinorScales() {
        Set<Map.Entry<Note, KeySignature>> entries = circleOfFifths.getMinorKeys().entrySet();


    }


    public Chord generateChordByChordType(Note note, ScaleType scaleType, ChordType chordType) {

        return switch (chordType) {
            case AUGMENTED_TRIAD,
                 DIMINISHED_TRIAD,
                 DOMINANT_SEVENTH_CHORD,
                 DOMINANT_SIX_FIVE_CHORD,
                 DOMINANT_FOUR_THREE_CHORD,
                 DOMINANT_FOUR_TWO_CHORD -> new Chord(note, chordType);
            default -> new Chord(scaleType, note, chordType);
        };

    }

}
