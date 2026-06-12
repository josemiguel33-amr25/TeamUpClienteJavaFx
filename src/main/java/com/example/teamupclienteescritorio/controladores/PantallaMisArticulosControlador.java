package com.example.teamupclienteescritorio.controladores;

import com.example.teamupclienteescritorio.clasesMensajes.CosmeticoSimplificado;
import com.example.teamupclienteescritorio.clasesMensajes.MercadoSimplificado;
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

public class PantallaMisArticulosControlador implements Initializable {

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
    private Button botonIrMercado;

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
        contenedorCosmeticos.setPadding(new Insets(40, 40, 100, 40));

        cargarMercado();
    }

    private void cargarMercado() {
        contenedorCosmeticos.getChildren().clear();

        for (MercadoSimplificado articulo : Sesion.getSesion().getObjetosMercadosGlobal()) {
            crearTarjetaArticulo(articulo);
        }
    }

    private void crearTarjetaArticulo(MercadoSimplificado articulo) {
        VBox tarjeta = new VBox();

        FlowPane.setMargin(tarjeta, new Insets(20, 20, 30, 20));

        tarjeta.setSpacing(10);
        tarjeta.setAlignment(Pos.CENTER);


        tarjeta.setPrefWidth(250);
        tarjeta.setPrefHeight(400);

        ImageView imagenArticulo = new ImageView();
        imagenArticulo.setFitWidth(180);
        imagenArticulo.setFitHeight(220);

        // placeholder por ahora
        imagenArticulo.setImage(new Image(getClass().getResourceAsStream("/com/example/teamupclienteescritorio/imagenes/logo.jpg")));

        Label vendedor = new Label("Vendedor: " + articulo.getNombreVendedor());
        Label nombreArticulo = new Label(articulo.getNombreArticulo());
        Label precio = new Label("💰 " + articulo.getPrecio() + " monedas");

        Button quitar = new Button("Quitar del Mercado");
        quitar.setUserData(articulo.getIdArticulo());
        quitar.setOnAction(this::quitarDelMercado);

        tarjeta.getChildren().addAll(imagenArticulo, vendedor, nombreArticulo, precio, quitar);

        contenedorCosmeticos.getChildren().add(tarjeta);
    }

    private void quitarDelMercado(ActionEvent event) {
        try {
            Button boton = (Button) event.getSource();
            int idArticulo = (int) boton.getUserData();

            Map<String, Object> mensaje = new HashMap<>();
            Map<String, String> data = new HashMap<>();

            data.put("tipoCosmeticos", "quitarArticulo");
            data.put("idArticuloMercado", String.valueOf(idArticulo));

            mensaje.put("tipo", "cosmeticos");
            mensaje.put("data", data);

            ObjectMapper mapper = new ObjectMapper();

            SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));
        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }
    @FXML
    private void irMercado() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> mensaje = new HashMap<>();
            Map<String, String> datos = new HashMap<>();

            datos.put("tipoCosmeticos", "mercado");

            mensaje.put("tipo", "cosmeticos");
            mensaje.put("data", datos);

            SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));
        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }

    @FXML // funcion lo mismo que pasa en la otra parte lo he simplificado para darle a un boton
    private void abrirVentaArticulo() {
        SistemaDeJuego.abrirPopup("pantallaVentaArticulo.fxml", "Vender Articulo");
    }

    @FXML
    private void irMenuPrincipal() {
        SistemaDeJuego.cambiarPantalla("pantallaPrincipal.fxml");
    }
}