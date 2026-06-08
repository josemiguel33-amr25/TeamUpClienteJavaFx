package com.example.teamupclienteescritorio.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.example.teamupclienteescritorio.utilidades.SistemaDeJuego;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class PantallaCrearPartidosControlador implements Initializable {

    @FXML
    private TextField titulo;

    @FXML
    private ImageView logo;

    @FXML
    private TextField precio;

    @FXML
    private TextField ubicacion;

    @FXML
    private ComboBox<String> ciudades;

    @FXML
    private DatePicker fecha;

    @FXML
    private CheckBox soloVerificados;

    @FXML
    private ComboBox<String> horas;

    @FXML
    private ComboBox<String> minutos;

    @FXML
    private Button botonPartido;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ciudades.getItems().addAll("Madrid", "Barcelona", "Valencia", "Sevilla", "Zaragoza", "Málaga", "Murcia", "Palma", "Las Palmas", "Bilbao", "Alicante", "Córdoba", "Valladolid", "Vigo", "Gijón", "Hospitalet", "A Coruña", "Vitoria", "Granada", "Elche", "Oviedo", "Badalona", "Cartagena", "Terrassa", "Jerez", "Sabadell", "Móstoles", "Santa Cruz de Tenerife", "Pamplona", "Almería", "Alcalá de Henares", "Fuenlabrada", "Leganés", "Donostia", "Burgos", "Santander", "Castellón", "Getafe", "Albacete", "Alcorcón", "Logroño", "Badajoz", "Salamanca", "Huelva", "Marbella", "Lleida", "Tarragona", "León", "Cádiz", "Jaén", "Ourense", "Girona", "Lugo", "Santiago de Compostela", "Toledo", "Cáceres", "Melilla", "Ceuta");
        horas.getItems().addAll("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
        minutos.getItems().addAll("00","15","30","45");
    }


    @FXML
    private void crearPartido() {

        if (!validarDatosPartido()) {
            return;
        }

        String tituloPartido = titulo.getText();
        String ubicacionPartido = ubicacion.getText();
        String ciudad = ciudades.getValue();
        String precioPartido = precio.getText();
        boolean verificados = soloVerificados.isSelected();
        LocalDate fechaSeleccionada = fecha.getValue();

        int dia = fechaSeleccionada.getDayOfMonth();
        int mes = fechaSeleccionada.getMonthValue();
        int anio = fechaSeleccionada.getYear();
        int hora = Integer.parseInt(horas.getValue());
        int minutosPartido = Integer.parseInt(minutos.getValue());

        enviarCrearPartido(tituloPartido, ubicacionPartido, precioPartido, ciudad, verificados, dia, mes, anio, hora, minutosPartido);
    }

    private void enviarCrearPartido(String titulo, String ubicacion, String precio, String ciudad, boolean verificados, int dia, int mes, int anio, int hora, int minutos) {
        try {
            Map<String, Object> mensaje = new HashMap<>();
            Map<String, String> data = new HashMap<>();

            data.put("tipoPartido", "crearPartido");
            data.put("titulo", titulo);
            data.put("ubicacion", ubicacion);
            data.put("precio", precio);
            data.put("ciudad", ciudad);
            data.put("verificados", verificados ? "si" : "no");
            data.put("anio", String.valueOf(anio));
            data.put("mes", String.valueOf(mes));
            data.put("dia", String.valueOf(dia));
            data.put("hora", String.valueOf(hora));
            data.put("minutos", String.format("%02d", minutos));

            mensaje.put("tipo", "partidos");
            mensaje.put("data", data);

            ObjectMapper mapper = new ObjectMapper();
            SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));
        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }

    private boolean validarDatosPartido() {
        if (titulo.getText() == null || titulo.getText().isBlank()) {
            SistemaDeJuego.abrirMensaje("Debes introducir un título");
            return false;
        }

        if (titulo.getText().length() > 100) {
            SistemaDeJuego.abrirMensaje("El título no puede superar los 100 caracteres");
            return false;
        }

        if (ubicacion.getText() == null || ubicacion.getText().isBlank()) {
            SistemaDeJuego.abrirMensaje("Debes introducir una ubicación");
            return false;
        }

        if (ciudades.getValue() == null) {
            SistemaDeJuego.abrirMensaje("Debes seleccionar una ciudad");
            return false;
        }

        if (precio.getText() == null || precio.getText().isBlank()) {
            SistemaDeJuego.abrirMensaje("Debes introducir un precio");
            return false;
        }

        try {
            int precioNumerico = Integer.parseInt(precio.getText());

            if (precioNumerico < 0) {
                SistemaDeJuego.abrirMensaje("El precio no puede ser negativo");
                return false;
            }

        } catch (NumberFormatException e) { // viejo truco para comprobar esto, me encanta
            SistemaDeJuego.abrirMensaje("El precio debe ser un número entero");
            return false;
        }

        if (fecha.getValue() == null) {
            SistemaDeJuego.abrirMensaje("Debes seleccionar una fecha");
            return false;
        }

        if (fecha.getValue().isBefore(LocalDate.now())) {
            SistemaDeJuego.abrirMensaje("No puedes crear partidos en fechas pasadas");
            return false;
        }

        if (horas.getValue() == null) {
            SistemaDeJuego.abrirMensaje("Debes seleccionar una hora");
            return false;
        }

        if (minutos.getValue() == null) {
            SistemaDeJuego.abrirMensaje("Debes seleccionar los minutos");
            return false;
        }
        return true;
    }
}