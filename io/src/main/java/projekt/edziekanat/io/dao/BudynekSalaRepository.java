package projekt.edziekanat.io.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import projekt.edziekanat.io.entites.BudynekSala;
import projekt.edziekanat.io.entites.BudynekSalaId;

public interface BudynekSalaRepository extends JpaRepository<BudynekSala, BudynekSalaId> {
}
