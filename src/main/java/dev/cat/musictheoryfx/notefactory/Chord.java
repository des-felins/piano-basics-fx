package dev.cat.musictheoryfx.notefactory;

import java.util.ArrayList;
import java.util.List;

public class Chord {

    private ScaleType scaleType;
    private Note rootNote;
    private ChordType chordType;
    private final KeySignature keySignature;
    private final List<Note> chordNotes = new ArrayList<>();

    public Chord(ScaleType scaleType, Note rootNote, KeySignature keySignature, ChordType chordType) {
        this.scaleType = scaleType;
        this.rootNote = rootNote;
        this.chordType = chordType;
        this.keySignature = keySignature;
        getChordByScaleType(rootNote, keySignature, scaleType, chordType);
    }

    public Chord(Note rootNote, KeySignature keySignature, ChordType chordType) {
        this.rootNote = rootNote;
        this.chordType = chordType;
        this.keySignature = keySignature;
        scaleType = null;
        getChordForNote(rootNote, keySignature, chordType);
    }


    public void getChordForNote(Note note, KeySignature keySignature, ChordType chordType) {

    }

    public void getChordByScaleType(Note note, KeySignature keySignature, ScaleType scaleType, ChordType chordType) {

    }

    public ScaleType getScaleType() {
        return scaleType;
    }

    public Note getRootNote() {
        return rootNote;
    }

    public ChordType getChordType() {
        return chordType;
    }

    public List<Note> getChordNotes() {
        return chordNotes;
    }

    public KeySignature getKeySignature() {
        return keySignature;
    }
}
