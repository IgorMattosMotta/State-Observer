package org.example;

import java.util.Observable;

public class ViewerEstadoApoiador extends ViewerEstado {
    private ViewerEstadoApoiador() {};
    private static ViewerEstadoApoiador instance = new ViewerEstadoApoiador();

    public static ViewerEstadoApoiador getInstance() {
        return instance;
    }

    public String getEstado() {
        return "Apoiador";
    }

    public boolean desinscrever(Viewer viewer, CanalYoutube canal) {
        canal.deleteObserver(viewer);
        viewer.setEstado(ViewerEstadoDesinscrito.getInstance());
        return true;
    }

    public boolean virarMembro(Viewer viewer) {
        viewer.setEstado(ViewerEstadoMembro.getInstance());
        return true;
    }

    public String notificar(Viewer viewer, Observable canal) {
        return viewer.getNome() + " [Apoiador], obrigado pelo apoio! Novo vídeo de: " + canal;
    }
}
