package com.example.teamupclienteescritorio.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PantallaPartidosControlador implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imagenFondo;

    @FXML
    private Text textoTeamUp;

    @FXML
    private StackPane tarjetaVisita;

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
    private Button botonMisPartidos;

    @FXML
    private Button botonCrearPartido;

    @FXML
    private Button menuPrincipal;

    @FXML
    private ComboBox<String> ciudades;

    @FXML
    private CheckBox soloVerificados;

    @FXML
    private Button recargarBoton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox contenedorPartidos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        imagenFondo.fitWidthProperty().bind(root.widthProperty());
        imagenFondo.fitHeightProperty().bind(root.heightProperty());

        ciudades.getItems().addAll("Madrid", "Barcelona", "Valencia", "Sevilla", "Zaragoza", "Málaga", "Murcia", "Palma", "Las Palmas", "Bilbao", "Alicante", "Córdoba", "Valladolid", "Vigo", "Gijón", "Hospitalet", "A Coruña", "Vitoria", "Granada", "Elche", "Oviedo", "Badalona", "Cartagena", "Terrassa", "Jerez", "Sabadell", "Móstoles", "Santa Cruz de Tenerife", "Pamplona", "Almería", "Alcalá de Henares", "Fuenlabrada", "Leganés", "Donostia", "Burgos", "Santander", "Castellón", "Getafe", "Albacete", "Alcorcón", "Logroño", "Badajoz", "Salamanca", "Huelva", "Marbella", "Lleida", "Tarragona", "León", "Cádiz", "Jaén", "Ourense", "Girona", "Lugo", "Santiago de Compostela", "Toledo", "Cáceres", "Melilla", "Ceuta");
    }

    @FXML
    private void verPartidos() {

        String ciudad = ciudades.getValue();

        boolean verificados = soloVerificados.isSelected();

        System.out.println("Ciudad seleccionada: " + ciudad);

        System.out.println("Solo verificados: " + verificados);

        // enviar json al servidor
    }

    @FXML
    private void irMisPartidos() {

    }

    @FXML
    private void irCrearPartido() {

    }

    @FXML
    private void irMenuPrincipal() {

    }
}