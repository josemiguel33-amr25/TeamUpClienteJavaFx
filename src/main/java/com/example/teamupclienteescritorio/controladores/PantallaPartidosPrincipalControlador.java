package com.example.teamupclienteescritorio.controladores;

import com.example.teamupclienteescritorio.utilidades.SistemaDeJuego;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import com.example.teamupclienteescritorio.clasesMensajes.PartidoSimplificado;
import com.example.teamupclienteescritorio.logicaAplicacion.Sesion;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.List;

public class PantallaPartidosPrincipalControlador implements Initializable {

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
    private VBox contenedorPartidos;

    @FXML
    private HBox informacionJugador;

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
    private ScrollPane tarjetas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contenedorPartidos.setFillWidth(true);
        SistemaDeJuego.cargarDatosUsuario(imagenPerfil, imagenRango, tarjetavisita, monedas, reputacion);
        cargarPartidos();
        imagenFondo.fitWidthProperty().bind(root.widthProperty());
        imagenFondo.fitHeightProperty().bind(root.heightProperty());
        ciudades.getItems().addAll("Madrid", "Barcelona", "Valencia", "Sevilla", "Zaragoza", "Málaga", "Murcia", "Palma", "Las Palmas", "Bilbao", "Alicante", "Córdoba", "Valladolid", "Vigo", "Gijón", "Hospitalet", "A Coruña", "Vitoria", "Granada", "Elche", "Oviedo", "Badalona", "Cartagena", "Terrassa", "Jerez", "Sabadell", "Móstoles", "Santa Cruz de Tenerife", "Pamplona", "Almería", "Alcalá de Henares", "Fuenlabrada", "Leganés", "Donostia", "Burgos", "Santander", "Castellón", "Getafe", "Albacete", "Alcorcón", "Logroño", "Badajoz", "Salamanca", "Huelva", "Marbella", "Lleida", "Tarragona", "León", "Cádiz", "Jaén", "Ourense", "Girona", "Lugo", "Santiago de Compostela", "Toledo", "Cáceres", "Melilla", "Ceuta");
    }

    @FXML
    private void verPartidos() {
        try {
            String ciudad = ciudades.getValue();
            boolean verificados = soloVerificados.isSelected();

            if (ciudad == null || ciudad.isBlank()) {
                ciudad = "todas";
            }

            Map<String, Object> mensaje = new HashMap<>();
            Map<String, String> data = new HashMap<>();

            data.put("tipoPartido", "verPartidos");
            data.put("ciudad", ciudad);
            data.put("soloverificados", verificados ? "si" : "no");

            mensaje.put("tipo", "partidos");
            mensaje.put("data", data);

            ObjectMapper mapper = new ObjectMapper();
            SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));
        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }

    @FXML
    private void irMisPartidos() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> mensaje = new HashMap<>();
            Map<String, String> datos = new HashMap<>();

            datos.put("tipoPartido", "verMisPartidos");
            datos.put("estado", "abierto");

            mensaje.put("tipo", "partidos");
            mensaje.put("data", datos);

            SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));
        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }

    @FXML
    private void irCrearPartido() {
        SistemaDeJuego.abrirPopup("pantallaCrearPartido.fxml", "Crear Partido");
    }

    @FXML
    private void irMenuPrincipal() {
        SistemaDeJuego.cambiarPantalla("pantallaPrincipal.fxml");
    }

    public void cargarPartidos() {

        contenedorPartidos.setPadding(new Insets(20, 20,  100, 20)); // arriba, derecha, abajo izquierda sin esto se corta la ultima tarjeta al final
        contenedorPartidos.getChildren().clear();

        for (PartidoSimplificado p : Sesion.getSesion().getPartidosSimplificados()) {

            HBox tarjeta = new HBox(20);
            tarjeta.setStyle("-fx-border-color: black; -fx-padding: 15;");
            tarjeta.setMaxWidth(Double.MAX_VALUE);
            tarjeta.prefWidthProperty().bind(contenedorPartidos.widthProperty().subtract(25));

            // Usuario
            VBox usuario = new VBox(5);

            // por ahora placeholder imagen mas addelante usare servidor httpp, esto es para identificar el jugador que crea el partido y que el usuario pueda pinchar en el perfil para ver mas info del usuario
            ImageView foto = new ImageView(new Image(getClass().getResourceAsStream("/com/example/teamupclienteescritorio/imagenes/logo.jpg")));
            foto.setFitWidth(60);
            foto.setFitHeight(60);
            Label nombreUsuario = new Label(p.getNombreUsuario());
            usuario.getChildren().addAll(foto, nombreUsuario);

            // Esto es todo lo relacionado con la informacion de los partidos
            VBox informacion = new VBox(5);
            HBox.setHgrow(informacion, Priority.ALWAYS);

            Label titulo = new Label(p.getTituloPartido());
            titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

            Label ubicacion = new Label("📍 " + p.getUbicacion());
            Label ciudad = new Label("🌍 " + p.getCiudad());
            Label fecha = new Label("📅 " + p.getDia() + "/" + p.getMes() + "/" + p.getAnio() + " " + p.getHora() + ":" + String.format("%02d", p.getMinutos()));
            Label precio = new Label("💰 " + p.getPrecio() + "€");
            Label estado = new Label("🔓 " + p.getEstado());
            Label verificados = new Label(p.isSoloVerificados() ? "✔ Solo verificados" : "✖ Todos los usuarios");

            informacion.getChildren().addAll(titulo, ubicacion, ciudad, fecha, precio, estado, verificados);

            // Separador raro que encontre google es para crear regiones
            Region espacio = new Region();
            HBox.setHgrow(espacio, Priority.ALWAYS);

            // Logica de los botoncitos
            VBox botones = new VBox(10);
            botones.setAlignment(Pos.CENTER);

            Button botonUnirse = new Button("Unirse");


            Button botonVerMas = new Button("Ver más");
            botonVerMas.setUserData(p.getIdPartido());
            botonVerMas.setOnAction(this::verMasPartido);

            ComboBox<String> equipos = new ComboBox<>();
            equipos.getItems().addAll("equipo1", "equipo2");
            equipos.setValue("equipo1");


            botonUnirse.setUserData(new Object[]{p.getIdPartido(), equipos});
            botonUnirse.setOnAction(this::unirsePartido);

            botones.getChildren().addAll(botonUnirse, botonVerMas, equipos);
            tarjeta.getChildren().addAll(usuario, informacion, espacio, botones);
            contenedorPartidos.getChildren().add(tarjeta);
        }
    }

    private void unirsePartido(ActionEvent event) {
        try {
            Button boton = (Button) event.getSource();
            Object[] datosBoton = (Object[]) boton.getUserData();

            int idPartido = (int) datosBoton[0];
            ComboBox<String> equipos = (ComboBox<String>) datosBoton[1];

            String equipo = equipos.getValue();

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> mensaje = new HashMap<>();
            Map<String, String> datos = new HashMap<>();

            datos.put("tipoPartido", "unirsePartido");
            datos.put("idPartido", String.valueOf(idPartido));
            datos.put("equipo", equipo);

            mensaje.put("tipo", "partidos");
            mensaje.put("data", datos);

            SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));
        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }

    private void verMasPartido(ActionEvent event) {
        Button boton = (Button) event.getSource();
        int idPartido = (int) boton.getUserData();
        System.out.println("TeamUp|MensajeInterno| Usuario quiere ver mas info del partido con id: " + idPartido);

        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> mensaje = new HashMap<>();
            Map<String, String> datos = new HashMap<>();

            datos.put("tipoPartido", "masInfoPartido");
            datos.put("idPartido", String.valueOf(idPartido));

            mensaje.put("tipo", "partidos");
            mensaje.put("data", datos);

            SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));
        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }
}