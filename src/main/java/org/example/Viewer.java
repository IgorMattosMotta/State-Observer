package org.example;

import java.util.Observable;
import java.util.Observer;

public class Viewer implements Observer {
    private String nome;
    private String ultimoVideoNotificado;
    private ViewerEstado estado;

    public Viewer(String nome) {
        this.nome = nome;
        this.estado = ViewerEstadoDesinscrito.getInstance();
    }

    public String getNome() {
        return nome;
    }

    public void setEstado(ViewerEstado estado) {
        this.estado = estado;
    }

    public ViewerEstado getEstado() {
        return estado;
    }

    public boolean inscrever(CanalYoutube canal) {
        return estado.inscrever(this, canal);
    }

    public boolean desinscrever(CanalYoutube canal) {
        return estado.desinscrever(this, canal);
    }

    public boolean apoiar() {
        return estado.apoiar(this);
    }

    public boolean tornarMembro() {
        return estado.virarMembro(this);
    }

    public String getUltimoVideoNotificado(){
        return this.ultimoVideoNotificado;
    }

    @Override
    public void update(Observable canal, Object arg1){
        this.ultimoVideoNotificado = estado.notificar(this, canal);
        System.out.println(this.ultimoVideoNotificado + " | Detalhe: " + arg1);
    }
}