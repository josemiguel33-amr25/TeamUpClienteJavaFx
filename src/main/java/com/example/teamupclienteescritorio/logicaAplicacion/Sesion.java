package com.example.teamupclienteescritorio.logicaAplicacion;

import com.example.teamupclienteescritorio.clasesMensajes.CosmeticoSimplificado;
import com.example.teamupclienteescritorio.clasesMensajes.PartidoSimplificado;
import com.example.teamupclienteescritorio.clasesMensajes.SobreSimplificado;
import com.example.teamupclienteescritorio.clasesSesion.DatosPartido;
import com.example.teamupclienteescritorio.clasesSesion.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Sesion {
    private static Sesion sesion;
    private Usuario usuario;
    private List<PartidoSimplificado> partidos = new ArrayList<>();
    private List<SobreSimplificado> sobresTienda = new ArrayList<>();
    private List<SobreSimplificado> sobresUsuario = new ArrayList<>();
    private List<CosmeticoSimplificado> inventarioJugador = new ArrayList<>();
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
