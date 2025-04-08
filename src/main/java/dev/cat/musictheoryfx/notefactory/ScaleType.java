package dev.cat.musictheoryfx.notefactory;


import java.util.List;

import static dev.cat.musictheoryfx.notefactory.Interval.*;


public enum ScaleType {

    SCALE_CHROMATIC("chromatic", List.of(P1, m2, M2, m3, M3, P4, d5, P5, m6, M6, m7, M7, P8)),
    SCALE_MAJOR("major", List.of(P1, M2, M3, P4, P5, M6, M7, P8)),
    SCALE_MAJOR_MELODIC("melodic major", List.of(P1, M2, M3, P4, P5, m6, m7, P8), ScaleType.SCALE_MAJOR),
    SCALE_MAJOR_HARMONIC("harmonic major", List.of(P1, M2, M3, P4, P5, m6, M7, P8), ScaleType.SCALE_MAJOR),
    SCALE_MINOR("minor", List.of(P1, M2, m3, P4, P5, m6, m7, P8)),
    SCALE_MINOR_MELODIC("melodic minor", List.of(P1, M2, m3, P4, P5, M6, M7, P8), ScaleType.SCALE_MINOR),
    SCALE_MINOR_HARMONIC("harmonic minor", List.of(P1, M2, m3, P4, P5, m6, M7, P8), ScaleType.SCALE_MINOR);


    private final String description;
    private final List<Interval> intervals;
    private ScaleType parentScaleType;


    ScaleType(String description, List<Interval> intervals) {
        this.description =  description;
        this.intervals = intervals;
    }

    ScaleType(String description, List<Interval> intervals, ScaleType parentScaleType) {
        this.description = description;
        this.intervals = intervals;
        this.parentScaleType = parentScaleType;
    }


    public ScaleType getParentScaleType() {
        return parentScaleType;
    }

    public List<Interval> getIntervals() {
        return intervals;
    }

    public String getDescription() {
        return description;
    }

}
