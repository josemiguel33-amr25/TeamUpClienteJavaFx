package com.example.teamupclienteescritorio;

import com.example.teamupclienteescritorio.utilidades.SistemaDeJuego;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioTeamUp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        SistemaDeJuego.stagePrincipal = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(InicioTeamUp.class.getResource("pantallaCarga.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        stage.setTitle("TeamUp V.1");

        scene.setOnKeyPressed(event -> {

            if (event.getCode() == KeyCode.SPACE) { // eventos para pasar de la pantalla de carga adelante dandole al espacio + comprobacion cliente

                System.out.println("TeamUp|MensajeInterno|Tecla detectada");

                SistemaDeJuego.crearCliente();

                if (SistemaDeJuego.cliente.isConectado())
                    SistemaDeJuego.cambiarPantalla("pantallaLogReg.fxml");
                else
                    SistemaDeJuego.abrirMensaje("No te has podido conectar al servidor porque no se encuentra disponible, ajusta la direccion en ajustes");
            }
        });

        stage.setOnCloseRequest(event -> {

            if (SistemaDeJuego.cliente != null) {
                SistemaDeJuego.cliente.salir();
            } else {
                Platform.exit();
            }
        });

        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();

        scene.getRoot().requestFocus();
    }
}
