package dev.cat.musictheoryfx.controller.ui;

import dev.cat.musictheoryfx.notefactory.Octave;
import dev.cat.musictheoryfx.notefactory.Pitch;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;

public record Key(AudioClip audioClip,
                  Pitch pitch,
                  Octave octave,
                  KeyCode keyCode) {
}
