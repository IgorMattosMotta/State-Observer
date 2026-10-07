package org.example;

public class ViewerEstadoInscrito extends ViewerEstado {
    private ViewerEstadoInscrito() {};
    private static ViewerEstadoInscrito instance = new ViewerEstadoInscrito();

    public static ViewerEstadoInscrito getInstance() {
        return instance;
    }

    public String getEstado() {
        return "Inscrito";
    }

    public boolean desinscrever(Viewer viewer, CanalYoutube canal) {
        canal.deleteObserver(viewer);
        viewer.setEstado(ViewerEstadoDesinscrito.getInstance());
        return true;
    }

    public boolean apoiar(Viewer viewer) {
        viewer.setEstado(ViewerEstadoApoiador.getInstance());
        return true;
    }
}
