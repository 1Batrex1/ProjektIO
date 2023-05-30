package projekt.edziekanat.io.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import projekt.edziekanat.io.entites.Zajecia;

import java.util.List;

public interface ZajeciaRepository extends JpaRepository<Zajecia,Integer> {

    List<Zajecia> findAllByWykladowca_IndexWykladowcy(Integer integer);

    List<Zajecia> findAllByPrzedmiot(Integer integer);

    List<Zajecia> findAllByPrzedmiotAndGrupa(Integer integer1, Integer integer2);
}
