package projekt.edziekanat.io.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import projekt.edziekanat.io.entites.Przedmiot;

import java.util.List;
import java.util.Optional;

public interface PrzedmiotRepository extends JpaRepository<Przedmiot,Integer> {
    Optional<Przedmiot> findByIdPrzedmiotu(Integer integer);

    List<Przedmiot> findAllBySemestr(Integer integer);
}
