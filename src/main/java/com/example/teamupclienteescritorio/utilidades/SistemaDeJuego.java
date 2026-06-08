package com.example.teamupclienteescritorio.utilidades;

import com.example.teamupclienteescritorio.clasesSesion.Usuario;
import com.example.teamupclienteescritorio.controladores.PantallaErrorControlador;
import com.example.teamupclienteescritorio.controladores.PantallaObjetoSobreControlador;
import com.example.teamupclienteescritorio.logicaAplicacion.Cliente;
import com.example.teamupclienteescritorio.logicaAplicacion.Sesion;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SistemaDeJuego { // Clase que emula el sistema de juego del server(sitio  centralizado con el funcionamiento), pero en el cliente y obviamente con menos cosas
    public static Cliente cliente;
    public static Stage stagePrincipal;
    public static Object controladorActual;
    public static Stage popupActual;


    public static void crearCliente() {
        cliente = new Cliente();
    }

    public static void cambiarPantalla(String nombreFxml) {

        try {
            System.out.println("TeamUp|MensajeInterno|Entramos en cambiar pantalla con: " + nombreFxml);
            FXMLLoader loader = new FXMLLoader(SistemaDeJuego.class.getResource("/com/example/teamupclienteescritorio/" + nombreFxml));

            Scene escena = new Scene(loader.load());
            SistemaDeJuego.controladorActual = loader.getController();
            stagePrincipal.setScene(escena);

            System.out.println("TeamUp|MensajeInterno|Hemos cambiado la pantalla a " + stagePrincipal.getTitle());

        } catch (Exception em) {
            System.out.println("TeamUp|Error|EM3" + em.getMessage());
            em.printStackTrace();
        }
    }

    public static void abrirPopup(String nombreFxml, String titulo) {
        try {

            if (popupActual != null) { // esto lo hago para que por ejemplo abro ver mas detalles de partidos
                popupActual.close();
            }

            FXMLLoader loader = new FXMLLoader(SistemaDeJuego.class.getResource("/com/example/teamupclienteescritorio/" + nombreFxml));
            Scene scene = new Scene(loader.load());

            popupActual = new Stage();
            popupActual.setTitle(titulo);
            popupActual.setScene(scene);
            popupActual.setResizable(false);
            popupActual.setOnHidden(event -> popupActual = null);
            popupActual.show();

        } catch (Exception em) {
            System.out.println("TeamUp|Error|EM3" + em.getMessage());
        }
    }

    public static void abrirCosmeticoSobre(String nombre, String rareza, String rutaImagen) { // lo mismo que abrir mensaje pero para los sobres concretamente
        try {
            FXMLLoader loader = new FXMLLoader(SistemaDeJuego.class.getResource("/com/example/teamupclienteescritorio/pantallaObjetoSobre.fxml"));
            Scene scene = new Scene(loader.load());

            PantallaObjetoSobreControlador controlador = loader.getController();
            controlador.cargarCosmetico(nombre, rareza, rutaImagen);

            Stage stage = new Stage();
            stage.setTitle("Objeto conseguido");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (Exception em) {
            System.out.println("TeamUp|Error|EM3");
        }
    }

    public static void abrirMensaje(String mensaje) {
        try {
            FXMLLoader loader = new FXMLLoader(SistemaDeJuego.class.getResource("/com/example/teamupclienteescritorio/pantallaMensajeServidor.fxml"));
            Scene scene = new Scene(loader.load());

            PantallaErrorControlador controlador = loader.getController();
            controlador.setMensaje(mensaje);

            Stage stage = new Stage();
            stage.setTitle("Servidor Mensaje");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (Exception em) {
            System.out.println("TeamUp|Error|EM3");
        }
    }

    // lo pongo aqui porque es un metodo que van a usar bastantes pestañas
    public static void cargarDatosUsuario(ImageView imagenPerfil, ImageView imagenRango, ImageView tarjetaVisita, Label monedas, Label reputacion) {
        Usuario usuario = Sesion.getSesion().getUsuario();
        monedas.setText("Monedas: " + usuario.getMonedas());
        reputacion.setText("Reputación: " + usuario.getReputacion());

        Image imagenPlaceholder = new Image(SistemaDeJuego.class.getResourceAsStream("/com/example/teamupclienteescritorio/imagenes/logo.jpg"));
        Image tarjetaVisitaPlaceHolder = new Image(SistemaDeJuego.class.getResourceAsStream("/com/example/teamupclienteescritorio/imagenes/tarjetaVisitaPlaceHolder.png"));

        imagenPerfil.setImage(imagenPlaceholder);
        imagenRango.setImage(imagenPlaceholder);
        tarjetaVisita.setImage(tarjetaVisitaPlaceHolder);
    }

    





}
