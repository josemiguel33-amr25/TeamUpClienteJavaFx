package com.example.teamupclienteescritorio.controladores;

import com.example.teamupclienteescritorio.clasesMensajes.CosmeticoSimplificado;
import com.example.teamupclienteescritorio.logicaAplicacion.Sesion;
import com.example.teamupclienteescritorio.utilidades.SistemaDeJuego;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PantallaInventarioControlador implements Initializable {

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
    private Button miPerfil;

    @FXML
    private Button botonVenderArticulo;

    @FXML
    private Button menuPrincipal;

    @FXML
    private ScrollPane tarjetas;

    @FXML
    private FlowPane contenedorCosmeticos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SistemaDeJuego.cargarDatosUsuario(imagenPerfil, imagenRango, tarjetavisita, monedas, reputacion);

        imagenFondo.fitWidthProperty().bind(root.widthProperty());
        imagenFondo.fitHeightProperty().bind(root.heightProperty());

        contenedorCosmeticos.setHgap(30);
        contenedorCosmeticos.setVgap(40);
        contenedorCosmeticos.setAlignment(Pos.TOP_CENTER);

        cargarInventario();
    }

    private void cargarInventario() {
        contenedorCosmeticos.getChildren().clear();

        for (CosmeticoSimplificado cosmetico : Sesion.getSesion().getInventarioJugador()) {
            System.out.println("TeamUp|MensajeInterno| Cosmetico actual: " + cosmetico.getTituloCosmetico());
            crearTarjetaCosmetico(cosmetico);
        }
    }

    private void crearTarjetaCosmetico(CosmeticoSimplificado cosmetico) {
        VBox tarjeta = new VBox();

        FlowPane.setMargin(tarjeta, new Insets(20, 20, 30, 20));

        tarjeta.setSpacing(10);
        tarjeta.setAlignment(Pos.CENTER);

        tarjeta.setPrefWidth(250);
        tarjeta.setPrefHeight(450);

        ImageView imagenArticulo = new ImageView();
        imagenArticulo.setFitWidth(180);
        imagenArticulo.setFitHeight(220);
        imagenArticulo.setImage(new Image(getClass().getResourceAsStream("/com/example/teamupclienteescritorio/imagenes/logo.jpg")));

        Label nombreArticulo = new Label(cosmetico.getTituloCosmetico());
        Label rareza = new Label("Rareza: " + cosmetico.getRareza());
        Label tipo = new Label("Tipo: " + cosmetico.getTipo());
        Label cantidad = new Label("Cantidad: " + cosmetico.getCantidad());

        Button equipar = new Button("Equipar");
        equipar.setUserData(cosmetico.getIdCosmetico() + "");
        equipar.setOnAction(this::equipar);

        tarjeta.getChildren().addAll(imagenArticulo, nombreArticulo, rareza, tipo, cantidad, equipar);
        contenedorCosmeticos.getChildren().add(tarjeta);
    }

    private void equipar(ActionEvent event) {
        try {
            Button boton = (Button) event.getSource();
            String idCosmetico = (String) boton.getUserData();

            Map<String, Object> mensaje = new HashMap<>();
            Map<String, String> data = new HashMap<>();

            data.put("tipoCosmeticos", "cambiarCosmetico");
            data.put("idCosmetico", idCosmetico);

            mensaje.put("tipo", "cosmeticos");
            mensaje.put("data", data);

            ObjectMapper mapper = new ObjectMapper();
            SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));
        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }

    @FXML
    private void irMiPerfil() {
        SistemaDeJuego.cambiarPantalla("pantallaPerfil.fxml");
    }

    @FXML
    private void abrirVentaArticulo() {
        SistemaDeJuego.abrirPopup("pantallaVenderArticulo.fxml", "Vender Articulo");
    }

    @FXML
    private void irMenuPrincipal() {
        SistemaDeJuego.cambiarPantalla("pantallaPrincipal.fxml");
    }
}