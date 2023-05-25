package projekt.edziekanat.io.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import projekt.edziekanat.io.entites.Ocena;

import java.util.List;

public interface OcenaRepository extends JpaRepository<Ocena,Integer> {

    List<Ocena> findAllByStudent_IndexStudenta(Integer id);
}
