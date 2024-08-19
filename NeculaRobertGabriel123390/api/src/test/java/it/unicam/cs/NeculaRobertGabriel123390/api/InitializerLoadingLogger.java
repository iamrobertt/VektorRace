package it.unicam.cs.NeculaRobertGabriel123390.api;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.LoadingLogger;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.concurrent.CountDownLatch;

public class InitializerLoadingLogger {

    public static boolean initialized = false;
    public static TextArea textArea;

    private InitializerLoadingLogger(){}


    public static void initialize() {

        InitializerToolkitJavaFX.initialize();

        if(initialized) return;

        initialized = true;

        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            textArea = new TextArea();
            LoadingLogger.initialize(textArea);
            latch.countDown();
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
