package com.example.teamupclienteescritorio.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PantallaPartidosControlador implements Initializable {

    @FXML
    private TextField titulo;

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

        //mensajes depurador para luego
        System.out.println("Titulo: " + tituloPartido);

        System.out.println("Ubicacion: " + ubicacionPartido);

        System.out.println("Ciudad: " + ciudad);

        System.out.println("Precio: " + precioPartido);

        System.out.println("Verificados: " + verificados);

        System.out.println("Fecha: " + dia + "/" + mes + "/" + anio);

        System.out.println("Hora: " + hora + ":" + minutosPartido);

        // enviar json al servidor
    }
}