package com.example.teamupclienteescritorio.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PantallaErrorControlador implements Initializable {

    @FXML
    private Label cajaTexto;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setMensaje(String mensaje) {
        cajaTexto.setText(mensaje);
    }
}