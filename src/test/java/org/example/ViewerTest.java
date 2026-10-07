package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewerTest {
    private CanalYoutube canal;
    private Viewer viewer;

    @BeforeEach
    void setUp() {
        canal = new CanalYoutube(new Video());
        viewer = new Viewer("Igor");
    }

    @Test
    void deveComecarDesinscritoSemReceberNotificacao() {
        assertEquals(ViewerEstadoDesinscrito.getInstance(), viewer.getEstado());
        canal.publicarVideo();
        assertNull(viewer.getUltimoVideoNotificado());
    }

    @Test
    void deveInscreverEReceberNotificacao() {
        assertTrue(viewer.inscrever(canal));
        assertEquals(ViewerEstadoInscrito.getInstance(), viewer.getEstado());
        assertEquals(1, canal.countObservers());

        canal.publicarVideo();
        assertEquals("Igor, novo vídeo notificado de: " + canal, viewer.getUltimoVideoNotificado());
    }

    @Test
    void deveDesinscreverEPararDeReceberNotificacao() {
        viewer.inscrever(canal);
        assertTrue(viewer.desinscrever(canal));
        assertEquals(ViewerEstadoDesinscrito.getInstance(), viewer.getEstado());
        assertEquals(0, canal.countObservers());

        canal.publicarVideo();
        assertNull(viewer.getUltimoVideoNotificado());
    }

    @Test
    void naoDeveInscreverDuasVezes() {
        viewer.inscrever(canal);
        assertFalse(viewer.inscrever(canal));
        assertEquals(1, canal.countObservers());
    }

    @Test
    void naoDeveApoiarSemEstarInscrito() {
        assertFalse(viewer.apoiar());
        assertFalse(viewer.tornarMembro());
        assertEquals(ViewerEstadoDesinscrito.getInstance(), viewer.getEstado());
    }

    @Test
    void deveSeguirFluxoInscritoApoiadorMembro() {
        viewer.inscrever(canal);
        assertFalse(viewer.tornarMembro());
        assertTrue(viewer.apoiar());
        assertEquals(ViewerEstadoApoiador.getInstance(), viewer.getEstado());
        assertTrue(viewer.tornarMembro());
        assertEquals(ViewerEstadoMembro.getInstance(), viewer.getEstado());

        canal.publicarVideo();
        assertNotNull(viewer.getUltimoVideoNotificado());
    }

    @Test
    void apoiadorDeveReceberNotificacaoDeApoiador() {
        viewer.inscrever(canal);
        viewer.apoiar();

        canal.publicarVideo();
        assertEquals("Igor [Apoiador], obrigado pelo apoio! Novo vídeo de: " + canal,
                viewer.getUltimoVideoNotificado());
    }

    @Test
    void membroDeveReceberNotificacaoDeMembro() {
        viewer.inscrever(canal);
        viewer.apoiar();
        viewer.tornarMembro();

        canal.publicarVideo();
        assertEquals("Igor [Membro], acesso antecipado ao vídeo de: " + canal,
                viewer.getUltimoVideoNotificado());
    }

    @Test
    void viewersEmEstadosDiferentesRecebemNotificacoesDiferentes() {
        Viewer membro = new Viewer("Ana");
        viewer.inscrever(canal);
        membro.inscrever(canal);
        membro.apoiar();
        membro.tornarMembro();

        canal.publicarVideo();
        assertEquals("Igor, novo vídeo notificado de: " + canal, viewer.getUltimoVideoNotificado());
        assertEquals("Ana [Membro], acesso antecipado ao vídeo de: " + canal, membro.getUltimoVideoNotificado());
    }

    @Test
    void membroDeveConseguirSeDesinscrever() {
        viewer.inscrever(canal);
        viewer.apoiar();
        viewer.tornarMembro();
        assertTrue(viewer.desinscrever(canal));
        assertEquals(0, canal.countObservers());
        assertEquals(ViewerEstadoDesinscrito.getInstance(), viewer.getEstado());
    }
}
