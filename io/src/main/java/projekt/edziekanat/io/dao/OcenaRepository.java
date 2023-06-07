package projekt.edziekanat.io.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import projekt.edziekanat.io.entites.Ocena;

import java.util.List;
import java.util.Optional;

public interface OcenaRepository extends JpaRepository<Ocena,Integer> {

    List<Ocena> findAllByStudent_IndexStudenta(Integer id);

    Optional<Ocena> findByStudent_IndexStudentaAndAndPrzedmiot_IdPrzedmiotuAndAndTyp(Integer i1 , Integer i2 , boolean bool);
}
