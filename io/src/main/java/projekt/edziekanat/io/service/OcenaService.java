package projekt.edziekanat.io.service;

import org.springframework.stereotype.Repository;
import projekt.edziekanat.io.entites.Ocena;

import java.util.List;

@Repository
public interface OcenaService {
    List<Ocena> findAllByStudentId(int theId);
}
