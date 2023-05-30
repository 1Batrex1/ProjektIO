package projekt.edziekanat.io.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import projekt.edziekanat.io.entites.Wykladowca;

import java.util.Optional;

public interface WykladowcaRepository extends JpaRepository<Wykladowca,Integer> {
    Optional<Wykladowca> findWykladowcaByOsobaId(Integer theId);
}
