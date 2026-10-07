package org.example;

import java.util.Observable;

public abstract class ViewerEstado {
    public abstract String getEstado();

    public boolean inscrever(Viewer viewer, CanalYoutube canal){
        return false;
    }

    public boolean desinscrever(Viewer viewer, CanalYoutube canal){
        return false;
    }

    public boolean apoiar(Viewer viewer){
        return false;
    }

    public boolean virarMembro(Viewer viewer){return false;}

    public String notificar(Viewer viewer, Observable canal){
        return viewer.getNome() + ", novo vídeo notificado de: " + canal;
    }
}
