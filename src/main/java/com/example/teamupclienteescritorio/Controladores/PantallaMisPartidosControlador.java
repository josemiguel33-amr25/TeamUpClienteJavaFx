package com.example.teamupclienteescritorio.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PantallaMisPartidosControlador implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imagenFondo;

    @FXML
    private Text textoTeamUp;

    @FXML
    private ImageView tarjetavisita;

    @FXML
    private ImageView imagenPerfil;

    @FXML
    private ImageView imagenRango;

    @FXML
    private Label monedas;

    @FXML
    private Label reputacion;

    @FXML
    private Button botonPartidos;

    @FXML
    private Button botonCrearPartido;

    @FXML
    private Button menuPrincipal;

    @FXML
    private Button recargarBoton;

    @FXML
    private ScrollPane tarjetas;

    @FXML
    private VBox contenedorPartidos;

    @FXML
    private ComboBox<String> estadoPartidosFiltro;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        imagenFondo.fitWidthProperty().bind(root.widthProperty());

        imagenFondo.fitHeightProperty().bind(root.heightProperty());

        estadoPartidosFiltro.getItems().addAll("abierto", "terminado");
    }

    @FXML
    private void verMisPartidos() {

        String estado = estadoPartidosFiltro.getValue();

        System.out.println("Estado seleccionado: " + estado);

        // enviar json al servidor
    }

    @FXML
    private void irPartidos() {

    }

    @FXML
    private void irCrearPartido() {

    }

    @FXML
    private void irMenuPrincipal() {

    }
}