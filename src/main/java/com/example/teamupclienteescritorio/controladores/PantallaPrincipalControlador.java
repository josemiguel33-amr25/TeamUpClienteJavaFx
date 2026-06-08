package com.example.teamupclienteescritorio.controladores;

import com.example.teamupclienteescritorio.logicaAplicacion.Sesion;
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

public class PantallaPrincipalControlador implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imagenFondo;

    @FXML
    private ImageView logo;


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
        SistemaDeJuego.cargarDatosUsuario(imagenPerfil, imagenRango, tarjetavisita, monedas, reputacion);
        imagenFondo.fitWidthProperty().bind(root.widthProperty());
        imagenFondo.fitHeightProperty().bind(root.heightProperty());
    }

    @FXML
    private void irPartidos() {
        try {
            if (Sesion.getSesion().getPartidosSimplificados().isEmpty()) { // si la lista esta vacia pues llamamos al servidor para que nos actualice la lista pero sino usamos la sesion que tenemos
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> mensaje = new HashMap<>();
                Map<String, String> datos = new HashMap<>();

                datos.put("tipoPartido", "verPartidos");
                datos.put("ciudad", "todas");
                datos.put("soloverificados", "no");

                mensaje.put("tipo", "partidos");
                mensaje.put("data", datos);

                SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));
            } else
                SistemaDeJuego.cambiarPantalla("pantallaPartidos.fxml");
        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }

    @FXML
    private void irMercado() {
        SistemaDeJuego.cambiarPantalla("pantallaMercado.fxml");
    }

    @FXML
    private void irRanking() {
        // hacer ranking
    }

    @FXML
    private void irTienda() {
        SistemaDeJuego.cambiarPantalla("pantallaTienda.fxml");
    }

    @FXML
    private void irMiPerfil() {
        // hacer mi perfil
    }

    @FXML
    private void salir() {
        SistemaDeJuego.cliente.salir();
    }
}