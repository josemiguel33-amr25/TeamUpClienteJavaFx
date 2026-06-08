package com.example.teamupclienteescritorio.controladores;

import com.example.teamupclienteescritorio.utilidades.SistemaDeJuego;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    private void iniciarSesion() { // la funcion esta es simplemente antes de ir a inicia sesion comprobamos si el usuario tiene el token, si el usuario lo tiene generado, pues se manda al servidor a comprobarlo y si esta bien se da paso directamente a como si hubieras iniciado sesion (que teoricamente es lo que has hecho)

        try {
            File archivoToken = new File("teamUp/token.txt");
            if (archivoToken.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(archivoToken));
                String linea = br.readLine();

                br.close();

                if (linea != null && linea.contains(":")) {
                    String[] partes = linea.split(":");
                    String selector = partes[0];
                    String token = partes[1];

                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, Object> mensaje = new HashMap<>();
                    mensaje.put("tipo", "iniciarSesion");
                    Map<String, String> datos = new HashMap<>();

                    datos.put("remember", "si");
                    datos.put("selector", selector);
                    datos.put("token", token);
                    mensaje.put("data", datos);
                    String json = mapper.writeValueAsString(mensaje);

                    SistemaDeJuego.cliente.enviarMensaje(json);

                    return;
                }
            }

            SistemaDeJuego.cambiarPantalla("pantallaLog.fxml");

        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
            SistemaDeJuego.cambiarPantalla("pantallaLog.fxml");
        } catch (IOException em) {
            System.out.println("TeamUp|Error|EM0");
        }
    }

    @FXML
    private void salir() {

        SistemaDeJuego.cliente.salir();
    }

}