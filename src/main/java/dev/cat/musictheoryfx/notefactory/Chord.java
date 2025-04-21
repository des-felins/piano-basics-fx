package dev.cat.musictheoryfx.notefactory;

import java.util.ArrayList;
import java.util.List;

public class Chord {

    private ScaleType scaleType;
    private Note rootNote;
    private ChordType chordType;
    private final List<Note> chordNotes = new ArrayList<>();

    public Chord(ScaleType scaleType, Note rootNote, ChordType chordType) {
        this.scaleType = scaleType;
        this.rootNote = rootNote;
        this.chordType = chordType;
        getChordByScaleType(rootNote, scaleType, chordType);
    }

    public Chord(Note rootNote, ChordType chordType) {
        this.rootNote = rootNote;
        this.chordType = chordType;
        scaleType = null;
        getChordForNote(rootNote, chordType);
    }



    public void getChordForNote(Note note, ChordType chordType) {

    }

    public void getChordByScaleType(Note note, ScaleType scaleType, ChordType chordType) {

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
}
