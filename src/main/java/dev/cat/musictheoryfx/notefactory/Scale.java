package dev.cat.musictheoryfx.notefactory;


import java.util.ArrayList;
import java.util.List;

public class Scale {

    private final ScaleType scaleType;
    private final Note rootNote;
    private final KeySignature keySignature;
    private final List<Note> scaleNotesAscending = new ArrayList<>();
    private final List<Note> scaleNotesDescending = new ArrayList<>();

    private static final int SEVENTH_STEP_ASC_INDEX = 6;
    private static final int SEVENTH_STEP_DESC_INDEX = 1;
    private static final int SIXTH_STEP_ASC_INDEX = 5;
    private static final int SIXTH_STEP_DESC_INDEX = 2;

    public Scale(ScaleType scaleType, Note rootNote, KeySignature keySignature) {
        this.scaleType = scaleType;
        this.rootNote = rootNote;
        this.keySignature = keySignature;

        fillScaleNotes();
    }

    private void fillScaleNotes() {

        switch (scaleType) {
            case SCALE_MAJOR -> buildMajorScale(scaleType);
            case SCALE_MINOR -> buildMinorScale(scaleType);
            case SCALE_MAJOR_HARMONIC -> buildMajorHarmonicScale(scaleType);
            case SCALE_MAJOR_MELODIC -> buildMajorMelodicScale(scaleType);
            case SCALE_MINOR_HARMONIC -> buildMinorHarmonicScale(scaleType);
            case SCALE_MINOR_MELODIC -> buildMinorMelodicScale(scaleType);

        }
    }


    private void buildMajorScale(ScaleType scaleType) {

        List<Pitch> scalePitches = getPitches(scaleType);
        populateNotesForNaturalScale(scalePitches);

    }


    private void buildMajorHarmonicScale(ScaleType scaleType) {
        buildMajorScale(ScaleType.SCALE_MAJOR);
        Note noteToDecrease = scaleNotesAscending.get(SIXTH_STEP_ASC_INDEX);
        Note newNote = Note.getDecreasedNote(noteToDecrease);
        scaleNotesAscending.set(SIXTH_STEP_ASC_INDEX, newNote);
        scaleNotesDescending.set(SIXTH_STEP_DESC_INDEX, newNote);
    }

    private void buildMajorMelodicScale(ScaleType scaleType) {
        buildMajorScale(ScaleType.SCALE_MAJOR);

        Note sixthNoteToDecrease = scaleNotesAscending.get(SIXTH_STEP_ASC_INDEX);
        Note newSixthNote = Note.getDecreasedNote(sixthNoteToDecrease);
        Note seventhNoteToDecrease = scaleNotesAscending.get(SEVENTH_STEP_ASC_INDEX);
        Note newSevenththNote = Note.getDecreasedNote(seventhNoteToDecrease);

        scaleNotesDescending.set(SIXTH_STEP_DESC_INDEX, newSixthNote);
        scaleNotesDescending.set(SEVENTH_STEP_DESC_INDEX, newSevenththNote);

    }


    private void buildMinorScale(ScaleType scaleType) {
        List<Pitch> scalePitches = getPitches(scaleType);
        populateNotesForNaturalScale(scalePitches);
    }


    private void buildMinorHarmonicScale(ScaleType scaleType) {
        buildMinorScale(ScaleType.SCALE_MINOR);
        Note noteToIncrease = scaleNotesAscending.get(SEVENTH_STEP_ASC_INDEX);
        Note newNote = Note.getIncreasedNote(noteToIncrease);
        scaleNotesAscending.set(SEVENTH_STEP_ASC_INDEX, newNote);
        scaleNotesDescending.set(SEVENTH_STEP_DESC_INDEX, newNote);
    }

    private void buildMinorMelodicScale(ScaleType scaleType) {
        buildMinorScale(ScaleType.SCALE_MINOR);
        Note sixthNoteToIncrease = scaleNotesAscending.get(SIXTH_STEP_ASC_INDEX);
        Note newSixthNote = Note.getIncreasedNote(sixthNoteToIncrease);
        Note seventhNoteToIncrease = scaleNotesAscending.get(SEVENTH_STEP_ASC_INDEX);
        Note newSevenththNote = Note.getIncreasedNote(seventhNoteToIncrease);

        scaleNotesAscending.set(SIXTH_STEP_ASC_INDEX, newSixthNote);
        scaleNotesAscending.set(SEVENTH_STEP_ASC_INDEX, newSevenththNote);


    }


    private List<Pitch> getPitches(ScaleType scaleType) {

        List<Integer> intervals = new ArrayList<>();

        for (Interval interval : scaleType.getIntervals()) {
            intervals.add(interval.getSemiTones());
        }

        return Pitch.getPitchesForScale(rootNote.getPitch(), intervals);
    }

    private void populateNotesForNaturalScale(List<Pitch> scalePitches) {

        int cursor = rootNote.getPosition();

        for (int i = 0; i < scalePitches.size(); i++) {

            Note scaleNote = Note.getNoteByPositionPitchAndSignature(
                    cursor, scalePitches.get(i), keySignature);
            scaleNotesAscending.add(scaleNote);
            if (cursor < 7) {
                cursor++;
            } else cursor = 1;
        }

        scaleNotesDescending.addAll(scaleNotesAscending.reversed());


    }


    public ScaleType getScaleType() {
        return scaleType;
    }

    public KeySignature getKeySignature() {
        return keySignature;
    }

    public Note getRootNote() {
        return rootNote;
    }

    public List<Note> getScaleNotesAscending() {
        return scaleNotesAscending;
    }

    public List<Note> getScaleNotesDescending() {
        return scaleNotesDescending;
    }



}

