package br.com.projeto.arenapernambuco.service;

import br.com.projeto.arenapernambuco.model.Evento;
import br.com.projeto.arenapernambuco.repository.CompraRepository;
import br.com.projeto.arenapernambuco.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EstatisticaService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Async
    public CompletableFuture<Long> totalEventos() {

        System.out.println(
                "Thread Total Eventos: "
                        + Thread.currentThread().getName()
        );

        return CompletableFuture.completedFuture(
                eventoRepository.count()
        );
    }

    @Async
    public CompletableFuture<Long> aprovados() {

        System.out.println(
                "Thread Aprovados: "
                        + Thread.currentThread().getName()
        );

        return CompletableFuture.completedFuture(
                eventoRepository.countByStatus(
                        Evento.Status.APROVADO
                )
        );
    }

    @Async
    public CompletableFuture<Long> pendentes() {

        System.out.println(
                "Thread Pendentes: "
                        + Thread.currentThread().getName()
        );

        return CompletableFuture.completedFuture(
                eventoRepository.countByStatus(
                        Evento.Status.PENDENTE
                )
        );
    }

    @Async
    public CompletableFuture<Long> cancelados() {

        System.out.println(
                "Thread Cancelados: "
                        + Thread.currentThread().getName()
        );

        return CompletableFuture.completedFuture(
                eventoRepository.countByStatus(
                        Evento.Status.CANCELADO
                )
        );
    }

    @Async
    public CompletableFuture<Long> ingressosVendidos() {

        System.out.println(
                "Thread Ingressos: "
                        + Thread.currentThread().getName()
        );

        return CompletableFuture.completedFuture(
                compraRepository.count()
        );
    }
}