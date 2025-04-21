package dev.cat.musictheoryfx.notefactory;

public enum Interval {

    P1(0),
    m2(1),
    M2(2),
    m3(3),
    M3(4),
    d4(4),
    P4(5),
    d5(6),
    a4(6),
    P5(7),
    a5(8),
    m6(8),
    M6(9),
    m7(10),
    M7(11),
    P8(12);

    private final int semiTones;

    Interval(int semiTones) {
        this.semiTones = semiTones;
    }

    public int getSemiTones() {
        return semiTones;
    }

}
