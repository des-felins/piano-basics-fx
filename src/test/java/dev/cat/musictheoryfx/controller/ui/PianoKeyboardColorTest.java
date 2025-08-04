package dev.cat.musictheoryfx.controller.ui;

import dev.cat.musictheoryfx.config.FxmlLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ExtendWith(ApplicationExtension.class)
class PianoKeyboardColorTest {

    @Autowired
    private ConfigurableApplicationContext context;

    @Start
    public void start(Stage stage) throws Exception {
        FxmlLoader loader = new FxmlLoader(context);
        Parent rootNode = loader.load("/fxml/in-build-keyboard.fxml");

        stage.setScene(new Scene(rootNode, 800, 600));
        stage.show();
    }

    @Test
    void shouldContainCanvas(FxRobot robot) {
        assertThat(robot.lookup("#keyboard")
                .queryAs(ResizableCanvas.class))
                .isNotNull();
    }

}