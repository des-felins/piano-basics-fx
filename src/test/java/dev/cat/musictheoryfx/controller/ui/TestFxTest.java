package dev.cat.musictheoryfx.controller.ui;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(ApplicationExtension.class)
public class TestFxTest {

    @Start
    void start(Stage stage) { /* no-op */ }

    @Test
    void robot_is_injected(FxRobot robot) {
        assertThat(robot).isNotNull();
    }

}
