package com.example.teamupclienteescritorio.Utilidades;

import com.example.teamupclienteescritorio.Conexion.Cliente;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SistemaDeJuego { // Clase que emula el sistema de juego del server(sitio  centralizado con el funcionamiento), pero en el cliente y obviamente con menos cosas
    public static Cliente cliente;
    public static Stage stagePrincipal;


    public static void crearCliente() {
        cliente = new Cliente();
    }

    public static void cambiarPantalla(String nombreFxml) {

        try {
            System.out.println("TeamUp|MensajeInterno|Entramos en cambiar pantalla con: " + nombreFxml);
            FXMLLoader loader = new FXMLLoader(SistemaDeJuego.class.getResource("/com/example/teamupclienteescritorio/" + nombreFxml));

            Scene escena = new Scene(loader.load());
            stagePrincipal.setScene(escena);

            System.out.println("TeamUp|MensajeInterno|Hemos cambiado la pantalla a " + stagePrincipal.getTitle());

        } catch (Exception em) {
            System.out.println("TeamUp|Error|EM3");
            em.printStackTrace();
        }
    }

    public static void abrirPopup(String nombreFxml, String titulo) {

        try {
            FXMLLoader loader = new FXMLLoader(SistemaDeJuego.class.getResource("/com/example/teamupclienteescritorio/" + nombreFxml));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (Exception em) {
            System.out.println("TeamUp|Error|EM3");
        }
    }

}
