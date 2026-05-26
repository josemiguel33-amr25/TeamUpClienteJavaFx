package com.example.teamupclienteescritorio.Controladores;

import com.example.teamupclienteescritorio.Utilidades.SistemaDeJuego;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class PantallaLogRegControlador {

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imagenFondo;

    @FXML
    private Text textoTeamUp;

    @FXML
    private Button botonCrear;

    @FXML
    private Button iniciarBoton;

    @FXML
    private Button botonSalir;

    @FXML
    public void initialize() {

    }

    @FXML
    private void crearCuenta() {
        SistemaDeJuego.cambiarPantalla("pantallaReg.fxml");
    }

    @FXML
    private void iniciarSesion() {
        SistemaDeJuego.cambiarPantalla("pantallaLog");
        // añadir que al darle esta pestaña comprobamos si tiene token, si lo tiene no cambiamos de pestaña y lo enviamos al servidor
    }

    @FXML
    private void salir() {

        SistemaDeJuego.cliente.salir();
    }

}