package com.example.teamupclienteescritorio.controladores;

import com.example.teamupclienteescritorio.utilidades.SistemaDeJuego;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PantallaTiendaControlador implements Initializable {

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
    private Button misSobres;

    @FXML
    private Button verSobres;

    @FXML
    private Button menuPrincipal;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SistemaDeJuego.cargarDatosUsuario(imagenPerfil, imagenRango, tarjetavisita, monedas, reputacion);
        imagenFondo.fitWidthProperty().bind(root.widthProperty());
        imagenFondo.fitHeightProperty().bind(root.heightProperty());
    }


    @FXML
    private void irMisSobres() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> mensaje = new HashMap<>();
            Map<String, String> datos = new HashMap<>();

            datos.put("tipoCosmeticos", "verSobres");
            datos.put("tipo", "usuario");

            mensaje.put("tipo", "cosmeticos");
            mensaje.put("data", datos);

            SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));
        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }

    @FXML
    private void irVerSobres() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> mensaje = new HashMap<>();
            Map<String, String> datos = new HashMap<>();

            datos.put("tipoCosmeticos", "verSobres");
            datos.put("tipo", "tienda");

            mensaje.put("tipo", "cosmeticos");
            mensaje.put("data", datos);

            SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));
        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }


    @FXML
    private void irMenuPrincipal() {
        SistemaDeJuego.cambiarPantalla("pantallaPrincipal.fxml");
    }
}