package com.example.teamupclienteescritorio.logicaAplicacion;

import com.example.teamupclienteescritorio.clasesMensajes.*;
import com.example.teamupclienteescritorio.clasesSesion.DatosPartido;
import com.example.teamupclienteescritorio.clasesSesion.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Sesion { // clase que me recuerda bastante al view model en android la verdad
    private static Sesion sesion;
    private Usuario usuario;

    // Partidos disponibles, filtros se hacen llamando al servidor
    private List<PartidoSimplificado> partidos = new ArrayList<>();

    // sobres disponibles en la tienda
    private List<SobreSimplificado> sobresTienda = new ArrayList<>();

    // sobres que el usuario tiene
    private List<SobreSimplificado> sobresUsuario = new ArrayList<>();

    // inventario del jugador
    private List<CosmeticoSimplificado> inventarioJugador = new ArrayList<>();

    // objetos disponibles para comprar en el mercado
    private List<MercadoSimplificado> objetosMercadosGlobal = new ArrayList<>();

    //objetos en venta del usuario
    private List<MercadoSimplificado> objetosMercadoUsuario = new ArrayList<>();

    // jugadores del ranking
    private List<UsuarioSimplificado> usuariosRanking = new ArrayList<>();

    private DatosPartido partidoActual;


    private Sesion() {

    }

    public static Sesion getSesion() {
        if (sesion == null)
            sesion = new Sesion();
        return sesion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<SobreSimplificado> getSobresTienda() {
        return sobresTienda;
    }

    public List<CosmeticoSimplificado> getInventarioJugador() {
        return inventarioJugador;
    }

    public List<UsuarioSimplificado> getUsuariosRanking() {
        return usuariosRanking;
    }

    public void setUsuariosRanking(List<UsuarioSimplificado> usuariosRanking) {
        this.usuariosRanking = usuariosRanking;
    }

    public List<MercadoSimplificado> getObjetosMercadosUsuario() {
        return objetosMercadoUsuario;
    }

    public void setObjetosMercadosUsuario(List<MercadoSimplificado> objetosMercadoUsuario) {
        this.objetosMercadoUsuario = objetosMercadoUsuario;
    }

    public List<MercadoSimplificado> getObjetosMercadosGlobal() {
        return objetosMercadosGlobal;
    }

    public void setObjetosMercadosGlobal(List<MercadoSimplificado> objetosMercadosGlobal) {
        this.objetosMercadosGlobal = objetosMercadosGlobal;
    }

    public List<SobreSimplificado> getSobresUsuario() {
        return sobresUsuario;
    }

    public void setSobresUsuario(List<SobreSimplificado> sobresUsuario) {
        this.sobresUsuario = sobresUsuario;
    }

    public void setInventarioJugador(List<CosmeticoSimplificado> inventarioJugador) {
        this.inventarioJugador = inventarioJugador;
    }

    public void setSobresTienda(List<SobreSimplificado> sobresTienda) {
        this.sobresTienda = sobresTienda;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<PartidoSimplificado> getPartidosSimplificados() {
        return partidos;
    }

    public void setPartidos(List<PartidoSimplificado> partidos) {
        this.partidos = partidos;
    }

    public DatosPartido getPartidoActual() {
        return partidoActual;
    }

    public void setPartidoActual(DatosPartido partidoActual) {
        this.partidoActual = partidoActual;
    }



}
