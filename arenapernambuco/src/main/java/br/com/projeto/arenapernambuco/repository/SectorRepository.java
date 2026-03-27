package br.com.projeto.arenapernambuco.repository;

import br.com.projeto.arenapernambuco.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SectorRepository extends JpaRepository<Sector, Long> {
    Optional<Sector> findByName(String name);
}