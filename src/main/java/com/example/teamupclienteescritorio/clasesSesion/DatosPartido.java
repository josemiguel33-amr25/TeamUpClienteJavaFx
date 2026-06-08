package com.example.teamupclienteescritorio.clasesSesion;

import com.example.teamupclienteescritorio.clasesMensajes.Participante;

import java.util.List;

public class DatosPartido { // esta clase esta hecha para el partido cuando se le da a ver mas detalles
    private int idPartido;
    private int creadorPartido;
    private String estadoPartido;
    private List<Participante> participantes; // clase json

    public DatosPartido(int idPartido, int creadorPartido, String estadoPartido, List<Participante> participantes) {
        this.idPartido = idPartido;
        this.creadorPartido = creadorPartido;
        this.estadoPartido = estadoPartido;
        this.participantes = participantes;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public int getCreadorPartido() {
        return creadorPartido;
    }

    public String getEstadoPartido() {
        return estadoPartido;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public void setCreadorPartido(int creadorPartido) {
        this.creadorPartido = creadorPartido;
    }

    public void setEstadoPartido(String estadoPartido) {
        this.estadoPartido = estadoPartido;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public boolean esCreador(int idJugador) {
        boolean esCreador = false;
        if (creadorPartido == idJugador)
            esCreador = true;

        return esCreador;
    }

    public boolean participaJugador(int idJugador) {
        boolean participa = false;
        for (Participante participante : participantes) {
            if (participante.getIdUsuario() == idJugador) {
                participa = true;
                break;
            }
        }

        return participa;
    }
}
