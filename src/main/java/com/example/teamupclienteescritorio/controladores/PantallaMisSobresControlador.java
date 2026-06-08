package com.example.teamupclienteescritorio.controladores;

import com.example.teamupclienteescritorio.clasesMensajes.SobreSimplificado;
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
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PantallaMisSobresControlador implements Initializable {

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
    private Button menuPrincipal;

    @FXML
    private Button misSobres;

    @FXML
    private ScrollPane contenedorScrollPane;

    @FXML
    private FlowPane contenedorMisSobres;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SistemaDeJuego.cargarDatosUsuario(imagenPerfil, imagenRango, tarjetavisita, monedas, reputacion);
        imagenFondo.fitWidthProperty().bind(root.widthProperty());
        imagenFondo.fitHeightProperty().bind(root.heightProperty());

        contenedorMisSobres.setHgap(30);
        contenedorMisSobres.setVgap(40);
        contenedorMisSobres.setAlignment(Pos.TOP_CENTER);
        contenedorMisSobres.setPadding(new Insets(50, 25, 100, 25));

        cargarSobres();
    }

    private void cargarSobres() {
        contenedorMisSobres.getChildren().clear();

        for (SobreSimplificado sobre : Sesion.getSesion().getSobresUsuario()) {
            crearTarjetaSobre(sobre);
        }
    }

    private void abrirSobre(ActionEvent event) {
        try {
            Button boton = (Button) event.getSource();
            String nombreSobre = (String) boton.getUserData();

            Map<String, Object> mensaje = new HashMap<>();
            Map<String, String> data = new HashMap<>();

            data.put("tipoCosmeticos", "abrirSobre");
            data.put("nombreSobre", nombreSobre);

            mensaje.put("tipo", "cosmeticos");
            mensaje.put("data", data);

            ObjectMapper mapper = new ObjectMapper();

            SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));
        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }

    public void crearTarjetaSobre(SobreSimplificado sobre) {
        VBox tarjeta = new VBox();

        FlowPane.setMargin(tarjeta, new Insets(50, 25, 25, 25));

        tarjeta.setSpacing(10);
        tarjeta.setPrefWidth(300);
        tarjeta.setPrefHeight(500);
        tarjeta.setStyle("-fx-border-color: black; -fx-padding: 10;");
        tarjeta.setAlignment(Pos.CENTER);
        tarjeta.setMinHeight(Region.USE_PREF_SIZE);

        ImageView imagenSobre = new ImageView();
        imagenSobre.setFitWidth(220);
        imagenSobre.setFitHeight(320);

        // cuando haga el servidor http pues cambiaremos esto
        imagenSobre.setImage(new Image(getClass().getResourceAsStream("/com/example/teamupclienteescritorio/imagenes/logo.jpg")));

        Label nombre = new Label(sobre.getNombre());
        Label cantidad = new Label("Cantidad: " + sobre.getCantidad());


        Button abrir = new Button("Abrir");
        abrir.setUserData(sobre.getNombre());
        abrir.setOnAction(this::abrirSobre);

        tarjeta.getChildren().addAll(imagenSobre, nombre, cantidad, abrir);
        contenedorMisSobres.getChildren().add(tarjeta);
    }

    @FXML
    private void irMenuPrincipal() {
        SistemaDeJuego.cambiarPantalla("pantallaPrincipal.fxml");
    }


    @FXML
    private void irMisSobres() {
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
}