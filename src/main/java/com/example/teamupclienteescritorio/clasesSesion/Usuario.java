package com.example.teamupclienteescritorio.clasesSesion;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String correo;
    private boolean verificado;
    private int monedas;
    private int reputacion;
    private int rango;
    private int puntosRango;
    private int partidosJugados;
    private int goles;
    private int asistencias;
    private int cantidadMvp;
    private String fotoPerfil;
    // Cosmeticos
    private CosmeticoUsuario cosmeticoUsuario;
    // Carta
    private CartaUsuario cartaUsuario;

    public Usuario(int idUsuario, String nombre, String correo, boolean verificado, int monedas, int reputacion, int rango, int puntosRango, int partidosJugados, int goles, int asistencias, int cantidadMvp, String fotoPerfil, CosmeticoUsuario cosmeticoUsuario, CartaUsuario cartaUsuario) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.verificado = verificado;
        this.monedas = monedas;
        this.reputacion = reputacion;
        this.rango = rango;
        this.puntosRango = puntosRango;
        this.partidosJugados = partidosJugados;
        this.goles = goles;
        this.asistencias = asistencias;
        this.cantidadMvp = cantidadMvp;
        this.fotoPerfil = fotoPerfil;
        this.cosmeticoUsuario = cosmeticoUsuario;
        this.cartaUsuario = cartaUsuario;
    }

    public CartaUsuario getCartaUsuario() {
        return cartaUsuario;
    }

    public CosmeticoUsuario getCosmeticoUsuario() {
        return cosmeticoUsuario;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public int getCantidadMvp() {
        return cantidadMvp;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public int getGoles() {
        return goles;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public int getPuntosRango() {
        return puntosRango;
    }

    public int getRango() {
        return rango;
    }

    public int getReputacion() {
        return reputacion;
    }

    public int getMonedas() {
        return monedas;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public void setCantidadMvp(int cantidadMvp) {
        this.cantidadMvp = cantidadMvp;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public void setPuntosRango(int puntosRango) {
        this.puntosRango = puntosRango;
    }

    public void setRango(int rango) {this.rango = rango;}

    public void setReputacion(int reputacion) {
        this.reputacion = reputacion;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
