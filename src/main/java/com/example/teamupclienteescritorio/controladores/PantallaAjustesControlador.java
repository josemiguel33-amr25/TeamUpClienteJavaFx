package com.example.teamupclienteescritorio.controladores;

import com.example.teamupclienteescritorio.utilidades.SistemaDeJuego;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.FileOutputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class PantallaAjustesControlador implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField titulo;

    @FXML
    private TextField ubicacion;

    @FXML
    private TextField titulo1;

    @FXML
    private TextField ubicacion1;

    @FXML
    private Button botonActualizar;

    private Properties propiedades;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        propiedades = new Properties();
    }

    @FXML
    private void actualizarProperties() {

        try {
            String ipTeamUp = titulo.getText();
            String puertoTeamUp = ubicacion.getText();
            String ipHttp = titulo1.getText();
            String puertoHttp = ubicacion1.getText();

            propiedades.setProperty("ip", ipTeamUp);
            propiedades.setProperty("port", puertoTeamUp);
            propiedades.setProperty("iphttp", ipHttp);
            propiedades.setProperty("puertohttp", puertoHttp);

            FileOutputStream fos = new FileOutputStream("TeamUp/configuracion.properties");
            propiedades.store(fos, "Configuracion TeamUp");
            fos.close();

            System.out.println("TeamUp|MensajeInterno|Properties actualizadas");
            SistemaDeJuego.abrirMensaje("Configuracion actualizada");

        } catch (Exception em) {
            System.out.println("TeamUp|Error|EM4");
        }
    }

}