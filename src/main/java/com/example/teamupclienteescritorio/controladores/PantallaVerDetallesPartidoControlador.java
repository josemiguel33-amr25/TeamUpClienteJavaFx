package com.example.teamupclienteescritorio.controladores;

import com.example.teamupclienteescritorio.clasesMensajes.Participante;
import com.example.teamupclienteescritorio.clasesMensajes.VotacionJugador;
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
    private ImageView imagenj1E1;

    @FXML
    private ImageView imagenj2E1;

    @FXML
    private ImageView imagenj3E1;

    @FXML
    private ImageView imagenj4E1;

    @FXML
    private ImageView imagenj5E1;

    @FXML
    private ImageView imagenj6E1;

    @FXML
    private ImageView imagenj7E1;

    @FXML
    private ImageView imagenj1E2;

    @FXML
    private ImageView imagenj2E2;

    @FXML
    private ImageView imagenj3E2;

    @FXML
    private ImageView imagenj4E2;

    @FXML
    private ImageView imagenj5E2;

    @FXML
    private ImageView imagenj6E2;

    @FXML
    private ImageView imagenj7E2;

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
    public void initialize(URL url, ResourceBundle resourceBundle) { // Las cargas de imagenes las desactivo porque relentizan mucho no se porque
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

        ImageView[] imagenesEquipo1 = {imagenj1E1, imagenj2E1, imagenj3E1, imagenj4E1, imagenj5E1, imagenj6E1, imagenj7E1};
        ImageView[] imagenesEquipo2 = {imagenj1E2, imagenj2E2, imagenj3E2, imagenj4E2, imagenj5E2, imagenj6E2, imagenj7E2};

        Label[] labelsEquipo1 = {nombrej1E1, nombrej2E1, nombrej3E1, nombrej4E1, nombrej5E1, nombrej6E1, nombrej7E1};
        Label[] labelsEquipo2 = {nombrej1E2, nombrej2E2, nombrej3E2, nombrej4E2, nombrej5E2, nombrej6E2, nombrej7E2};

        for (int i = 0; i < labelsEquipo1.length; i++) {
            if (i < equipo1.size()) {
                labelsEquipo1[i].setText(equipo1.get(i).getNombreUsuario());
                //imagenesEquipo1[i].setImage(SistemaDeJuego.cargarImagen("fotosPerfil", equipo1.get(i).getFotoUsuario() + "png"));
            } else {
                labelsEquipo1[i].setText("Libre");
            }

            if (i < equipo2.size()) {
                labelsEquipo2[i].setText(equipo2.get(i).getNombreUsuario());
                //imagenesEquipo2[i].setImage(SistemaDeJuego.cargarImagen("fotosPerfil", equipo2.get(i).getFotoUsuario() + "png"));
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
        } else if (partido.getEstadoPartido().equalsIgnoreCase("completado")) {
            botonAccion.setVisible(true);
            botonAccion.setText("Recoger recompensas");
        }
    }

    private void activarControlesAdministrador() {
        botonAccion.setVisible(true);
        botonAccion.setText("Enviar votacion");

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
            return;
        }

        if (partido.getEstadoPartido().equalsIgnoreCase("completado")) {
            enviarRecogerRecompensas();
            return;
        }

        if (partido.getEstadoPartido().equalsIgnoreCase("terminado")) {
            enviarVotacion();
        }
    }

    private void enviarRecogerRecompensas() {
        try {
            DatosPartido partido = Sesion.getSesion().getPartidoActual();

            Map<String, Object> mensaje = new HashMap<>();
            Map<String, String> data = new HashMap<>();

            data.put("tipoPartido", "recogerRecompensas");
            data.put("idPartido", String.valueOf(partido.getIdPartido()));

            mensaje.put("tipo", "partidos");
            mensaje.put("data", data);

            ObjectMapper mapper = new ObjectMapper();

            SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));
        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }

    private List<Participante> obtenerParticipantesOrdenados() {

        List<Participante> participantesOrdenados = new ArrayList<>();

        for (Participante p : Sesion.getSesion().getPartidoActual().getParticipantes()) {
            if (p.getEquipo().equalsIgnoreCase("equipo1")) {
                participantesOrdenados.add(p);
            }
        }

        for (Participante p : Sesion.getSesion().getPartidoActual().getParticipantes()) {
            if (p.getEquipo().equalsIgnoreCase("equipo2")) {
                participantesOrdenados.add(p);
            }
        }

        return participantesOrdenados;
    }

    private void enviarVotacion() {
        try {
            DatosPartido partido = Sesion.getSesion().getPartidoActual();
            boolean esCreador = partido.esCreador(Sesion.getSesion().getUsuario().getIdUsuario());

            if (esCreador && equipoSeleccion.getValue() == null) {
                SistemaDeJuego.abrirMensaje("Tienes que elegir un equipo ganador");
                return;
            }

            List<ComboBox<Integer>> puntuaciones = List.of(puntuacionJ1E1, puntuacionJ2E1, puntuacionJ3E1, puntuacionJ4E1, puntuacionJ5E1, puntuacionJ6E1, puntuacionJ7E1, puntuacionJ1E2, puntuacionJ2E2, puntuacionJ3E2, puntuacionJ4E2, puntuacionJ5E2, puntuacionJ6E2, puntuacionJ7E2);
            List<Spinner<Integer>> goles = List.of(golesJ1E1, golesJ2E1, golesJ3E1, golesJ4E1, golesJ5E1, golesJ6E1, golesJ7E1, golesJ1E2, golesJ2E2, golesJ3E2, golesJ4E2, golesJ5E2, golesJ6E2, golesJ7E2);
            List<Spinner<Integer>> asistencias = List.of(asisJ1E1, asisJ2E1, asisJ3E1, asisJ4E1, asisJ5E1, asisJ6E1, asisJ7E1, asisJ1E2, asisJ2E2, asisJ3E2, asisJ4E2, asisJ5E2, asisJ6E2, asisJ7E2);

            List<Participante> participantes = obtenerParticipantesOrdenados();
            List<VotacionJugador> votaciones = new ArrayList<>();

            for (int i = 0; i < participantes.size(); i++) {

                Participante p = participantes.get(i);

                int puntuacion = 0;

                if (p.getIdUsuario() == Sesion.getSesion().getUsuario().getIdUsuario() && !esCreador) { // evitamos que el usuario se pueda votar a si mismo (visualmente no pense como desactivar los controles del usuario), siempre claro que no sea creador
                    continue;
                }

                if (puntuaciones.get(i).getValue() != null) {
                    puntuacion = puntuaciones.get(i).getValue();
                }

                int golesJugador = 0;
                int asistenciasJugador = 0;
                boolean mvp = false;

                if (esCreador) {
                    golesJugador = goles.get(i).getValue() == null ? 0 : goles.get(i).getValue(); // para evitar que llegue null al server
                    asistenciasJugador = asistencias.get(i).getValue() == null ? 0 : asistencias.get(i).getValue();

                    if (mvpJugadores.getValue() != null) {
                        mvp = mvpJugadores.getValue().equals(p.getNombreUsuario());
                    }
                }

                votaciones.add(new VotacionJugador(p.getIdUsuario(), puntuacion, golesJugador, asistenciasJugador, mvp));}

            Map<String, Object> mensaje = new HashMap<>();
            Map<String, Object> data = new HashMap<>();

            data.put("tipoPartido", "votarJugadores");
            data.put("idPartido", String.valueOf(partido.getIdPartido()));

            if (esCreador) {
                data.put("equipoGanador", equipoSeleccion.getValue());
            } else {
                data.put("equipoGanador", "-33");
            }

            data.put("votaciones", votaciones);

            mensaje.put("tipo", "partidos");
            mensaje.put("data", data);

            ObjectMapper mapper = new ObjectMapper();

            SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));

        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }
}