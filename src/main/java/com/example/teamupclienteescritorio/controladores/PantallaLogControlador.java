package com.example.teamupclienteescritorio.controladores;

import com.example.teamupclienteescritorio.utilidades.SistemaDeJuego;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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

        String correo = correoElectronico.getText().trim();
        String password = contrasenia.getText();

        if (correo.isEmpty()) {
            mensajePosicion11.setVisible(true);
            return;
        }

        if (password.isEmpty()) {
            mensajePosicion111.setVisible(true);
            return;
        }

        try {

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> mensaje = new HashMap<>();
            mensaje.put("tipo", "iniciarSesion");

            Map<String, String> datos = new HashMap<>();
            datos.put("correo", correo);
            datos.put("contrasenia", password);
            datos.put("remember", "no");

            mensaje.put("data", datos);

            String json = mapper.writeValueAsString(mensaje);

            System.out.println(json);

            SistemaDeJuego.cliente.enviarMensaje(json);

        } catch (Exception em) {

            System.out.println("TeamUp|Error|EMIS1");
            em.printStackTrace();
        }
    }

    @FXML
    private void volverAtras() {

        SistemaDeJuego.cambiarPantalla("pantallaLogReg.fxml");
    }
}