package com.example.teamupclienteescritorio.controladores;

import com.example.teamupclienteescritorio.utilidades.SistemaDeJuego;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PantallaMercadoControlador implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imagenFondo;

    @FXML
    private ImageView tarjetavisita;

    @FXML
    private ImageView imagenPerfil;

    @FXML
    private ImageView imagenRango;

    @FXML
    private ImageView logo;

    @FXML
    private Label monedas;

    @FXML
    private Label reputacion;

    @FXML
    private Button misArticulos;

    @FXML
    private Button botonVenderArticulo;

    @FXML
    private Button menuPrincipal;

    @FXML
    private ScrollPane tarjetas;

    @FXML
    private VBox contenedorCosmeticos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SistemaDeJuego.cargarDatosUsuario(imagenPerfil, imagenRango, tarjetavisita, monedas, reputacion);
        imagenFondo.fitWidthProperty().bind(root.widthProperty());
        imagenFondo.fitHeightProperty().bind(root.heightProperty());
    }


    @FXML
    private void verMisArticulos() {

    }

    @FXML
    private void abrirVentaArticulo() {
        SistemaDeJuego.abrirPopup("pantallaVentaArticulo.fxml", "Vender Articulo");
    }

    @FXML
    private void irMenuPrincipal() {
        SistemaDeJuego.cambiarPantalla("pantallaPrincipal.fxml");
    }
}