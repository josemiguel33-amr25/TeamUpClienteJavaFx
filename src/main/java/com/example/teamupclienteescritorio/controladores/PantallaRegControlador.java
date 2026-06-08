package com.example.teamupclienteescritorio.controladores;

import com.example.teamupclienteescritorio.utilidades.SistemaDeJuego;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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

        String nombre = nombreUsuario.getText().trim();
        String correo = correoElectronico.getText().trim();
        String password = contrasenia.getText();

        if (nombre.isEmpty()) {
            System.out.println("TeamUp|Error|Nombre vacío");
            return;
        }

        if (correo.isEmpty()) {
            System.out.println("TeamUp|Error|Correo vacío");
            return;
        }

        if (password.isEmpty()) {
            System.out.println("TeamUp|Error|Contraseña vacía");
            return;
        }

        if (nombre.length() < 4 || nombre.length() > 20) {
            System.out.println("TeamUp|Error|Nombre inválido");
            return;
        }


        if (password.length() < 8) {
            System.out.println("TeamUp|Error|Contraseña demasiado corta");
            return;
        }

        if (posicion1.getValue() == null) {
            mensajePosicion1.setVisible(true);
            return;
        }

        if (posicion2.getValue() == null || posicion1.getValue().equals(posicion2.getValue())) {
            mensajePosicion2.setVisible(true);
            return;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> mensaje = new HashMap<>();
            mensaje.put("tipo", "registro");

            Map<String, String> datos = new HashMap<>();

            datos.put("nombre", nombre);
            datos.put("correo", correo);
            datos.put("contrasenia", password);
            datos.put("posicion1", posicion1.getValue());
            datos.put("posicion2", posicion2.getValue());
            datos.put("recordarme", recordarme.isSelected() ? "1" : "0");

            mensaje.put("data", datos);

            String json = mapper.writeValueAsString(mensaje);

            SistemaDeJuego.cliente.enviarMensaje(json);

        } catch (Exception em) {
            System.out.println("TeamUp|Error|EMR1");
            em.printStackTrace();
        }
    }

    @FXML
    private void volverAtras() {
        SistemaDeJuego.cambiarPantalla("pantallaLogReg.fxml");
    }
}