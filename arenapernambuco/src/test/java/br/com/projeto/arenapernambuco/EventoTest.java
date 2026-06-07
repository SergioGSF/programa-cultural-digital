package br.com.projeto.arenapernambuco;

import br.com.projeto.arenapernambuco.model.Evento;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventoTest {

    // RED
    /*
    @Test
    void novoEventoDeveIniciarComoPendente() {

        Evento evento = new Evento();

        assertEquals(
                Evento.Status.PENDENTE,
                evento.getStatus()
        );
    }
    */

    // GREEN

    @Test
    void novoEventoDeveIniciarComoPendente() {

        Evento evento = new Evento();

        assertEquals(
                Evento.Status.PENDENTE,
                evento.getStatus()
        );
    }

    // REFACTOR
    /*
    @Test
    @DisplayName("Novo evento deve iniciar com status pendente")
    void novoEventoDeveIniciarComoPendente() {

        Evento evento = new Evento();

        assertEquals(
                Evento.Status.PENDENTE,
                evento.getStatus()
        );
    }
    */
}