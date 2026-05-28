package com.example.teamupclienteescritorio.Controladores;

import com.example.teamupclienteescritorio.Utilidades.SistemaDeJuego;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PantallaLogControlador implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imagenFondo;

    @FXML
    private ImageView logo;

    @FXML
    private TextArea correoElectronico;

    @FXML
    private PasswordField contrasenia;

    @FXML
    private CheckBox recordarme;

    @FXML
    private Button botonIniciarSesion;

    @FXML
    private Label mensajePosicion1;

    @FXML
    private Label mensajePosicion2;

    @FXML
    private Label mensajePosicion11;

    @FXML
    private Label mensajePosicion111;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        imagenFondo.fitWidthProperty().bind(root.widthProperty());
        imagenFondo.fitHeightProperty().bind(root.heightProperty());
    }

    @FXML
    private void iniciarSesion() {

        mensajePosicion1.setVisible(false);
        mensajePosicion2.setVisible(false);
        mensajePosicion11.setVisible(false);
        mensajePosicion111.setVisible(false);

        String correo = correoElectronico.getText();
        String password = contrasenia.getText();

        boolean recordar = recordarme.isSelected();

        System.out.println("Correo: " + correo);
        System.out.println("Contraseña: " + password);
        System.out.println("Recordarme: " + recordar);

        // enviar json al servidor
    }

    @FXML
    private void volverAtras() {

        SistemaDeJuego.cambiarPantalla("pantallaLogReg.fxml");
    }
}