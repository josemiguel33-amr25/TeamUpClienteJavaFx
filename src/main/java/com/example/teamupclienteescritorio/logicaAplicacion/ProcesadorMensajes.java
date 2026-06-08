package com.example.teamupclienteescritorio.logicaAplicacion;

import com.example.teamupclienteescritorio.clasesMensajes.Participante;
import com.example.teamupclienteescritorio.clasesMensajes.PartidoSimplificado;
import com.example.teamupclienteescritorio.clasesMensajes.SobreSimplificado;
import com.example.teamupclienteescritorio.clasesSesion.CartaUsuario;
import com.example.teamupclienteescritorio.clasesSesion.CosmeticoUsuario;
import com.example.teamupclienteescritorio.clasesSesion.DatosPartido;
import com.example.teamupclienteescritorio.clasesSesion.Usuario;
import com.example.teamupclienteescritorio.utilidades.SistemaDeJuego;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcesadorMensajes {

    public ProcesadorMensajes() {

    }

    public void buzon(String mensaje) {
        System.out.println("TeamUp|MensajeInterno| Entramos en buzon");
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> mensajeMapita = mapper.readValue(mensaje, Map.class);
            Map<String, String> datos = null;

            String opcion = (String) mensajeMapita.get("opcion");
            String codigo = (String) mensajeMapita.get("codigo");
            String mensajeServidor = (String) mensajeMapita.get("mensaje");
            String status = (String) mensajeMapita.get("status");
            System.out.println("TeamUp|MensajeInterno| Mensaje Recibido con: opcion: " + opcion + "  codigo: " + codigo + "  status: " + status  );

            switch (opcion) {
                case "registrarUsuario":
                    datos = mapper.convertValue(mensajeMapita.get("datos"), new com.fasterxml.jackson.core.type.TypeReference<Map<String, String>>() {});
                    procesarAutenticacion(status,mensajeServidor,codigo,datos);
                    break;
                case "iniciarSesion":
                    datos = mapper.convertValue(mensajeMapita.get("datos"), new com.fasterxml.jackson.core.type.TypeReference<Map<String, String>>() {});
                    procesarAutenticacion(status,mensajeServidor,codigo,datos);
                    break;
                case "obtenerPartidos":
                    Map<String, Object> datosPartidos = mapper.convertValue(mensajeMapita.get("datos"), new TypeReference<Map<String, Object>>() {});
                    procesarPartidos(status, mensajeServidor, codigo, datosPartidos);
                    break;
                case "unirsePartido":
                    procesarUnirsePartido(status, mensajeServidor, codigo);
                    break;
                case "verMasInfoPartido":
                    Map<String, Object> datosPartido = mapper.convertValue(mensajeMapita.get("datos"), new TypeReference<Map<String, Object>>() {});
                    procesarMasInfoPartido(status, mensajeServidor, codigo, datosPartido);
                    break;
                case "registrarPartido":
                    procesarCrearPartido(status, mensajeServidor, codigo);
                    break;
                case "abandonarPartido":
                    procesarAbandonarPartido(status, mensajeServidor, codigo); // a estas alturas me he dado cuenta de que pasar el codigo a las funciones solo sirve para la funcion de iniciar sesion pero bueno yo lo paso por si en el futuro lo usamos para dar mas feedback al usuario
                    break;
                case "partidoFinalizado":
                    procesarFinalizarPartido(status,mensajeServidor,codigo);
                    break;
                case "obtenerPartidosUsuario":
                    Map<String, Object> datosPartidoUsuario = mapper.convertValue(mensajeMapita.get("datos"), new TypeReference<Map<String, Object>>() {});
                    procesarPartidosUsuario(status, mensajeServidor, codigo, datosPartidoUsuario);
                    break;
                case "verSobres":
                    Map<String, Object> datosSobresUsuario = mapper.convertValue(mensajeMapita.get("datos"), new TypeReference<Map<String, Object>>() {});
                    procesarVerSobresUsuario(status, mensajeServidor, codigo, datosSobresUsuario);
                    break;
                case "abrirSobre":
                    Map<String, String> datosSobreAbierto = mapper.convertValue(mensajeMapita.get("datos"), new TypeReference<Map<String, String>>() {});
                    procesarAbrirSobre(status, mensajeServidor, codigo, datosSobreAbierto.get("nombreCosmetico"), datosSobreAbierto.get("rareza"));
                    break;
                case "verSobresTienda": // super parecida a ver Sobres pero sobres de la tienda por tanto estan a la venta y no precisan el dato de cantidad solo precio
                    Map<String, Object> datosSobreTienda = mapper.convertValue(mensajeMapita.get("datos"), new TypeReference<Map<String, Object>>() {});
                    procesarVerSobresTienda(status, mensajeServidor, codigo, datosSobreTienda);
                    break;
                case "comprarSobre":
                    Map<String, String> datosComprarSobre = mapper.convertValue(mensajeMapita.get("datos"), new TypeReference<Map<String, String>>() {});
                    procesarComprarSobre(status, mensajeServidor, codigo, datosComprarSobre);
                    break;
            }
        } catch (Exception em) {
            System.out.println("TeamUp|Error|EM5 " + em.getMessage());
        }
    }

    public void procesarComprarSobre(String status, String mensajeServidor, String codigo, Map<String,String> datosComprarSobre) {
        SistemaDeJuego.abrirMensaje(mensajeServidor);

        if (status.equalsIgnoreCase("perfecto")) {
            int monedas = Integer.parseInt(datosComprarSobre.get("nuevasMonedas"));
            Sesion.getSesion().getUsuario().setMonedas(monedas);
            SistemaDeJuego.cambiarPantalla("pantallaSobres.fxml");
        }
    }

    public void procesarVerSobresTienda(String status, String mensajeServidor, String codigo,Map<String,Object> datosSobresUsuario ) {
        if (status.equalsIgnoreCase("perfecto")) {
            ObjectMapper mapper = new ObjectMapper();
            List<SobreSimplificado> sobres = mapper.convertValue(datosSobresUsuario.get("sobres"), new TypeReference<List<SobreSimplificado>>() {});
            Sesion.getSesion().setSobresTienda(sobres);
            SistemaDeJuego.cambiarPantalla("pantallaSobres.fxml");
        } else {
            SistemaDeJuego.abrirMensaje(mensajeServidor);
        }
    }

    public void procesarAbrirSobre(String status, String mensajeServidor, String codigo, String nombreCosmetico, String rareza) {
        if (status.equalsIgnoreCase("perfecto")) {
            SistemaDeJuego.abrirCosmeticoSobre(nombreCosmetico, rareza, nombreCosmetico); // la ruta imagen es el tercer argumento es el mismo nombre que el cosmetico porque asi refenciaremos a la imagen en el servidor http
            pedirMisSobres();
        } else
            SistemaDeJuego.abrirMensaje(mensajeServidor);
    }

    public void procesarVerSobresUsuario(String status, String mensajeServidor, String codigo, Map<String,Object> datosSobresUsuario) {
        if (status.equalsIgnoreCase("perfecto")) {
            ObjectMapper mapper = new ObjectMapper();
            List<SobreSimplificado> sobres = mapper.convertValue(datosSobresUsuario.get("sobres"), new TypeReference<List<SobreSimplificado>>() {});
            Sesion.getSesion().setSobresUsuario(sobres);
            SistemaDeJuego.cambiarPantalla("pantallaMisSobres.fxml");
        } else {
            SistemaDeJuego.abrirMensaje(mensajeServidor);
        }
    }

    public void procesarCrearPartido(String status, String mensajeServidor, String codigo) {
        SistemaDeJuego.abrirMensaje(mensajeServidor);
    }

    public void procesarFinalizarPartido(String status, String mensajeServidor, String codigo) {
        SistemaDeJuego.abrirMensaje(mensajeServidor); // misma idea en procesar abandonar partido, recargamos y asi recargamos tambien la interfaz
        if (status.equalsIgnoreCase("perfecto")) {
            pedirMasInfoPartido(Sesion.getSesion().getPartidoActual().getIdPartido());
        }
    }

    public void procesarAbandonarPartido(String status, String mensajeServidor, String codigo) {
        SistemaDeJuego.abrirMensaje(mensajeServidor);

        if (status.equalsIgnoreCase("perfecto")) {
            pedirMasInfoPartido(Sesion.getSesion().getPartidoActual().getIdPartido());
        }
    }

    private void procesarPartidosUsuario(String status, String mensajeServidor, String codigo, Map<String,Object> datosPartidos)  {
        if (status.equalsIgnoreCase("perfecto")) {
            ObjectMapper mapper = new ObjectMapper();
            List<PartidoSimplificado> partidos = mapper.convertValue(datosPartidos.get("partidos"), new TypeReference<List<PartidoSimplificado>>() {});
            Sesion.getSesion().setPartidos(partidos);
            SistemaDeJuego.cambiarPantalla("pantallaMisPartidos.fxml");
        } else {
            SistemaDeJuego.abrirMensaje(mensajeServidor);
        }
    }


    private void pedirMasInfoPartido(int idPartido) {
        try { // esta funcion la uso para cuando un usuario abandona / pasa partido a finalizado etc se llame a esta funcion y se actualice el estado del partido actual, esto mas una modificacion que le he hecho a abrir pop up para que cada vez que se abra uno se cierre el anterior hacen que si abandono partido se actualice la pestaña
            Map<String, Object> mensaje = new HashMap<>();
            Map<String, String> data = new HashMap<>();

            data.put("tipoPartido", "masInfoPartido");
            data.put("idPartido", String.valueOf(idPartido));

            mensaje.put("tipo", "partidos");
            mensaje.put("data", data);

            ObjectMapper mapper = new ObjectMapper();

            SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));
        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }

    private void pedirMisSobres() {
        try {
            Map<String, Object> mensaje = new HashMap<>();
            Map<String, String> data = new HashMap<>();

            data.put("tipoCosmeticos", "verSobres");
            data.put("tipo", "usuario");

            mensaje.put("tipo", "cosmeticos");
            mensaje.put("data", data);

            ObjectMapper mapper = new ObjectMapper();

            SistemaDeJuego.cliente.enviarMensaje(mapper.writeValueAsString(mensaje));
        } catch (JsonProcessingException em) {
            System.out.println("TeamUp|Error|EM9");
        }
    }

    public void procesarPartidos(String status, String mensajeServidor, String codigo, Map<String, Object> datosPartidos) {
        if (status.equalsIgnoreCase("perfecto")) {
            ObjectMapper mapper = new ObjectMapper();
            List<PartidoSimplificado> partidos = mapper.convertValue(datosPartidos.get("partidos"), new TypeReference<List<PartidoSimplificado>>() {});
            Sesion.getSesion().setPartidos(partidos);
            SistemaDeJuego.cambiarPantalla("pantallaPartidos.fxml");
        } else {
            SistemaDeJuego.abrirMensaje(mensajeServidor);
        }
    }



    public void procesarMasInfoPartido(String status, String mensajeServidor, String codigo, Map<String, Object> datosPartido) {
        if (status.equalsIgnoreCase("perfecto")) {
            ObjectMapper mapper = new ObjectMapper();

            List<Participante> participantes = mapper.convertValue(datosPartido.get("participantes"), new TypeReference<List<Participante>>() {});
            DatosPartido partido = new DatosPartido((Integer) datosPartido.get("idPartido"), (Integer) datosPartido.get("creadorPartido"), (String) datosPartido.get("estadoPartido"), participantes);

            Sesion.getSesion().setPartidoActual(partido);
            SistemaDeJuego.abrirPopup("pantallaVerDetallesPartido.fxml", "Mas Info Partido");
        } else
            SistemaDeJuego.abrirMensaje(mensajeServidor);
    }

    public void procesarUnirsePartido(String status, String mensajeServidor, String codigo) {
        SistemaDeJuego.abrirMensaje(mensajeServidor);
    }

    public void procesarAutenticacion(String status, String mensajeServidor, String codigo, Map<String,String> mapaDatos ) {
        System.out.println("TeamUp|MensajeInterno| El mensaje del servidor es: " + mensajeServidor);
        if (status.equalsIgnoreCase("perfecto")) {
            CartaUsuario cartaUsuario = new CartaUsuario(Integer.parseInt(mapaDatos.get("mediaJugador")), Integer.parseInt(mapaDatos.get("ritmo")), Integer.parseInt(mapaDatos.get("tiro")), Integer.parseInt(mapaDatos.get("pase")), Integer.parseInt(mapaDatos.get("regate")), Integer.parseInt(mapaDatos.get("defensa")), Integer.parseInt(mapaDatos.get("fisico")), Integer.parseInt(mapaDatos.get("mediaPortero")), Integer.parseInt(mapaDatos.get("posicionamiento")), Integer.parseInt(mapaDatos.get("reflejos")), Integer.parseInt(mapaDatos.get("manejo")), Integer.parseInt(mapaDatos.get("estirada")), Integer.parseInt(mapaDatos.get("saque")), mapaDatos.get("posicion1"), mapaDatos.get("posicion2"), Integer.parseInt(mapaDatos.get("velocidad")));
            CosmeticoUsuario cosmeticoUsuario = new CosmeticoUsuario(mapaDatos.get("tarjetaVisita"), mapaDatos.get("titulo"), mapaDatos.get("cartaCosmetico"));
            Usuario usuario = new Usuario(Integer.parseInt(mapaDatos.get("idUsuario")), mapaDatos.get("nombre"), mapaDatos.get("correo"), Boolean.parseBoolean(mapaDatos.get("verificado")), Integer.parseInt(mapaDatos.get("monedas")), Integer.parseInt(mapaDatos.get("reputacion")), Integer.parseInt(mapaDatos.get("rango")), Integer.parseInt(mapaDatos.get("puntosRango")), Integer.parseInt(mapaDatos.get("partidosJugados")), Integer.parseInt(mapaDatos.get("goles")), Integer.parseInt(mapaDatos.get("asistencias")), Integer.parseInt(mapaDatos.get("mvp")), mapaDatos.get("fotoperfil"), cosmeticoUsuario, cartaUsuario);
            Sesion.getSesion().setUsuario(usuario);

            if (mapaDatos.containsKey("selector") && mapaDatos.containsKey("token")) {
                crearArchivoToken(mapaDatos.get("selector"), mapaDatos.get("token"));
            }

            SistemaDeJuego.cambiarPantalla("pantallaPrincipal.fxml");
        } else {
            if (codigo.equals("ertkNe")) {
                SistemaDeJuego.cambiarPantalla("pantallaLog.fxml");
            }
            SistemaDeJuego.abrirMensaje(mensajeServidor);
        }
    }

    private void crearArchivoToken(String selector, String token) {
        try {
            File carpeta = new File("teamUp");

            File archivo = new File(carpeta, "token.txt");

            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
                pw.println(selector + ":" + token);
            }
            System.out.println("TeamUp|MensajeInterno| Archivo creado perfectamente");

        } catch (Exception em) {
            System.out.println("TeamUp|Error|EM7");
        }
    }

}
