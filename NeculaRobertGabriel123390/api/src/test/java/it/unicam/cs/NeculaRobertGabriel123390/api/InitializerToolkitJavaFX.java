package it.unicam.cs.NeculaRobertGabriel123390.api;

import javafx.application.Platform;

public class InitializerToolkitJavaFX {

    public static boolean initialized = false;


    public static void initialize(){

        if(initialized) return;
        initialized = true;

        Platform.startup(() -> {

        });

    }
}
