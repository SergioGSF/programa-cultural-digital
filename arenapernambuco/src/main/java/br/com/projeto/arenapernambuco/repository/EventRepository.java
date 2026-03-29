package br.com.projeto.arenapernambuco.repository;

import br.com.projeto.arenapernambuco.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCategory_NameIgnoreCase(String categoryName);
    List<Event> findByTitleContainingIgnoreCase(String title);
}
