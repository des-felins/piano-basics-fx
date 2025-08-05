package dev.cat.musictheoryfx.controller.ui;

import dev.cat.musictheoryfx.config.FxmlLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
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

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ExtendWith(ApplicationExtension.class)
class PianoKeyboardColorTest {

    @Autowired
    private ConfigurableApplicationContext context;

    ResizableCanvas canvas;
    Map<Character, Point2D> centers;

    @Start
    public void start(Stage stage) throws Exception {
        FxmlLoader loader = new FxmlLoader(context);
        Parent rootNode = loader.load("/fxml/in-build-keyboard.fxml");

        stage.setScene(new Scene(rootNode, 800, 600));
        stage.show();
        WaitForAsyncUtils.waitForFxEvents();
    }

    @BeforeEach
    void focusCanvasAndWaitForGeometry(FxRobot robot) throws TimeoutException {
        canvas = robot.lookup("#keyboard").queryAs(ResizableCanvas.class);
        robot.interact(canvas::requestFocus);

        WaitForAsyncUtils.waitFor(3, TimeUnit.SECONDS,
                () -> !getCenters(canvas).isEmpty());

        centers = getCenters(canvas);
    }

    @Test
    void shouldContainCanvas(FxRobot robot) {
        assertThat(robot.lookup("#keyboard")
                .queryAs(ResizableCanvas.class))
                .isNotNull();
    }

    @Test
    void shouldHighLightPressedKey(FxRobot robot) throws Exception {
        Point2D point = Objects.requireNonNull(centers.get('Q'));
        Color before = samplePixel(canvas, point);

        robot.press(KeyCode.Q);

        WaitForAsyncUtils.waitFor(2, TimeUnit.SECONDS,
                () -> samplePixel(canvas, point) != before);

        Color duringPress = samplePixel(canvas, point);
        assertThat(before.equals(duringPress)).isFalse();
    }

    @Test
    void shouldReturnToNormalColorReleasedKey(FxRobot robot) throws Exception {

        Point2D point = Objects.requireNonNull(centers.get('Q'));
        Color before = samplePixel(canvas, point);

        robot.press(KeyCode.Q);

        WaitForAsyncUtils.waitFor(2, TimeUnit.SECONDS,
                () -> samplePixel(canvas, point) != before);

        Color duringPress = samplePixel(canvas, point);

        robot.release(KeyCode.Q);
        WaitForAsyncUtils.waitFor(2, TimeUnit.SECONDS,
                () -> samplePixel(canvas, point) != duringPress);

        Color after = samplePixel(canvas, point);
        assertThat(before.equals(after)).isTrue();

    }

    @Test
    void shouldHighlightMultiplePressedKeysSimultaneously(FxRobot robot) throws Exception {

        Point2D qPoint = Objects.requireNonNull(centers.get('Q'));
        Point2D wPoint = Objects.requireNonNull(centers.get('W'));
        Color qBefore = samplePixel(canvas, qPoint);
        Color wBefore = samplePixel(canvas, wPoint);

        robot.press(KeyCode.Q, KeyCode.W);

        WaitForAsyncUtils.waitFor(2, TimeUnit.SECONDS,
                () -> samplePixel(canvas, qPoint) != qBefore);

        Color qDuringPress = samplePixel(canvas, qPoint);
        Color wDuringPress = samplePixel(canvas, wPoint);

        assertThat(qBefore.equals(qDuringPress)).isFalse();
        assertThat(wBefore.equals(wDuringPress)).isFalse();
    }

    @Test
    void shouldManageColorOfMultiplePressedReleasedKeys(FxRobot robot) throws Exception {

        Point2D qPoint = Objects.requireNonNull(centers.get('Q'));
        Point2D wPoint = Objects.requireNonNull(centers.get('W'));
        Color qBefore = samplePixel(canvas, qPoint);
        Color wBefore = samplePixel(canvas, wPoint);

        robot.press(KeyCode.Q, KeyCode.W);

        WaitForAsyncUtils.waitFor(2, TimeUnit.SECONDS,
                () -> samplePixel(canvas, qPoint) != qBefore);

        robot.release(KeyCode.Q);

        WaitForAsyncUtils.waitFor(2, TimeUnit.SECONDS,
                () -> samplePixel(canvas, qPoint).equals(qBefore));

        Color wStill = samplePixel(canvas, wPoint);
        assertThat(wBefore.equals(wStill)).isFalse();

    }


    @SuppressWarnings("unchecked")
    private Map<Character, Point2D> getCenters(Canvas canvas) {
        Map<Character, Point2D> centers =
                (Map<Character, Point2D>) canvas.getProperties().get("keyCenters");
        if (centers == null) {
            throw new IllegalStateException("keyCenters not available yet on #keyboard");
        }
        return centers;
    }

    private static Color samplePixel(Canvas canvas, Point2D p) throws Exception {

        return WaitForAsyncUtils.asyncFx(() -> {

            WritableImage img = canvas.snapshot(new SnapshotParameters(), null);
            PixelReader pr = img.getPixelReader();
            int x = (int) Math.round(p.getX());
            int y = (int) Math.round(p.getY());
            x = Math.max(0, Math.min(x, (int) canvas.getWidth() - 1));
            y = Math.max(0, Math.min(y, (int) canvas.getHeight() - 1));
            return pr.getColor(x, y);
        }).get();
    }

}