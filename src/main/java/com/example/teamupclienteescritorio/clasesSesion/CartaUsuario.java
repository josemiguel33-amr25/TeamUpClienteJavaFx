package com.example.teamupclienteescritorio.clasesSesion;

public class CartaUsuario {
    private int mediaJugador;
    private int ritmo;
    private int tiro;
    private int pase;
    private int regate;
    private int defensa;
    private int fisico;
    private int mediaPortero;
    private int posicionamiento;
    private int reflejos;
    private int manejo;
    private int estirada;
    private int saque;
    private String posicion1;
    private String posicion2;
    private int velocidad;


    public CartaUsuario(int mediaJugador, int ritmo, int tiro, int pase, int regate, int defensa, int fisico, int mediaPortero, int posicionamiento, int reflejos, int manejo, int estirada, int saque, String posicion1, String posicion2, int velocidad) {
        this.mediaJugador = mediaJugador;
        this.ritmo = ritmo;
        this.tiro = tiro;
        this.pase = pase;
        this.regate = regate;
        this.defensa = defensa;
        this.fisico = fisico;
        this.mediaPortero = mediaPortero;
        this.posicionamiento = posicionamiento;
        this.reflejos = reflejos;
        this.manejo = manejo;
        this.estirada = estirada;
        this.saque = saque;
        this.posicion1 = posicion1;
        this.posicion2 = posicion2;
        this.velocidad = velocidad;
    }

    public int getMediaJugador() {
        return mediaJugador;
    }

    public void setMediaJugador(int mediaJugador) {
        this.mediaJugador = mediaJugador;
    }

    public int getRitmo() {
        return ritmo;
    }

    public void setRitmo(int ritmo) {
        this.ritmo = ritmo;
    }

    public int getTiro() {
        return tiro;
    }

    public void setTiro(int tiro) {
        this.tiro = tiro;
    }

    public int getPase() {
        return pase;
    }

    public void setPase(int pase) {
        this.pase = pase;
    }

    public int getRegate() {
        return regate;
    }

    public void setRegate(int regate) {
        this.regate = regate;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getFisico() {
        return fisico;
    }

    public void setFisico(int fisico) {
        this.fisico = fisico;
    }

    public int getMediaPortero() {
        return mediaPortero;
    }

    public void setMediaPortero(int mediaPortero) {
        this.mediaPortero = mediaPortero;
    }

    public int getPosicionamiento() {
        return posicionamiento;
    }

    public void setPosicionamiento(int posicionamiento) {
        this.posicionamiento = posicionamiento;
    }

    public int getReflejos() {
        return reflejos;
    }

    public void setReflejos(int reflejos) {
        this.reflejos = reflejos;
    }

    public int getManejo() {
        return manejo;
    }

    public void setManejo(int manejo) {
        this.manejo = manejo;
    }

    public int getEstirada() {
        return estirada;
    }

    public void setEstirada(int estirada) {
        this.estirada = estirada;
    }

    public int getSaque() {
        return saque;
    }

    public void setSaque(int saque) {
        this.saque = saque;
    }

    public String getPosicion1() {
        return posicion1;
    }

    public void setPosicion1(String posicion1) {
        this.posicion1 = posicion1;
    }

    public String getPosicion2() {
        return posicion2;
    }

    public void setPosicion2(String posicion2) {
        this.posicion2 = posicion2;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
}
