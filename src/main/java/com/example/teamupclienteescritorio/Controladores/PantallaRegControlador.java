package com.example.teamupclienteescritorio.Controladores;

import com.example.teamupclienteescritorio.Utilidades.SistemaDeJuego;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PantallaRegControlador implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imagenFondo;

    @FXML
    private ImageView logo;

    @FXML
    private Text textoTeamUp;

    @FXML
    private TextField nombreUsuario;

    @FXML
    private TextField correoElectronico;

    @FXML
    private PasswordField contrasenia;

    @FXML
    private CheckBox recordarme;

    @FXML
    private Button registrarse;

    @FXML
    private ComboBox<String> posicion1;

    @FXML
    private ComboBox<String> posicion2;

    @FXML
    private Label mensajePosicion1;

    @FXML
    private Label mensajePosicion2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        imagenFondo.fitWidthProperty().bind(root.widthProperty());
        imagenFondo.fitHeightProperty().bind(root.heightProperty());

        posicion1.getItems().addAll("por", "dfc", "mc", "ld", "mco", "mcd","dc","ei","ed", "li");

        posicion2.getItems().addAll("por", "dfc", "mc", "ld", "mco", "mcd","dc","ei","ed", "li");
    }

    @FXML
    public void registroUsuario() {

        mensajePosicion1.setVisible(false);
        mensajePosicion2.setVisible(false);

        if (posicion1.getValue() == null) {
            mensajePosicion1.setVisible(true);
            return;
        }

        if (posicion2.getValue() == null || posicion1.getValue().equals(posicion2.getValue())) {
            mensajePosicion2.setVisible(true);
            return;
        }

        System.out.println("Nombre: " + nombreUsuario.getText());
        System.out.println("Correo: " + correoElectronico.getText());
        System.out.println("Contraseña: " + contrasenia.getText());
        System.out.println("Posicion 1: " + posicion1.getValue());
        System.out.println("Posicion 2: " + posicion2.getValue());
        System.out.println("Recordarme: " + recordarme.isSelected());

        // enviar json al servidor
    }

    @FXML
    private void volverAtras() {
        SistemaDeJuego.cambiarPantalla("pantallaLogReg.fxml");
    }
}