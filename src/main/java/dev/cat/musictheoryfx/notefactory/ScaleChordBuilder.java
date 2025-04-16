package dev.cat.musictheoryfx.notefactory;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ScaleChordBuilder {

    private final CircleOfFifths circleOfFifths;

    private final HashMap<RootScaleNote, List<Scale>> majorScalesFromCircle = new HashMap<>();
    private final HashMap<RootScaleNote, List<Scale>> minorScalesFromCircle = new HashMap<>();
    private final HashMap<RootScaleNote, List<Chord>> chordsForScalesFromCircle = new HashMap<>();

    public ScaleChordBuilder(CircleOfFifths circleOfFifths) {
        this.circleOfFifths = circleOfFifths;
        populateMajorScales();
        populateMinorScales();
    }


    private void populateMajorScales() {
        Set<Map.Entry<Note, KeySignature>> entries = circleOfFifths.getMajorKeys().entrySet();
        for (Map.Entry<Note, KeySignature> entry : entries) {

            Scale scaleNaturalMajor = new Scale(ScaleType.SCALE_MAJOR, entry.getKey(), entry.getValue());
            Scale scaleHarmonicMajor = new Scale(ScaleType.SCALE_MAJOR_HARMONIC, entry.getKey(), entry.getValue());
            Scale scaleMelodicMajor = new Scale(ScaleType.SCALE_MAJOR_MELODIC, entry.getKey(), entry.getValue());

            List<Scale> majorScales = new ArrayList<>();
            majorScales.add(scaleNaturalMajor);
            majorScales.add(scaleHarmonicMajor);
            majorScales.add(scaleMelodicMajor);

            majorScalesFromCircle.put(
                    new RootScaleNote(scaleNaturalMajor.getRootNote(), scaleNaturalMajor.getScaleType()),
                    majorScales);
        }
    }

    private void populateMinorScales() {
        Set<Map.Entry<Note, KeySignature>> entries = circleOfFifths.getMinorKeys().entrySet();

        for (Map.Entry<Note, KeySignature> entry : entries) {

            Scale scaleNaturalMinor = new Scale(ScaleType.SCALE_MINOR, entry.getKey(), entry.getValue());
            Scale scaleHarmonicMinor = new Scale(ScaleType.SCALE_MINOR_HARMONIC, entry.getKey(), entry.getValue());
            Scale scaleMelodicMinor = new Scale(ScaleType.SCALE_MINOR_MELODIC, entry.getKey(), entry.getValue());

            List<Scale> minorScales = new ArrayList<>();
            minorScales.add(scaleNaturalMinor);
            minorScales.add(scaleHarmonicMinor);
            minorScales.add(scaleMelodicMinor);


            minorScalesFromCircle.put(
                    new RootScaleNote(scaleNaturalMinor.getRootNote(), scaleNaturalMinor.getScaleType()),
                    minorScales);
        }
    }




    public HashMap<RootScaleNote, List<Scale>> getMajorScalesFromCircle() {
        return majorScalesFromCircle;
    }

    public HashMap<RootScaleNote, List<Scale>> getMinorScalesFromCircle() {
        return minorScalesFromCircle;
    }


}
