package com.example.teamupclienteescritorio.Controladores;

import com.example.teamupclienteescritorio.Utilidades.SistemaDeJuego;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class PantallaCargaControlador implements Initializable {

    @FXML
    private ImageView imagenFondo;

    @FXML
    private AnchorPane root;

    @FXML
    private Text textoCualquierTecla;

    @FXML
    private Text textoTeamUp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("TeamUp|MensajeInterno|Aplicacion Iniciada");
        imagenFondo.fitWidthProperty().bind(root.widthProperty());
        imagenFondo.fitHeightProperty().bind(root.heightProperty());

    }



}