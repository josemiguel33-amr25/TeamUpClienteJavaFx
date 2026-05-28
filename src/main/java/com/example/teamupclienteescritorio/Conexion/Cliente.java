package com.example.teamupclienteescritorio.Conexion;

import javafx.application.Platform;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class Cliente {
    private Socket socket;
    private PrintWriter salida;
    private BufferedReader lector;
    private Properties propiedades;

    public Cliente() {
        try {
            propiedades = new Properties();
            cargarPropiedades();
            socket = new Socket(propiedades.getProperty("ip"), Integer.parseInt(propiedades.getProperty("port")));
            salida = new PrintWriter(socket.getOutputStream(), true);
            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            iniciarHiloBuzon();
        } catch (IOException em1) {
            System.out.println("TeamUp|Error|EM0");
        }
    }

    public void iniciarHiloBuzon() {
        Thread hiloEscucha = new Thread(() -> {
            String mensaje;
            try
            {
                while ((mensaje = lector.readLine()) != null) {
                    String mensajeFinal = mensaje; //Si no lo hago asi no funcionab
                    Platform.runLater(() -> {
                        // hacer buzon
                    });
                }
            } catch (IOException em) {
                System.out.println("TeamUp|Error|EM0");
            }
        });

        hiloEscucha.setDaemon(true);
        hiloEscucha.start();
    }

    public void enviarMensaje(String mensaje) {
        if (salida != null) {
            salida.println(mensaje);
        } else {
            System.out.println("TeamUp|Error|EM2");
        }
    }

    public void cargarPropiedades() {
        try {
            System.out.println("TeamUp|MensajeInterno| Entro en cargar propiedades");
            FileInputStream fis = new FileInputStream("TeamUp/configuracion.properties");
            propiedades.load(fis);
            System.out.println("TeamUp|MensajeInterno| propiedades puerto " + propiedades.getProperty("port"));
        } catch (Exception em) {
            System.out.println("TeamUp|Error|EM1|");
        }
    }



    public void salir() {
        // cuando llamamos a esta funcion ademas de salir y cerrar la aplicacion tambien cerramos la sesion con el servidor llamando a la funcion del servidor
        Platform.exit();
    }
}
