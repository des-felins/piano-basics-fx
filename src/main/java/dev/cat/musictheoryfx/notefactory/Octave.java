package dev.cat.musictheoryfx.notefactory;

public enum Octave {

    SMALL(3),
    ONE_LINE(4),
    TWO_LINE(5);

    private final int octaveSPN;

    Octave(int octaveSPN) {
        this.octaveSPN = octaveSPN;
    }
}
