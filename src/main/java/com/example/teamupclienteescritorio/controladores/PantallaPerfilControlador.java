package com.example.teamupclienteescritorio.controladores;

import com.example.teamupclienteescritorio.clasesSesion.CartaUsuario;
import com.example.teamupclienteescritorio.clasesSesion.Usuario;
import com.example.teamupclienteescritorio.logicaAplicacion.Sesion;
import com.example.teamupclienteescritorio.utilidades.SistemaDeJuego;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PantallaPerfilControlador implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imagenFondo;

    @FXML
    private Button botonInventario;

    @FXML
    private Label rangoLabel;

    @FXML
    private ImageView flechaAtras;

    @FXML
    private Label nombreUsuario;

    @FXML
    private Label correoUsuario;

    @FXML
    private Label verificado;

    @FXML
    private Label cantidadGoles;

    @FXML
    private Label cantidadGoles1;

    @FXML
    private Label cantidadMvps;

    @FXML
    private Label partidosJugados;

    @FXML
    private ImageView carta;

    @FXML
    private Label nombreUsuarioCarta;

    @FXML
    private Label mediaJugador;

    @FXML
    private ImageView caraJugador;

    @FXML
    private Label estadisticaRitmo;

    @FXML
    private Label ritmoLabel;

    @FXML
    private Label regateLabel;

    @FXML
    private Label estadisticaRegate;

    @FXML
    private Label estadisticaDefensa;

    @FXML
    private Label defensaLabel;

    @FXML
    private Label tiroLabel;

    @FXML
    private Label estadisticaTiro;

    @FXML
    private Label paseLabel;

    @FXML
    private Label fisicoLabel;

    @FXML
    private Label estadisticaPase;

    @FXML
    private Label estadisticaFisico;


    // estos son imagenes de camisetas de futbol que representan las posiciones en el campo, dos de estas se cambiaran por camisetas rojas para mostrar que el usuario juega en esas posiciones
    @FXML private ImageView por;
    @FXML private ImageView dfc;
    @FXML private ImageView ld;
    @FXML private ImageView li;
    @FXML private ImageView dc;
    @FXML private ImageView mco;
    @FXML private ImageView ei;
    @FXML private ImageView ed;
    @FXML private ImageView mcd;
    @FXML private ImageView mc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imagenFondo.fitWidthProperty().bind(root.widthProperty());
        imagenFondo.fitHeightProperty().bind(root.heightProperty());
        cargarPosiciones();
        cargarDatosUsuario();
        cargarCarta();
    }

    private void cargarDatosUsuario() {
        Usuario usuario = Sesion.getSesion().getUsuario();

        nombreUsuario.setText(usuario.getNombre());
        correoUsuario.setText(usuario.getCorreo());

        verificado.setText(usuario.isVerificado() ? "Verificado: Sí" : "Verificado: No");

        cantidadGoles.setText("Goles: " + usuario.getGoles());
        cantidadGoles1.setText("Asistencias: " + usuario.getAsistencias());
        cantidadMvps.setText("MVPs: " + usuario.getCantidadMvp());
        partidosJugados.setText("Partidos Jugados: " + usuario.getPartidosJugados());

        rangoLabel.setText(usuario.getNombreRango());
    }

    private void cargarPosiciones() {
        CartaUsuario carta = Sesion.getSesion().getUsuario().getCartaUsuario();

        String posicion1 = carta.getPosicion1();
        String posicion2 = carta.getPosicion2();

        Image camisetaActiva = new Image(getClass().getResourceAsStream("/com/example/teamupclienteescritorio/imagenes/camisetaActiva.png"));

        activarPosicion(posicion1, camisetaActiva);
        activarPosicion(posicion2, camisetaActiva);
    }

    private void activarPosicion(String posicion, Image camisetaActiva) {
        if (posicion == null) // por si acaso nunca se sabe
            return;

        switch (posicion) {
            case "por":
                por.setImage(camisetaActiva);
                break;
            case "dfc":
                dfc.setImage(camisetaActiva);
                break;
            case "ld":
                ld.setImage(camisetaActiva);
                break;
            case "li":
                li.setImage(camisetaActiva);
                break;
            case "dc":
                dc.setImage(camisetaActiva);
                break;
            case "mco":
                mco.setImage(camisetaActiva);
                break;
            case "ei":
                ei.setImage(camisetaActiva);
                break;
            case "ed":
                ed.setImage(camisetaActiva);
                break;
            case "mcd":
                mcd.setImage(camisetaActiva);
                break;
            case "mc":
                mc.setImage(camisetaActiva);
                break;
        }
    }

    private void cargarCarta() {
        Usuario usuario = Sesion.getSesion().getUsuario();

        nombreUsuarioCarta.setText(usuario.getNombre());

        mediaJugador.setText(String.valueOf(usuario.getCartaUsuario().getMediaJugador()));
        estadisticaRitmo.setText(String.valueOf(usuario.getCartaUsuario().getRitmo()));
        estadisticaTiro.setText(String.valueOf(usuario.getCartaUsuario().getTiro()));
        estadisticaPase.setText(String.valueOf(usuario.getCartaUsuario().getPase()));
        estadisticaRegate.setText(String.valueOf(usuario.getCartaUsuario().getRegate()));
        estadisticaDefensa.setText(String.valueOf(usuario.getCartaUsuario().getDefensa()));
        estadisticaFisico.setText(String.valueOf(usuario.getCartaUsuario().getFisico()));

        caraJugador.setImage(new Image(getClass().getResourceAsStream("/com/example/teamupclienteescritorio/imagenes/placeholdercara.png")));
    }


    @FXML
    private void irInventario() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> mensaje = new HashMap<>();
            Map<String, String> datos = new HashMap<>();

            mensaje.put("tipo", "inventarioJugador");

            SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));
        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }



    @FXML
    private void volverAtras(MouseEvent event) {
        SistemaDeJuego.cambiarPantalla("pantallaPrincipal.fxml");
    }
}