package dev.cat.musictheoryfx.notefactory;


import java.util.NoSuchElementException;

public enum Note {

    C(Pitch.C, Accidental.NATURAL, "C", Pitch.C, 1, 0, "C"),
    D(Pitch.D, Accidental.NATURAL, "D", Pitch.D, 2, 0, "D"),
    E(Pitch.E, Accidental.NATURAL, "E", Pitch.E, 3, 0, "E"),
    F(Pitch.F, Accidental.NATURAL, "F", Pitch.F, 4, 0, "F"),
    G(Pitch.G, Accidental.NATURAL, "G", Pitch.G, 5, 0, "G"),
    A(Pitch.A, Accidental.NATURAL, "A", Pitch.A, 6, 0, "A"),
    B(Pitch.B, Accidental.NATURAL, "B", Pitch.B, 7, 0, "B"),

    Cis(Pitch.Cis, Accidental.SHARP, "C#", Pitch.C, 1, 1, "C-sharp"),
    Dis(Pitch.Dis, Accidental.SHARP, "D#", Pitch.D, 2, 1, "D-sharp"),
    Eis(Pitch.F, Accidental.SHARP, "E#", Pitch.E, 3, 1, "E-sharp"),
    Fis(Pitch.Fis, Accidental.SHARP, "F#", Pitch.F, 4, 1, "F-sharp"),
    Gis(Pitch.Gis, Accidental.SHARP, "G#", Pitch.G, 5, 1, "G-sharp"),
    Ais(Pitch.Ais, Accidental.SHARP, "A#", Pitch.A, 6, 1, "A-sharp"),
    Bis(Pitch.C, Accidental.SHARP, "B#", Pitch.B, 7, 1, "B-sharp"),

    Cisis(Pitch.D, Accidental.DOUBLE_SHARP, "Cx", Pitch.C, 1, 2, "C-double-sharp"),
    Disis(Pitch.E, Accidental.DOUBLE_SHARP, "Dx", Pitch.D, 2, 2, "D-double-sharp"),
    Eisis(Pitch.Fis, Accidental.DOUBLE_SHARP, "Ex", Pitch.E, 3, 2, "E-double-sharp"),
    Fisis(Pitch.G, Accidental.DOUBLE_SHARP, "Fx", Pitch.F, 4, 2, "F-double-sharp"),
    Gisis(Pitch.A, Accidental.DOUBLE_SHARP, "Gx", Pitch.G, 5, 2, "G-double-sharp"),
    Aisis(Pitch.B, Accidental.DOUBLE_SHARP, "Ax", Pitch.A, 6, 2, "A-double-sharp"),
    Bisis(Pitch.Cis, Accidental.DOUBLE_SHARP, "Bx", Pitch.B, 7, 2, "B-double-sharp"),

    Ces(Pitch.B, Accidental.FLAT, "C♭", Pitch.C, 1, -1, "C-flat"),
    Des(Pitch.Cis, Accidental.FLAT, "D♭", Pitch.D, 2, -1, "D-flat"),
    Es(Pitch.Dis, Accidental.FLAT, "E♭", Pitch.E, 3, -1, "E-flat"),
    Fes(Pitch.E, Accidental.FLAT, "F♭", Pitch.F, 4, -1, "F-flat"),
    Ges(Pitch.Fis, Accidental.FLAT, "G♭", Pitch.G, 5, -1, "G-flat"),
    As(Pitch.Gis, Accidental.FLAT, "A♭", Pitch.A, 6, -1, "A-flat"),
    Bes(Pitch.Ais, Accidental.FLAT, "B♭", Pitch.B, 7, -1, "B-flat"),

    Ceses(Pitch.Ais, Accidental.DOUBLE_FLAT, "C♭♭", Pitch.C, 1, -2, "C-double-flat"),
    Deses(Pitch.C, Accidental.DOUBLE_FLAT, "D♭♭", Pitch.D, 2, -2, "D-double-flat"),
    Eses(Pitch.D, Accidental.DOUBLE_FLAT, "E♭♭", Pitch.E, 3, -2, "E-double-flat"),
    Feses(Pitch.Dis, Accidental.DOUBLE_FLAT, "F♭♭", Pitch.F, 4, -2, "F-double-flat"),
    Geses(Pitch.F, Accidental.DOUBLE_FLAT, "G♭♭", Pitch.G, 5, -2, "G-double-flat"),
    Ases(Pitch.G, Accidental.DOUBLE_FLAT, "A♭♭", Pitch.A, 6, -2, "A-double-flat"),
    Beses(Pitch.A, Accidental.DOUBLE_FLAT, "B♭♭", Pitch.B, 7, -2, "B-double-flat");


