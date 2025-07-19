package dev.cat.musictheoryfx.notefactory;

public enum Octave {

    SMALL(3),
    ONE_LINE(4),
    TWO_LINE(5);

    private final int octaveSPN;

    Octave(int octaveSPN) {
        this.octaveSPN = octaveSPN;
    }

    public static Octave of(int spn) {
        for (Octave o : values()) if (o.octaveSPN == spn) return o;
        throw new IllegalArgumentException("Unsupported octave (out of 3-octave range): " + spn);
    }

}
