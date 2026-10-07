package org.example;

public class ViewerEstadoDesinscrito extends ViewerEstado {
    private ViewerEstadoDesinscrito() {};
    private static ViewerEstadoDesinscrito instance = new ViewerEstadoDesinscrito();

    public static ViewerEstadoDesinscrito getInstance() {
        return instance;
    }

    public String getEstado() {
        return "Desinscrito";
    }

    public boolean inscrever(Viewer viewer, CanalYoutube canal) {
        canal.addObserver(viewer);
        viewer.setEstado(ViewerEstadoInscrito.getInstance());
        return true;
    }
}
