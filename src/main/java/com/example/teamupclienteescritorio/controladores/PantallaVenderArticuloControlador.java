package com.example.teamupclienteescritorio.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PantallaVenderArticuloControlador implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imagenObjeto;

    @FXML
    private Label nombreArticulo;

    @FXML
    private Label rareza;

    @FXML
    private Label tipoCosmetico;

    @FXML
    private TextField precio;

    @FXML
    private Button venderBoton;


    private int idCosmetico;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void cargarArticulo(int idCosmetico, String nombre, String rarezaArticulo, String tipo) { // se me ocurrio esto por ahora pero no se si sera lo que usare para pasar datos
        this.idCosmetico = idCosmetico;
        nombreArticulo.setText(nombre);
        rareza.setText(rarezaArticulo);
        tipoCosmetico.setText(tipo);
    }


    @FXML
    private void venderArticulo() {
        try {
            int precioVenta = Integer.parseInt(precio.getText());
            System.out.println("TeamUp|MensajeInterno|Vendiendo articulo");
            System.out.println("TeamUp|MensajeInterno|ID cosmetico: " + idCosmetico);
            System.out.println("TeamUp|MensajeInterno|Precio: " + precioVenta);
        } catch (NumberFormatException em) {
            System.out.println("TeamUp|Error|EM6");
        }
    }
}