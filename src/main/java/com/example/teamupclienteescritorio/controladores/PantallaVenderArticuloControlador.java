package com.example.teamupclienteescritorio.controladores;

import com.example.teamupclienteescritorio.clasesMensajes.CosmeticoSimplificado;
import com.example.teamupclienteescritorio.logicaAplicacion.Sesion;
import com.example.teamupclienteescritorio.utilidades.SistemaDeJuego;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PantallaVenderArticuloControlador implements Initializable {

    @FXML
    private Button venderBoton;

    @FXML
    private TextField precio;

    @FXML
    private ComboBox<CosmeticoSimplificado> articulosInventario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        articulosInventario.getItems().addAll(Sesion.getSesion().getInventarioJugador());
    }

    @FXML
    private void venderArticulo() {
        if (articulosInventario.getValue() == null) {
            SistemaDeJuego.abrirMensaje("Debes seleccionar un artículo");
            return;
        }

        if (precio.getText().isBlank()) {
            SistemaDeJuego.abrirMensaje("Debes introducir un precio");
            return;
        }

        int precioVenta;

        try {
            precioVenta = Integer.parseInt(precio.getText());
        } catch (NumberFormatException e) {
            SistemaDeJuego.abrirMensaje("El precio debe ser un numero");
            return;
        }

        if (precioVenta <= 0) {
            SistemaDeJuego.abrirMensaje("El precio debe ser mayor que 0");
            return;
        }

        enviarVentaArticulo(articulosInventario.getValue().getIdCosmetico(), precioVenta);
    }

    private void enviarVentaArticulo(int idCosmetico, int precio) {
        try {
            Map<String, Object> mensaje = new HashMap<>();
            Map<String, String> data = new HashMap<>();

            data.put("tipoCosmeticos", "venderArticulo");
            data.put("idCosmetico", String.valueOf(idCosmetico));
            data.put("precio", String.valueOf(precio));

            mensaje.put("tipo", "cosmeticos");
            mensaje.put("data", data);

            ObjectMapper mapper = new ObjectMapper();

            SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));
        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }
}