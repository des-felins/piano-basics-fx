package dev.cat.musictheoryfx.controller.ui;

import dev.cat.musictheoryfx.notefactory.Octave;
import dev.cat.musictheoryfx.notefactory.Pitch;
import javafx.scene.media.AudioClip;

public record Key(AudioClip clip, Pitch pitch, Octave octave, double frequency) {
}
