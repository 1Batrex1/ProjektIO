package projekt.edziekanat.io.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import projekt.edziekanat.io.entites.Grupa;
import projekt.edziekanat.io.entites.Zajecia;

import java.util.List;

public interface ZajeciaRepository extends JpaRepository<Zajecia,Integer> {

    List<Zajecia> findAllByWykladowca_IndexWykladowcy(Integer integer);
}
