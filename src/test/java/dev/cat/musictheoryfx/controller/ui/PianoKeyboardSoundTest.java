package dev.cat.musictheoryfx.controller.ui;

import dev.cat.musictheoryfx.config.FxmlLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ExtendWith(ApplicationExtension.class)
public class PianoKeyboardSoundTest {

    @Autowired
    private ConfigurableApplicationContext context;

    ResizableCanvas canvas;
    List<Key> keys;

    @Start
    public void start(Stage stage) throws Exception {
        FxmlLoader loader = new FxmlLoader(context);
        Parent rootNode = loader.load("/fxml/in-build-keyboard.fxml");

        keys = SoundBuilder.getInstance().getKeys();

        stage.setScene(new Scene(rootNode, 800, 600));
        stage.show();
        WaitForAsyncUtils.waitForFxEvents();
    }

    @BeforeEach
    void focusCanvas(FxRobot robot) {
        canvas = robot.lookup("#keyboard").queryAs(ResizableCanvas.class);
        robot.interact(canvas::requestFocus);
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void shouldPlayClipWhenPressed(FxRobot robot) throws Exception {
        AudioClip q = getClipForCode(KeyCode.Q);
        robot.press(KeyCode.Q);

        WaitForAsyncUtils.waitFor(2, TimeUnit.SECONDS,
                () -> WaitForAsyncUtils.asyncFx(q::isPlaying).get());

        assertThat(q.isPlaying()).isTrue();

        robot.release(KeyCode.Q);
        WaitForAsyncUtils.waitFor(2, TimeUnit.SECONDS,
                () -> !WaitForAsyncUtils.asyncFx(q::isPlaying).get());

        assertThat(q.isPlaying()).isFalse();
    }




    private AudioClip getClipForCode(KeyCode code) {
        return keys.stream()
                .filter(k -> k.keyCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new AssertionError("No Key for " + code))
                .audioClip();
    }




}
