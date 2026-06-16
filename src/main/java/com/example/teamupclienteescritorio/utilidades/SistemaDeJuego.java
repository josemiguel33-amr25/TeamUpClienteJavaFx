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

import java.net.HttpURLConnection;
import java.net.URL;

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
            escena.getStylesheets().add(SistemaDeJuego.class.getResource("/com/example/teamupclienteescritorio/css/estilos.css").toExternalForm());

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
            if (popupActual != null) { // esto lo hago para que por ejemplo abro ver mas detalles de partidos y
                popupActual.close();
            }

            FXMLLoader loader = new FXMLLoader(SistemaDeJuego.class.getResource("/com/example/teamupclienteescritorio/" + nombreFxml));

            Scene scene = new Scene(loader.load());
            //scene.getStylesheets().add(SistemaDeJuego.class.getResource("/com/example/teamupclienteescritorio/css/estilos.css").toExternalForm());

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

    public static void cerrarPopup() { // funcion nueva para cerrar el pop up, por ejemplo abrimos venta articulo >> ponemos en venta un articulo >> la respuesta es perfecto >> pues cerramos el pop up
        if (popupActual != null) {
            popupActual.close();
            popupActual = null;
        }
    }

    public static String obtenerUrlServidorHttp() {
        return "http://" + cliente.getPropiedades().getProperty("iphttp") + ":" + cliente.getPropiedades().getProperty("puertohttp");
    }

    public static boolean comprobarServidorHttp() {
        try {
            URL url = new URL(obtenerUrlServidorHttp() + "/comprobacion");

            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

            conexion.setRequestMethod("GET");
            conexion.setConnectTimeout(2000);

            return conexion.getResponseCode() == 200;
        } catch (Exception em) {
            System.out.println("TeamUp|MensajeInterno| Entro en expecion (es una excepcion pensada para qdevolver falso por eso no tiene codigo)");
            return false;
        }
    }

    // funciones relacionadas con la construccion de la ruta http
    public static Image cargarImagen(String categoria, String archivo) {
        System.out.println("teamUp|MensajeInterno| Categoria pedida: " + categoria + " archivo pedido: " + archivo + "\n Ruta: " + categoria + "/" + archivo);
        return new Image(obtenerUrlServidorHttp() + "/" + categoria + "/" + archivo);
    }

    public static String nombreArchivo(String texto) { // esta funcion es importante porque en la base de datos guardo los nombres de l os cosmeticos y el nombre es el mismo que se previsualiza como nombre del cosmetico pero tambien es el nombre del cosmetico en el servidor http, por eso con esto quito espacios y pongo todo en minusculas
        return texto.toLowerCase().replace(" ", "_");
    }

    public static String obtenerCosmeticoRuta(String tipoCosmetico) { // esto es porque en cosmetico simplificado al momento de cargar mercado y inventario el tipo no coincide con el nombre de las carpetas en el servidor http
        String carpeta = "";

        if (tipoCosmetico.equalsIgnoreCase("carta"))
            carpeta = "cartas";
        else if (tipoCosmetico.equalsIgnoreCase("tarjetaVisita"))
            carpeta = "tarjetasVisita";
        else
            carpeta = "titulos";

        return carpeta;
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
            scene.getStylesheets().add(SistemaDeJuego.class.getResource("/com/example/teamupclienteescritorio/css/mensajes.css").toExternalForm());

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

        imagenPerfil.setImage(SistemaDeJuego.cargarImagen("fotosPerfil", SistemaDeJuego.nombreArchivo(usuario.getFotoPerfil()) + ".png"));
        imagenRango.setImage(SistemaDeJuego.cargarImagen("rangos", SistemaDeJuego.nombreArchivo(usuario.getNombreRango()) + ".png"));
        tarjetaVisita.setImage(SistemaDeJuego.cargarImagen("tarjetasVisita", SistemaDeJuego.nombreArchivo(usuario.getCosmeticoUsuario().getTarjetaVisita()) + ".png"));
    }

    





}
