package dev.cat.musictheoryfx.notefactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Pitch {

    C(0),
    Cis(1),
    D(2),
    Dis(3),
    E(4),
    F(5),
    Fis(6),
    G(7),
    Gis(8),
    A(9),
    Ais(10),
    B(11);

    private final int position;
    static List<Pitch> pitchesClockwise = populateClockwise();
    static List<Pitch> pitchesAnticlockwise = populateAnticlockwise();

    Pitch(int position) {
        this.position = position;
    }


    private static List<Pitch> populateClockwise() {
        return Arrays.asList(Pitch.values());
    }

    private static List<Pitch> populateAnticlockwise() {
        List<Pitch> inter = Arrays.asList(Pitch.values()).reversed();

        List<Pitch> intermediate = new ArrayList<>(inter);

        intermediate.addFirst(intermediate.getLast());
        intermediate.removeLast();
        return intermediate;
    }

    public static List<Pitch> getPitchesForScale(Pitch pitch, List<Integer> intervals) {
        List<Pitch> pitches = new ArrayList<>();
        int startingPosition = pitch.getPosition();

        for (int interval : intervals) {
            int newPosition = calculateNextPosition(startingPosition, interval);
            pitches.add(pitchesClockwise.get(newPosition));
        }

        return pitches;

    }

    private static int calculateNextPosition(int pos, int interval) {
        int cursor = pos;
            for (int i = 0; i < interval; i++) {
                cursor += 1;
                if (cursor == 12) {
                    cursor = 0;
                }
            }
        return cursor;
    }

    int getPosition() {
        return position;
    }


    static Pitch getPitchByIntervalAndScaleType(int steps, ScaleType scale) {

        return switch (scale) {
            case SCALE_MAJOR -> {
                if (steps < 0) {
                    yield getPitchForFlat(Math.abs(steps));
                } else yield getPitchForSharp(steps);
            }
            case SCALE_MINOR -> {
                if (steps < 0) {
                    yield getPitchForFlat(Math.abs(steps) + 3);
                } else yield getPitchForSharp(Math.abs(steps) + 9);
            }
            default -> throw new IllegalStateException("Unexpected value: " + scale);
        };

    }

    private static Pitch getPitchForSharp(int steps) {
        int position = 0;

            for (int i = 0; i < steps; i++) {
                position += 1;
                if (position == 12) {
                    position = 0;
                }
            }
        return pitchesClockwise.get(position);
    }


    private static Pitch getPitchForFlat(int steps) {
        int position = 0;
        for (int i = 0; i < steps; i++) {
            position += 1;
            if (position == 12) {
                position = 0;
            }
        }
        return pitchesAnticlockwise.get(position);
    }

    Pitch getPitchByPosition(int position) {
        return pitchesClockwise.get(position);
    }

}
