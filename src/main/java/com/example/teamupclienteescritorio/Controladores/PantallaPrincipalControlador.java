package com.example.teamupclienteescritorio.Controladores;

import com.example.teamupclienteescritorio.Utilidades.SistemaDeJuego;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PantallaPrincipalControlador implements Initializable {

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
    private Button partidos;

    @FXML
    private Button mercado;

    @FXML
    private Button ranking;

    @FXML
    private Button rangos;

    @FXML
    private Button tienda;

    @FXML
    private Button miPerfil;

    @FXML
    private Button salir;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        imagenFondo.fitWidthProperty().bind(root.widthProperty());
        imagenFondo.fitHeightProperty().bind(root.heightProperty());
    }

    @FXML
    private void irPartidos() {

    }

    @FXML
    private void irMercado() {

    }

    @FXML
    private void irRanking() {

    }

    @FXML
    private void irTienda() {

    }

    @FXML
    private void irMiPerfil() {

    }

    @FXML
    private void salir() {
        SistemaDeJuego.cliente.salir();
    }
}