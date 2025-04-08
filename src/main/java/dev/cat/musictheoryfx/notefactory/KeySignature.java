package dev.cat.musictheoryfx.notefactory;

public class KeySignature {

    Accidental signature;
    int numberOfAccidentals;

    public KeySignature() {
    }

    public Accidental getSignature() {
        return signature;
    }

    public void setSignature(Accidental signature) {
        this.signature = signature;
    }

    public int getNumberOfAccidentals() {
        return numberOfAccidentals;
    }

    public void setNumberOfAccidentals(int numberOfAccidentals) {
        this.numberOfAccidentals = numberOfAccidentals;
    }

}
