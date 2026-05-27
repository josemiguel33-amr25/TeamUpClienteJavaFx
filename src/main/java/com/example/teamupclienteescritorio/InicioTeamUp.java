package com.example.teamupclienteescritorio;

import com.example.teamupclienteescritorio.Utilidades.SistemaDeJuego;
import javafx.application.Application;
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

            if (event.getCode() == KeyCode.SPACE) {

                System.out.println("TeamUp|MensajeInterno|Tecla detectada");

                SistemaDeJuego.crearCliente();

                SistemaDeJuego.cambiarPantalla("pantallaLogReg.fxml");
            }
        });

        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();

        scene.getRoot().requestFocus();
    }
}
