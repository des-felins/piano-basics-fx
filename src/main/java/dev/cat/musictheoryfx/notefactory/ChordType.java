package dev.cat.musictheoryfx.notefactory;

import java.util.List;

import static dev.cat.musictheoryfx.notefactory.Interval.*;

public enum ChordType {

    TRIAD_MAJOR("Major Triad", List.of(P1, M3, P5), ScaleType.SCALE_MAJOR),
    TONIC_TRIAD_MAJOR("Major Tonic Triad", ChordType.TRIAD_MAJOR),
    SUBDOMINANT_TRIAD_MAJOR("Major Subdominant Triad", ChordType.TRIAD_MAJOR),
    DOMINANT_TRIAD_MAJOR("Major Dominant Triad", ChordType.TRIAD_MAJOR),

    TRIAD_MINOR("Minor Triad", List.of(P1, m3, P5), ScaleType.SCALE_MAJOR),
    TONIC_TRIAD_MINOR("Minor Tonic Triad", ChordType.TRIAD_MINOR),
    SUBDOMINANT_TRIAD_MINOR("Minor Subdominant Triad", ChordType.TRIAD_MINOR),
    DOMINANT_TRIAD_MINOR("Minor Dominant Triad", ChordType.TRIAD_MINOR),

    DOMINANT_SEVENTH_CHORD("7th Chord", List.of(P1, M3, P5, m7)),

    SIXTH_MAJOR("Major 6th Chord", List.of(), ScaleType.SCALE_MAJOR),

    SIXTH_MINOR("Minor 6th Chord", List.of(), ScaleType.SCALE_MINOR),

    SIX_FOUR_MAJOR("Major 6/4 Chord", List.of(), ScaleType.SCALE_MAJOR),

    SIX_FOUR_MINOR("Minor 6/4 Chord", List.of(), ScaleType.SCALE_MINOR);

    //G B D F

    private String description;
    private List<Interval> intervals;
    private ScaleType scaleType;
    private ChordType parentChord;

    ChordType(String description, List<Interval> intervals, ScaleType scaleType) {
        this.description = description;
        this.intervals = intervals;
        this.scaleType = scaleType;
    }

    ChordType(String description, ChordType parentChord) {
        this.description = description;
        this.parentChord = parentChord;

    }

    ChordType(String description, List<Interval> intervals) {
        this.description = description;
        this.intervals = intervals;
    }

    public String getDescription() {
        return description;
    }

    public List<Interval> getIntervals() {
        return intervals;
    }

    public ScaleType getScaleType() {
        return scaleType;
    }
}
