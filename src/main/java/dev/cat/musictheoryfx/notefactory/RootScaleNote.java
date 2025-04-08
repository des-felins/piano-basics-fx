package dev.cat.musictheoryfx.notefactory;


public class RootScaleNote {

    private final Note note;
    private final ScaleType scaleType;


    public RootScaleNote(Note note, ScaleType scaleType) {
        this.note = note;
        this.scaleType = scaleType;
    }

    public Note getNote() {
        return note;
    }

    public ScaleType getScaleType() {
        return scaleType;
    }
}
