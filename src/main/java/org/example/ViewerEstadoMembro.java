package org.example;

import java.util.Observable;

public class ViewerEstadoMembro extends ViewerEstado {
    private ViewerEstadoMembro() {};
    private static ViewerEstadoMembro instance = new ViewerEstadoMembro();

    public static ViewerEstadoMembro getInstance() {
        return instance;
    }

    public String getEstado() {
        return "Tornou membro!";
    }

    public boolean desinscrever(Viewer viewer, CanalYoutube canal) {
        canal.deleteObserver(viewer);
        viewer.setEstado(ViewerEstadoDesinscrito.getInstance());
        return true;
    }

    public String notificar(Viewer viewer, Observable canal) {
        return viewer.getNome() + " [Membro], acesso antecipado ao vídeo de: " + canal;
    }
}
