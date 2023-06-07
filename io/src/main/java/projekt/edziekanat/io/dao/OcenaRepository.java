package projekt.edziekanat.io.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import projekt.edziekanat.io.entites.Ocena;

import java.util.List;
import java.util.Optional;

public interface OcenaRepository extends JpaRepository<Ocena,Integer> {

    List<Ocena> findAllByStudent_IndexStudenta(Integer id);
    Optional<Ocena> findByStudent_IndexStudentaAndPrzedmiot_IdPrzedmiotuAndTyp(Integer i1 , Integer i2 , boolean bool);

    List<Ocena> findAllByPrzedmiot_IdPrzedmiotuAndStudent_IndexStudenta(Integer integer1, Integer integer2);
}

