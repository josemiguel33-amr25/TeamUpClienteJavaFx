package com.example.teamupclienteescritorio.controladores;

import com.example.teamupclienteescritorio.clasesMensajes.UsuarioSimplificado;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PantallaRankingControlador implements Initializable {

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
    private Button recargarBoton;

    @FXML
    private ComboBox<String> rangos;

    @FXML
    private ComboBox<String> orden;

    @FXML
    private ScrollPane tarjetas;

    @FXML
    private VBox contenedorPartidos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SistemaDeJuego.cargarDatosUsuario(imagenPerfil, imagenRango, tarjetavisita, monedas, reputacion);

        contenedorPartidos.setFillWidth(true);
        contenedorPartidos.setPadding(new Insets(20, 20, 100, 20));          // arriba, derecha, abajo izquierda sin esto se corta la ultima tarjeta al final
        imagenFondo.fitWidthProperty().bind(root.widthProperty());
        imagenFondo.fitHeightProperty().bind(root.heightProperty());

        rangos.getItems().addAll("bronce", "plata", "oro", "elite");
        orden.getItems().addAll("mayor", "menor");

        cargarRanking();
    }

    private void cargarRanking() { // pone contenedor partidos porque la interfaz es casi un copia y pega de partidos
        contenedorPartidos.getChildren().clear(); // no hago comprobacion de si la lista de jugadores del rango esta vacia porque si estuviera vacia ni abre esta ventana
        Image imagenRango = SistemaDeJuego.cargarImagen("rangos", SistemaDeJuego.nombreArchivo(obtenerRango(Sesion.getSesion().getUsuariosRanking().get(0).getRango())) + ".png");

        for (UsuarioSimplificado usuario : Sesion.getSesion().getUsuariosRanking()) {
            crearTarjetaUsuario(usuario, imagenRango);
        }
    }

    private String obtenerRango(String rango) {
        String rangoConvertido = "";
        if (rango.equalsIgnoreCase("1")) {
            rangoConvertido = "Bronce";
        } else if (rango.equalsIgnoreCase("2")) {
            rangoConvertido = "Plata";
        } else if (rango.equalsIgnoreCase("3")) {
            rangoConvertido = "Oro";
        } else if (rango.equalsIgnoreCase("4")) {
            rangoConvertido = "Elite";
        }

        return  rangoConvertido;
    }

    private void crearTarjetaUsuario(UsuarioSimplificado usuario, Image imagenRangoDescargada) { // paso la imagen porque para todos es el mismo rango asi que para ahorrarnos llamar al s ervidor http cada vez que se necesite la imagen del rango
        HBox tarjeta = new HBox(20);
        tarjeta.getStyleClass().add("tarjeta-ranking");

        tarjeta.setMaxWidth(Double.MAX_VALUE);
        tarjeta.prefWidthProperty().bind(contenedorPartidos.widthProperty().subtract(25));

        VBox rangoBox = new VBox(5);
        rangoBox.setAlignment(Pos.CENTER);

        ImageView imagenRango = new ImageView(imagenRangoDescargada);
        imagenRango.setFitWidth(120);
        imagenRango.setFitHeight(120);
        imagenRango.setPreserveRatio(true);

        Label nombreRango = new Label(obtenerRango(usuario.getRango()));
        nombreRango.getStyleClass().add("rango-ranking");

        rangoBox.getChildren().addAll(imagenRango, nombreRango);

        VBox informacion = new VBox(5);
        HBox.setHgrow(informacion, Priority.ALWAYS);

        Label nombreUsuario = new Label(usuario.getNombre());
        nombreUsuario.getStyleClass().add("nombre-ranking");nombreUsuario.getStyleClass().add("nombre-ranking");

        Label estadisticas = new Label("Estadísticas\n" + "Puntos: " + usuario.getPuntos() + "\n" + "Reputación: " + usuario.getReputacion() + "\n" + "Goles: " + usuario.getGoles() + "\n" + "Asistencias: " + usuario.getAsistencias() + "\n" + "MVPs: " + usuario.getMvps());
        estadisticas.getStyleClass().add("estadisticas-ranking");

        informacion.getChildren().addAll(nombreUsuario, estadisticas);
        Region espacio = new Region();
        HBox.setHgrow(espacio, Priority.ALWAYS);



        tarjeta.getChildren().addAll(rangoBox, informacion, espacio);

        contenedorPartidos.getChildren().add(tarjeta);
    }

    private void verPerfil(ActionEvent event) {
        Button boton = (Button) event.getSource();
        UsuarioSimplificado usuario = (UsuarioSimplificado) boton.getUserData();
    }

    @FXML
    private void verRanking() {
        try {
            String rangoSeleccionado = rangos.getValue();
            String mayorMenor = orden.getValue();
            int rango = 1; // tiene que ser int porque en la base de datos tengo rango con numero no con nombres

            if (rangoSeleccionado == null) {
                rango = 1;
            } else if (rangoSeleccionado.equalsIgnoreCase("bronce")) {
                rango = 1;
            } else if (rangoSeleccionado.equalsIgnoreCase("plata")) {
                rango = 2;
            } else if (rangoSeleccionado.equalsIgnoreCase("oro")) {
                rango = 3;
            } else if (rangoSeleccionado.equalsIgnoreCase("elite")) {
                rango = 4;
            }

            if (mayorMenor == null || mayorMenor.isBlank()) {
                mayorMenor = "mayor";
            }

            Map<String, Object> mensaje = new HashMap<>();
            Map<String, String> data = new HashMap<>();

            data.put("rango", String.valueOf(rango));
            data.put("mayorMenor", mayorMenor.toLowerCase());

            mensaje.put("tipo", "ranking");
            mensaje.put("data", data);

            ObjectMapper mapper = new ObjectMapper();

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