package br.com.projeto.arenapernambuco.repository;

import br.com.projeto.arenapernambuco.model.Ticket;
import br.com.projeto.arenapernambuco.model.User;
import br.com.projeto.arenapernambuco.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUser(User user);
    List<Ticket> findByEvent(Event event);
    Optional<Ticket> findByReservationCode(String reservationCode);
}