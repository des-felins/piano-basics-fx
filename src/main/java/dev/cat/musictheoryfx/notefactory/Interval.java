package dev.cat.musictheoryfx.notefactory;

public enum Interval {

    P1(0, "Perfect Unison (P1)"),
    m2(1, "Minor Second (m2)"),
    M2(2, "Major Second (M2)"),
    m3(3, "Minor Third (m3)"),
    M3(4, "Major Third (M3)"),
    d4(4, "Diminished Fourth (d4)"),
    P4(5, "Perfect Fourth (P4)"),
    d5(6, "Triton (aug4/dim5)"),
    a4(6, "Triton (aug4/dim5)"),
    P5(7, "Perfect Fifth (P5)"),
    m6(8, "Minor Sixth (m6)"),
    a5(8, "Augmented Fifth (a5)"),
    M6(9, "Major Sixth (M6)"),
    m7(10, "Minor Seventh (m7)"),
    M7(11, "Major Seventh (M7)"),
    P8(12, "Perfect Octave (P8)");

    private final int semiTones;
    private final String description;

    Interval(int semiTones, String description) {
        this.semiTones = semiTones;
        this.description = description;
    }

    public int getSemiTones() {
        return semiTones;
    }

    public String getDescription() {
        return description;
    }

    public static Interval getIntervalBySemitones(int semiTones) {
        Interval foundInterval = null;
        for (Interval interval : values()) {
            if (interval.getSemiTones() == semiTones) {
                foundInterval = interval;
                break;
            }
        }
        return foundInterval;
    }
}
