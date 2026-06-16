package com.example.teamupclienteescritorio.logicaAplicacion;

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;

public class EscuchadorMensajes implements  Runnable {
    private BufferedReader lector;
    private ProcesadorMensajes procesadorMensajes;

    public EscuchadorMensajes(BufferedReader lector, ProcesadorMensajes procesadorMensajes) {
        this.procesadorMensajes = procesadorMensajes;
        this.lector = lector;
    }

    @Override
    public void run() {
        String mensaje;
        try
        {
            while ((mensaje = lector.readLine()) != null) {
                String mensajeFinal = mensaje; //Si no lo hago asi no funcionab
                // uso platform run later porque para poder tocar cosas de la interfaz necesito que este desde el hilo de javafx sino explota
                Platform.runLater(() -> {
                    procesadorMensajes.buzon(mensajeFinal);
                });
            }
        } catch (IOException em) {
            System.out.println("TeamUp|Error|EM0");
        }
    }


}
