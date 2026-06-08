package com.example.teamupclienteescritorio.clasesSesion;

public class CosmeticoUsuario {
    private String tarjetaVisita;
    private String titulo;
    private String cartaCosmetico;

    public CosmeticoUsuario(String tarjetaVisita, String titulo, String cartaCosmetico) {
        this.tarjetaVisita = tarjetaVisita;
        this.titulo = titulo;
        this.cartaCosmetico = cartaCosmetico;
    }

    public String getTarjetaVisita() {
        return tarjetaVisita;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCartaCosmetico() {
        return cartaCosmetico;
    }

    public void setTarjetaVisita(String tarjetaVisita) {
        this.tarjetaVisita = tarjetaVisita;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCartaCosmetico(String cartaCosmetico) {
        this.cartaCosmetico = cartaCosmetico;
    }
}
