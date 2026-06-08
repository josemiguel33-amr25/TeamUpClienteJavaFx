package com.example.teamupclienteescritorio.controladores;

import com.example.teamupclienteescritorio.clasesMensajes.Participante;
import com.example.teamupclienteescritorio.clasesSesion.DatosPartido;
import com.example.teamupclienteescritorio.logicaAplicacion.Sesion;
import com.example.teamupclienteescritorio.utilidades.SistemaDeJuego;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class PantallaVerDetallesPartidoControlador implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView logo;

    @FXML
    private Button botonAccion;

    @FXML
    private ComboBox<String> equipoSeleccion;

    @FXML
    private ComboBox<String> mvpJugadores;

    @FXML
    private Label estadoPartido;

    @FXML
    private Label nombrej1E1;

    @FXML
    private Label nombrej2E1;

    @FXML
    private Label nombrej3E1;

    @FXML
    private Label nombrej4E1;

    @FXML
    private Label nombrej5E1;

    @FXML
    private Label nombrej6E1;

    @FXML
    private Label nombrej7E1;

    @FXML
    private Label nombrej1E2;

    @FXML
    private Label nombrej2E2;

    @FXML
    private Label nombrej3E2;

    @FXML
    private Label nombrej4E2;

    @FXML
    private Label nombrej5E2;

    @FXML
    private Label nombrej6E2;

    @FXML
    private Label nombrej7E2;

    @FXML
    private ComboBox<Integer> puntuacionJ1E1;

    @FXML
    private ComboBox<Integer> puntuacionJ2E1;

    @FXML
    private ComboBox<Integer> puntuacionJ3E1;

    @FXML
    private ComboBox<Integer> puntuacionJ4E1;

    @FXML
    private ComboBox<Integer> puntuacionJ5E1;

    @FXML
    private ComboBox<Integer> puntuacionJ6E1;

    @FXML
    private ComboBox<Integer> puntuacionJ7E1;

    @FXML
    private ComboBox<Integer> puntuacionJ1E2;

    @FXML
    private ComboBox<Integer> puntuacionJ2E2;

    @FXML
    private ComboBox<Integer> puntuacionJ3E2;

    @FXML
    private ComboBox<Integer> puntuacionJ4E2;

    @FXML
    private ComboBox<Integer> puntuacionJ5E2;

    @FXML
    private ComboBox<Integer> puntuacionJ6E2;

    @FXML
    private ComboBox<Integer> puntuacionJ7E2;

    @FXML
    private Spinner<Integer> golesJ1E1;

    @FXML
    private Spinner<Integer> golesJ2E1;

    @FXML
    private Spinner<Integer> golesJ3E1;

    @FXML
    private Spinner<Integer> golesJ4E1;

    @FXML
    private Spinner<Integer> golesJ5E1;

    @FXML
    private Spinner<Integer> golesJ6E1;

    @FXML
    private Spinner<Integer> golesJ7E1;

    @FXML
    private Spinner<Integer> golesJ1E2;

    @FXML
    private Spinner<Integer> golesJ2E2;

    @FXML
    private Spinner<Integer> golesJ3E2;

    @FXML
    private Spinner<Integer> golesJ4E2;

    @FXML
    private Spinner<Integer> golesJ5E2;

    @FXML
    private Spinner<Integer> golesJ6E2;

    @FXML
    private Spinner<Integer> golesJ7E2;

    @FXML
    private Spinner<Integer> asisJ1E1;

    @FXML
    private Spinner<Integer> asisJ2E1;

    @FXML
    private Spinner<Integer> asisJ3E1;

    @FXML
    private Spinner<Integer> asisJ4E1;

    @FXML
    private Spinner<Integer> asisJ5E1;

    @FXML
    private Spinner<Integer> asisJ6E1;

    @FXML
    private Spinner<Integer> asisJ7E1;

    @FXML
    private Spinner<Integer> asisJ1E2;

    @FXML
    private Spinner<Integer> asisJ2E2;

    @FXML
    private Spinner<Integer> asisJ3E2;

    @FXML
    private Spinner<Integer> asisJ4E2;

    @FXML
    private Spinner<Integer> asisJ5E2;

    @FXML
    private Spinner<Integer> asisJ6E2;

    @FXML
    private Spinner<Integer> asisJ7E2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatosPartido partido = Sesion.getSesion().getPartidoActual();
        estadoPartido.setText(partido.getEstadoPartido());
        System.out.println("TeamUp|MensajeInterno| Participantes del partido actual: " + partido.getParticipantes().size() + " y el creador del partido es: " + partido.getCreadorPartido());
        cargarParticipantes();
        configurarEstadoPartido();
    }


    private void cargarParticipantes() {
        DatosPartido partido = Sesion.getSesion().getPartidoActual();

        List<Participante> equipo1 = new ArrayList<>();
        List<Participante> equipo2 = new ArrayList<>();

        for (Participante participante : partido.getParticipantes()) {
            if (participante.getEquipo().equalsIgnoreCase("equipo1")) {
                equipo1.add(participante);
            } else {
                equipo2.add(participante);
            }
        }

        Label[] labelsEquipo1 = {nombrej1E1, nombrej2E1, nombrej3E1, nombrej4E1, nombrej5E1, nombrej6E1, nombrej7E1};
        Label[] labelsEquipo2 = {nombrej1E2, nombrej2E2, nombrej3E2, nombrej4E2, nombrej5E2, nombrej6E2, nombrej7E2};

        for (int i = 0; i < labelsEquipo1.length; i++) {
            if (i < equipo1.size()) {
                labelsEquipo1[i].setText(equipo1.get(i).getNombreUsuario());
            } else {
                labelsEquipo1[i].setText("Libre");
            }

            if (i < equipo2.size()) {
                labelsEquipo2[i].setText(equipo2.get(i).getNombreUsuario());
            } else {
                labelsEquipo2[i].setText("Libre");
            }
        }
    }

    private void configurarEstadoPartido() {
        DatosPartido partido = Sesion.getSesion().getPartidoActual();
        boolean esCreador = partido.esCreador(Sesion.getSesion().getUsuario().getIdUsuario());
        if (partido.getEstadoPartido().equalsIgnoreCase("abierto")) {
            if (partido.participaJugador(Sesion.getSesion().getUsuario().getIdUsuario()) && !esCreador) {
                botonAccion.setText("Abandonar Partido");
                botonAccion.setVisible(true);
            }
        }  else if (partido.getEstadoPartido().equalsIgnoreCase("lleno")) {
            if (esCreador) {
                botonAccion.setText("Finalizar Partido");
                botonAccion.setVisible(true);
            } else {
                botonAccion.setText("Abandonar Partido");
                botonAccion.setVisible(true);
            }

        }else if (partido.getEstadoPartido().equalsIgnoreCase("terminado")) {
            botonAccion.setVisible(true);
            botonAccion.setText("Enviar Votación");
            activarControlesPuntuacion();
            if (esCreador)
                activarControlesAdministrador();
        }
    }

    private void activarControlesAdministrador() {
        botonAccion.setVisible(true);
        botonAccion.setText("Completar Partido");

        mvpJugadores.setVisible(true);
        mvpJugadores.getItems().clear();

        equipoSeleccion.setVisible(true);
        equipoSeleccion.getItems().clear();
        equipoSeleccion.getItems().addAll("equipo1", "equipo2", "empate");

        configurarSpinnerGoles(golesJ1E1);
        configurarSpinnerGoles(golesJ2E1);
        configurarSpinnerGoles(golesJ3E1);
        configurarSpinnerGoles(golesJ4E1);
        configurarSpinnerGoles(golesJ5E1);
        configurarSpinnerGoles(golesJ6E1);
        configurarSpinnerGoles(golesJ7E1);

        configurarSpinnerGoles(golesJ1E2);
        configurarSpinnerGoles(golesJ2E2);
        configurarSpinnerGoles(golesJ3E2);
        configurarSpinnerGoles(golesJ4E2);
        configurarSpinnerGoles(golesJ5E2);
        configurarSpinnerGoles(golesJ6E2);
        configurarSpinnerGoles(golesJ7E2);

        configurarSpinnerGoles(asisJ1E1);
        configurarSpinnerGoles(asisJ2E1);
        configurarSpinnerGoles(asisJ3E1);
        configurarSpinnerGoles(asisJ4E1);
        configurarSpinnerGoles(asisJ5E1);
        configurarSpinnerGoles(asisJ6E1);
        configurarSpinnerGoles(asisJ7E1);

        configurarSpinnerGoles(asisJ1E2);
        configurarSpinnerGoles(asisJ2E2);
        configurarSpinnerGoles(asisJ3E2);
        configurarSpinnerGoles(asisJ4E2);
        configurarSpinnerGoles(asisJ5E2);
        configurarSpinnerGoles(asisJ6E2);
        configurarSpinnerGoles(asisJ7E2);

        for (Participante p : Sesion.getSesion().getPartidoActual().getParticipantes())
            mvpJugadores.getItems().add(p.getNombreUsuario());

    }

    private void configurarSpinnerGoles(Spinner<Integer> spinner) {
        spinner.setVisible(true);
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, 0));
    }

    private void configurarComboPuntuacion(ComboBox<Integer> combo) { // para no repetir tantos add all
        combo.setVisible(true);
        combo.getItems().addAll(1,2,3,4,5);
    }

    private void activarControlesPuntuacion() {
        configurarComboPuntuacion(puntuacionJ1E1);
        configurarComboPuntuacion(puntuacionJ2E1);
        configurarComboPuntuacion(puntuacionJ3E1);
        configurarComboPuntuacion(puntuacionJ4E1);
        configurarComboPuntuacion(puntuacionJ5E1);
        configurarComboPuntuacion(puntuacionJ6E1);
        configurarComboPuntuacion(puntuacionJ7E1);

        configurarComboPuntuacion(puntuacionJ1E2);
        configurarComboPuntuacion(puntuacionJ2E2);
        configurarComboPuntuacion(puntuacionJ3E2);
        configurarComboPuntuacion(puntuacionJ4E2);
        configurarComboPuntuacion(puntuacionJ5E2);
        configurarComboPuntuacion(puntuacionJ6E2);
        configurarComboPuntuacion(puntuacionJ7E2);
    }

    private void enviarFinalizarPartido() { // la pongo aqui para no sobrecargar la de accion del boton
        try {
            DatosPartido partido = Sesion.getSesion().getPartidoActual();
            Map<String, Object> mensaje = new HashMap<>();

            Map<String, String> data = new HashMap<>();
            data.put("tipoPartido", "pasarPartidoFinalizado");
            data.put("idPartido", String.valueOf(partido.getIdPartido()));

            mensaje.put("tipo", "partidos");
            mensaje.put("data", data);

            ObjectMapper mapper = new ObjectMapper();

            SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));
        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }

    private void enviarAbandonarPartido() {
        try {
            DatosPartido partido = Sesion.getSesion().getPartidoActual();

            Map<String, Object> mensaje = new HashMap<>();
            Map<String, String> data = new HashMap<>();

            data.put("tipoPartido", "abandonarPartido");
            data.put("idPartido", String.valueOf(partido.getIdPartido()));

            mensaje.put("tipo", "partidos");
            mensaje.put("data", data);

            ObjectMapper mapper = new ObjectMapper();

            SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));
        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }


    @FXML
    private void accionPartido() {
        DatosPartido partido = Sesion.getSesion().getPartidoActual();
        boolean esCreador = partido.esCreador(Sesion.getSesion().getUsuario().getIdUsuario());

        if (partido.getEstadoPartido().equalsIgnoreCase("lleno") && esCreador) {
            enviarFinalizarPartido();
            return;
        }

        if (!partido.getEstadoPartido().equalsIgnoreCase("terminado") && !partido.getEstadoPartido().equalsIgnoreCase("completado")) {
            enviarAbandonarPartido();
        }
    }
}