package projekt.edziekanat.io.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import projekt.edziekanat.io.entites.Budynek;

public interface BudynekRepository extends JpaRepository<Budynek,Integer> {
}
