package dev.cat.musictheoryfx.notefactory;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ChordBuilder {

    private final CircleOfFifths circleOfFifths;

    private final HashMap<RootScaleNote, List<Chord>> chordsForMajorScalesFromCircle = new HashMap<>();
    private final HashMap<RootScaleNote, List<Chord>> chordsForMinorScalesFromCircle = new HashMap<>();
    ChordType[] chordTypes = ChordType.values();


    public ChordBuilder(CircleOfFifths circleOfFifths) {
        this.circleOfFifths = circleOfFifths;
        populateChordsForMajorScales();
        populateChordsForMinorScales();
    }


    private void populateChordsForMajorScales() {
        Set<Map.Entry<Note, KeySignature>> entries = circleOfFifths.getMajorKeys().entrySet();

        for (Map.Entry<Note, KeySignature> entry : entries) {

            List<Chord> chords = new ArrayList<>();

            for (ChordType chordType : chordTypes) {
                Chord chord = generateChordByChordType(entry.getKey(), entry.getValue(), ScaleType.SCALE_MAJOR, chordType);
                chords.add(chord);
            }
            chordsForMajorScalesFromCircle.put(new RootScaleNote(entry.getKey(), ScaleType.SCALE_MAJOR), chords);

        }

    }

    private void populateChordsForMinorScales() {
        Set<Map.Entry<Note, KeySignature>> entries = circleOfFifths.getMinorKeys().entrySet();

        for (Map.Entry<Note, KeySignature> entry : entries) {

            List<Chord> chords = new ArrayList<>();

            for (ChordType chordType : chordTypes) {
                Chord chord = generateChordByChordType(entry.getKey(), entry.getValue(), ScaleType.SCALE_MINOR, chordType);
                chords.add(chord);
            }
            chordsForMajorScalesFromCircle.put(new RootScaleNote(entry.getKey(), ScaleType.SCALE_MINOR), chords);

        }

    }


    public Chord generateChordByChordType(Note note, KeySignature keySignature, ScaleType scaleType, ChordType chordType) {

        return switch (chordType) {
            case AUGMENTED_TRIAD,
                 DIMINISHED_TRIAD,
                 DOMINANT_SEVENTH_CHORD,
                 DOMINANT_SIX_FIVE_CHORD,
                 DOMINANT_FOUR_THREE_CHORD,
                 DOMINANT_FOUR_TWO_CHORD -> new Chord(note, keySignature, chordType);
            default -> new Chord(scaleType, note, keySignature, chordType);
        };

    }

}
