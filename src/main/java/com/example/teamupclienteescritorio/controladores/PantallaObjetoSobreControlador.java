package com.example.teamupclienteescritorio.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PantallaObjetoSobreControlador implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private Label rarezaCosmetico;

    @FXML
    private Label nombreCosmetico;

    @FXML
    private ImageView imagenCosmetico;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void cargarCosmetico(String nombre, String rareza, String rutaImagen) {
        nombreCosmetico.setText(nombre);
        rarezaCosmetico.setText(rareza);


        // placeholder
        imagenCosmetico.setImage(new Image(getClass().getResourceAsStream("/com/example/teamupclienteescritorio/imagenes/logo.jpg")));
    }
}