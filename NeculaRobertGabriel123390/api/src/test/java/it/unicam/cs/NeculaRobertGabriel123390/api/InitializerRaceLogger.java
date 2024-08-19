package it.unicam.cs.NeculaRobertGabriel123390.api;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.RaceLogger;
import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.TextFlow;

import java.util.concurrent.CountDownLatch;

public class InitializerRaceLogger {


    public static boolean initialized = false;
    public static TextFlow textFlow;
    public static ScrollPane scrollPane;

    public static void initialize() {

        if(initialized)  return;

        initialized = true;

        CountDownLatch latch = new CountDownLatch(1);

        InitializerToolkitJavaFX.initialize();

        Platform.runLater(() -> {
            textFlow = new TextFlow();
            scrollPane = new ScrollPane();
            scrollPane.setContent(textFlow);
            RaceLogger.initialize(textFlow, scrollPane);
            latch.countDown();
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

}