    private final Pitch pitch;
    private final Accidental accidental;
    private final String notation;
    private final Pitch parentPitch;
    private final int position;
    private final int halfStepsFromParent;
    private final String letterName;


    Note(Pitch pitch, Accidental accidental,
         String notation, Pitch parentPitch,
         int position, int halfStepsFromParent,
         String letterName) {
        this.pitch = pitch;
        this.accidental = accidental;
        this.notation = notation;
        this.parentPitch = parentPitch;
        this.position = position;
        this.halfStepsFromParent = halfStepsFromParent;
        this.letterName = letterName;
    }

    public Pitch getParentPitch() {
        return parentPitch;
    }

    public int getPosition() {
        return position;
    }

    public int getHalfStepsFromParent() {
        return halfStepsFromParent;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public Accidental getAccidental() {
        return accidental;
    }

    public String getNotation() {
        return notation;
    }

    public String getLetterName() {
        return letterName;
    }



    public static Note getNoteForKey(int steps, ScaleType scaleType, int pointer) {

        return switch (scaleType) {
            case SCALE_MAJOR -> getNoteForMajor(steps, scaleType, pointer);
            case SCALE_MINOR -> getNoteForMinor(steps, scaleType, pointer);
            default -> throw new IllegalStateException("Unexpected value: " + scaleType);
        };

    }

    private static Note getNoteForMajor(int steps, ScaleType scaleType, int pointer) {
        Note note = null;
        Pitch rootPitchMajor = Pitch.getPitchByIntervalAndScaleType(steps, scaleType);

        if (pointer > -2) {
            for (Note candidateNote : values()) {
                if (candidateNote.name().equals(rootPitchMajor.name())) {
                    note = candidateNote;
                }
            }
        } else {
            for (Note candidateNote : values()) {
                if (candidateNote.pitch == rootPitchMajor
                        && candidateNote.accidental == Accidental.FLAT) {
                    note = candidateNote;
                }
            }
        }

        if(note == null) {
            throw new NoSuchElementException();
        }

        return note;
    }


    private static Note getNoteForMinor(int steps, ScaleType scaleType, int pointer) {
        Note note = null;
        Pitch rootPitchMinor = Pitch.getPitchByIntervalAndScaleType(steps, scaleType);

        if (pointer > -5) {
            for (Note candidateNote : values()) {
                if (candidateNote.name().equals(rootPitchMinor.name())) {
                    note = candidateNote;
                }
            }
        } else {
            for (Note candidateNote : values()) {
                if (candidateNote.pitch == rootPitchMinor
                        && candidateNote.accidental == Accidental.FLAT) {
                    note = candidateNote;
                }
            }
        }

        if(note == null) {
            throw new NoSuchElementException();
        }

        return note;
    }

    public static Note getNoteByPositionPitchAndSignature(int position, Pitch pitch, KeySignature keySignature) {
        Note candidateNote = null;

        for (Note note : values()) {
            if (note.position == position && note.pitch == pitch && note.accidental == keySignature.signature) {
                candidateNote = note;
            } else if (note.position == position && note.pitch == pitch && note.accidental == Accidental.NATURAL) {
                candidateNote = note;
            }
        }

        if(candidateNote == null) {
            throw new NoSuchElementException();
        }

        return candidateNote;
    }

    public static Note getIncreasedNote(Note noteToIncrease) {
        Note candidateNote = null;
        for (Note note : values()) {
            if (note.halfStepsFromParent - 1 == noteToIncrease.halfStepsFromParent
                    && note.parentPitch.equals(noteToIncrease.parentPitch)) {
                candidateNote = note;
            }
        }

        if(candidateNote == null) {
            throw new NoSuchElementException();
        }

        return candidateNote;
    }

    public static Note getDecreasedNote(Note noteToDecrease) {
        Note candidateNote = null;
        for (Note note : values()) {
            if (note.halfStepsFromParent + 1 == noteToDecrease.halfStepsFromParent
                    && note.parentPitch.equals(noteToDecrease.parentPitch)) {
                candidateNote = note;
            }
        }

        if(candidateNote == null) {
            throw new NoSuchElementException();
        }

        return candidateNote;
    }


}
