package com.example.teamupclienteescritorio.Controladores;

import com.example.teamupclienteescritorio.Utilidades.SistemaDeJuego;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import java.net.URL;
import java.util.ResourceBundle;

public class PantallaCargaControlador implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imagenFondo;

    @FXML
    private ImageView logo;

    @FXML
    private Text textoCualquierTecla;

    @FXML
    private Text textoCualquierTecla1;

    @FXML
    private Text textoCualquierTecla11;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("TeamUp|MensajeInterno|Aplicacion Iniciada");

        imagenFondo.fitWidthProperty().bind(root.widthProperty());
        imagenFondo.fitHeightProperty().bind(root.heightProperty());

        try {
            File carpeta = new File("TeamUp"); // Carpeta default que se creara para guardar archivos (token, properties)

            if (!carpeta.exists()) {
                carpeta.mkdir();
                System.out.println("TeamUp|MensajeInterno|Carpeta TeamUp creada");
            }
            File archivo = new File("TeamUp/configuracion.properties");

            if (!archivo.exists()) {
                archivo.createNewFile();
                Properties propiedades = new Properties();
                propiedades.setProperty("ip", "127.0.0.1");
                propiedades.setProperty("port", "5000");
                propiedades.setProperty("iphttp", "127.0.0.1");
                propiedades.setProperty("puertohttp", "8080");

                FileOutputStream fos = new FileOutputStream(archivo);
                propiedades.store(fos, "Configuracion TeamUp");
                fos.close();

                System.out.println("TeamUp|MensajeInterno|Properties creadas");
            }

        } catch (Exception em) {
            System.out.println("TeamUp|Error|EM5");
            em.printStackTrace();
        }
    }

    @FXML
    private void abrirAjustes() {
        SistemaDeJuego.abrirPopup("pantallaAjustes.fxml", "Ajustes");
    }
}